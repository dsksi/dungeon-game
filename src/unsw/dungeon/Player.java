package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;
    private int keyID;
    private Bomb bomb;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
        if (getY() > 0) {
        	ArrayList<Entity> list = dungeon.getEntity(this.getX(), this.getY()-1);
        	if(!list.isEmpty()) {
	        	for (Entity e: list) {
	        		if(! e.movable()) return;
	        	}
        	}
        	y().set(getY() - 1);
        }
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) {
        	ArrayList<Entity> list = dungeon.getEntity(this.getX(), this.getY()+1);
        	if(!list.isEmpty()) {
	        	for (Entity e: list) {
	        		if(! e.movable()) return;
	        	}
        	}
        	y().set(getY() + 1);
        }
    }

    public void moveLeft() {
        if (getX() > 0) {
        	ArrayList<Entity> list = dungeon.getEntity(this.getX()-1, this.getY());
        	if(!list.isEmpty()) {
	        	for (Entity e: list) {
	        		if(! e.movable()) return;
	        	}
        	}
        	x().set(getX() - 1);
        }
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) {
        	ArrayList<Entity> list = dungeon.getEntity(this.getX()+1, this.getY());
        	if(!list.isEmpty()) {
	        	for (Entity e: list) {
	        		if(! e.movable()) return;
	        	}
        	}
            x().set(getX() + 1);
        }
    }

	@Override
	public boolean movable() {
		return true;
	}
	
	public int getKeyID() {
		return keyID;
	}

	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}

	public Dungeon getDungeon() {
		return dungeon;
	}
}
