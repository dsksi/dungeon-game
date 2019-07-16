package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private int treasureCollected;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    private boolean checkMoveable(int x, int y) {
    	if(!((y < dungeon.getHeight() - 1) && (y >= 0)))	return false;
    	if(!((x < dungeon.getWidth() - 1) && (x >= 0)))		return false;
    		
    	ArrayList<Entity> list = dungeon.getEntity(x, y);
        if(!list.isEmpty()) {
        	for (Entity e: list) {
        		if (e instanceof Boulder) {
        			Boulder b = (Boulder) e;
        			if (b.checkMoveable(this)) {
        				b.interact(this);
        				return true;
        			} else {
        				//System.out.println("can't move boulder");
        				return false;
        			}
        		} 
        		else if(! e.movable()) return false;
            }
        }
        return true;
    }
    
    public void moveUp() {
    	if(this.checkMoveable(this.getX(), this.getY() - 1))
    		y().set(getY() - 1);
    }

    public void moveDown() {
    	if(this.checkMoveable(this.getX(), this.getY() + 1))
    		y().set(getY() + 1);
    }

    public void moveLeft() {
    	if(this.checkMoveable(this.getX() - 1, this.getY()))
    		x().set(getX() - 1);
    }

    public void moveRight() {
    	if(this.checkMoveable(this.getX() + 1, this.getY()))
    		x().set(getX() + 1);
    }

	@Override
	public boolean movable() {
		return true;
	}


	public int getTreasureCollected() {
		return this.treasureCollected;
	}

	@Override
	public void interact(Entity obj) {
		//TODO: implement enemy interaction with player
		return;
	}
	
	// to keep IDE happy
    public String getState() {
    	return null;
    }
}
