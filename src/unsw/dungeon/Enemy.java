package unsw.dungeon;

import java.util.ArrayList;
import java.util.Collections;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Enemy extends Entity implements Subject, Observer {
	
	private Dungeon dungeon;
	private int playerXPos;
	private int playerYPos;
	private ArrayList<Observer> observers;
	
	public Enemy(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		this.playerXPos = 0;
		this.playerYPos = 0;
		this.observers = new ArrayList<Observer>();
	}
	
	public void followPath() {
		//System.out.println("followPath");
		ArrayList<Node> path = this.findPathToPlayer();
		//System.out.println("after make list");
		for (Node e : path) {
			if (e.getX() < this.getX() && e.getY() == this.getY()) moveLeft();
			if (e.getX() > this.getX() && e.getY() == this.getY()) moveRight();
			if (e.getX() == this.getX() && e.getY() > this.getY()) moveDown();
			if (e.getX() == this.getX() && e.getY() < this.getY()) moveUp();
		}
		
	}
	
	public ArrayList<Node> findPathToPlayer() {
		
		boolean[][] visited = new boolean[dungeon.getHeight()+1][dungeon.getWidth()+1];
		
		ArrayList<Node> predecessor = new ArrayList<Node>();

		
		int[] dx = {-1, +1, 0, 0};
		int[] dy = {0, 0, +1, -1};
		
		Queue<Integer> xq = new LinkedList<Integer>();
		Queue<Integer> yq = new LinkedList<Integer>();
		
		xq.add(this.getX());
		yq.add(this.getY());
		visited[this.getX()][this.getY()] = true;
		
		Node enemy = new Node(0, this.getX(), this.getY());
		predecessor.add(enemy);
	
		while (!xq.isEmpty()) {
			int x = xq.remove();
			int y = yq.remove();
			//System.out.println("after pop");
			if (x == this.playerXPos && y == this.playerYPos) {
				System.out.println("ply x = " + x + " ply y = " + y);
				System.out.println("found player");
				break;
			}
			
			int parent_index = -1;
			for (int i = 0; i < predecessor.size(); i++) {
				Node n = predecessor.get(i);
				if (n.getX() == x && n.getY() == y) {
					parent_index = i;
					break;
				}
			}
			
			//System.out.println("before for");
			for (int i=0; i < 4; i++) {
				int futureX = x + dx[i];
				int futureY = y + dy[i];
				
				//System.out.println("pop x = " + x + " pop y = " + y);
				//System.out.println("x = " + futureX + " y = " + futureY);
				
				if (!this.checkMoveable(futureX, futureY)) continue;
				if (visited[futureX][futureY]) continue;

				xq.add(futureX);
				yq.add(futureY);

				visited[futureX][futureY] = true;

				
				Node child = new Node(parent_index, futureX, futureY);
				predecessor.add(child);
				//System.out.println("put in predecessor");
				
				
			}			
		}
		
		ArrayList<Node> directions = new ArrayList<Node>();
		
		//System.out.println("out");
		
		int plyIdx = -1;
		for (int i = 0; i < predecessor.size(); i++) {
			Node n = predecessor.get(i);
			if (n.getX() == playerXPos && n.getY() == playerYPos) {
				plyIdx = i;
				break;
			}
		}
		
		for (int i = plyIdx; i != predecessor.get(i).getParentIndex(); i = predecessor.get(i).getParentIndex()) {
			directions.add(predecessor.get(i));
		}
		
		
		Collections.reverse(directions);
		
		
		return directions;
	
	}
	
	public void runAwayPath(int x, int y) {
		return;
	}
	
	private boolean checkMoveable(int x, int y) {
    	if(!((y < dungeon.getHeight() - 1) && (y >= 0)))	return false;
    	if(!((x < dungeon.getWidth() - 1) && (x >= 0)))		return false;
    		
    	ArrayList<Entity> list = dungeon.getEntity(x, y);
        if(!list.isEmpty()) {
        	boolean result = true;
        	for (Entity e: list) {
        		if(! e.movable(this)) return false;
            }
        	return result;
        }
        return true;
    }
	
	// ------ Controls ------
	public void moveUp() {
		y().set(getY() - 1);
	}
	
	public void moveDown() {
		y().set(getY() + 1);
	}
	
	public void moveLeft() {
		x().set(getX() - 1);
	}
	
	public void moveRight() {
		x().set(getX() + 1);
	}
	
	//------ Observer methods ------
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void updateObservers() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
	
	//------ Subject methods ------
	@Override
	public void update(Subject obj) {
		if (!(obj instanceof Player)) return;
		
		Player player = (Player) obj;
		this.playerXPos = player.getX();
		this.playerYPos = player.getY();
		
		this.followPath();
	}
	
	//------ Entity methods ------
	@Override
	public boolean movable(Entity obj) {
		return true;
	}

	@Override
	public void interact(Entity obj) {
		if (!(obj instanceof Player)) return;
		
		Player player = (Player) obj;
		if (player.attack(this)) {
			updateObservers();
		} else {
			obj.delete();
		}
	}


}