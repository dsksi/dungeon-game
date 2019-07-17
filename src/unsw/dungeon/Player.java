package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject{

    private Dungeon dungeon;
    private int treasureCollected;
    private ArrayList<Observer> observers;
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.treasureCollected = 0;
        this.observers = new ArrayList<Observer>();
    }

    private boolean checkMoveable(int x, int y) {
    	if(!((y < dungeon.getHeight() - 1) && (y >= 0)))	return false;
    	if(!((x < dungeon.getWidth() - 1) && (x >= 0)))		return false;
    		
    	ArrayList<Entity> list = dungeon.getEntity(x, y);
        if(!list.isEmpty()) {
        	for (Entity e: list) {
        		if(! e.movable()) return false;
            }
        }
        return true;
    }
    
    public void moveUp() {
    	if(this.checkMoveable(this.getX(), this.getY() - 1)) {
    		y().set(getY() - 1);
    		dungeon.interactItems(this);
    	}
    }

    public void moveDown() {
    	if(this.checkMoveable(this.getX(), this.getY() + 1)) {
    		y().set(getY() + 1);
    		dungeon.interactItems(this);
    	}
    }

    public void moveLeft() {
    	if(this.checkMoveable(this.getX() - 1, this.getY())) {
    		x().set(getX() - 1);
    		dungeon.interactItems(this);
    	}
    }

    public void moveRight() {
    	if(this.checkMoveable(this.getX() + 1, this.getY())) {
    		x().set(getX() + 1);
    		dungeon.interactItems(this);
    	}
    }

	@Override
	public boolean movable() {
		return true;
	}

	public void pickUpTreasure(Treasure treasure) {
		System.out.println("Pick up treasure");
		treasure.collect();
		dungeon.removeEntity(treasure);
		this.treasureCollected++;
		this.updateObservers();
	}
	
	public int getTreasureCollected() {
		return this.treasureCollected;
	}

	@Override
	public void interact(Entity obj) {
		//TODO: implement enemy interaction with player
		return;
	}

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
	
}
