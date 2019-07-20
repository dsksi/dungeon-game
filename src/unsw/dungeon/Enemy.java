package unsw.dungeon;

import java.util.ArrayList;
import java.util.Collections;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


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
	

	public boolean getGameInProgress() {
    	return dungeon.getGameInProgress();
    }
	
	// ------ Path finder methods ------ 
	
	public void followPath(ArrayList<Node> pathList) {
		ArrayList<Node> path = pathList;	
		for (Node e : path) {
			
			Entity entity = null;
			if (dungeon.getEntity(e.getX(), e.getY()).size() != 0) {
				entity = dungeon.getEntity(e.getX(), e.getY()).get(0);
			}
			if (entity != null) entity.interact(this);
			
			if (e.getX() < this.getX() && e.getY() == this.getY()) moveLeft();
			else if (e.getX() > this.getX() && e.getY() == this.getY()) moveRight();
			else if (e.getX() == this.getX() && e.getY() > this.getY()) moveDown();
			else if (e.getX() == this.getX() && e.getY() < this.getY()) moveUp();
			
		}
	}
	
	public ArrayList<Node> playerPath() {
		if (findPathTo(playerXPos, playerYPos).size() == 0) {
			return runAwayPath();
		}
		return findPathTo(playerXPos, playerYPos);
	}
	
	public ArrayList<Node> runAwayPath() {
		// Using random generator to determine path
		
		Random rand = new Random();
		
		int randX = rand.nextInt(dungeon.getHeight());
		int randY = rand.nextInt(dungeon.getWidth());
		
		int newX = (randX + playerXPos)%dungeon.getHeight();
		int newY = (randY + playerYPos)%dungeon.getWidth();
		
		while (!checkMoveable(newX,newY)) {
			randX = rand.nextInt(dungeon.getHeight());
			randY = rand.nextInt(dungeon.getWidth());
			
			newX = (randX + playerXPos + 1)%dungeon.getHeight();
			newY = (randY + playerYPos + 1)%dungeon.getWidth();
		}
		
		return findPathTo(newX, newY);
	}
	
	public ArrayList<Node> findPathTo(int tarX, int tarY) {
		int player_index = 0;
		int index = 0;
		Queue<Node> q = new LinkedList<Node>();
		ArrayList<Node> predecessor = new ArrayList<Node>();
		boolean[][] visited = new boolean[dungeon.getHeight()+1][dungeon.getWidth()+1];

		int[] dx = {-1, +1, 0, 0};
		int[] dy = {0, 0, +1, -1};

		Node enemy = new Node(index, 0, this.getX(), this.getY());
		index++;
		
		q.add(enemy);
		predecessor.add(enemy);
		visited[enemy.getX()][enemy.getY()] = true;

		while (!q.isEmpty()) {
			Node node = q.remove();
			int x = node.getX();
			int y = node.getY();
			int parent_index = node.getIndex();
	
			if (x == tarX && y == tarY) {
				player_index = node.getIndex();
				break;
			}
			for (int i=0; i < 4; i++) {
				int futureX = x + dx[i];
				int futureY = y + dy[i];
		
				if (!this.checkMoveable(futureX, futureY)) continue;
				if (visited[futureX][futureY]) continue;

				Node child = new Node(index, parent_index, futureX, futureY);
				index++;
				
				q.add(child);
				predecessor.add(child);
				visited[futureX][futureY] = true;
			}			
		}
		
		ArrayList<Node> directions = new ArrayList<Node>();
		for (int i = player_index; i != predecessor.get(i).getParentIndex(); i = predecessor.get(i).getParentIndex()) {
			directions.add(predecessor.get(i));
		}
		
		Collections.reverse(directions);
		return directions;
	
	}
	
	private boolean checkMoveable(int x, int y) {
		if(!getGameInProgress()) return false;
    	if(!((y < dungeon.getHeight()) && (y >= 0)))	return false;
    	if(!((x < dungeon.getWidth()) && (x >= 0)))		return false;

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

		if(player.isInvincible()) {
			followPath(runAwayPath());
		} else {
			followPath(playerPath());
		}
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