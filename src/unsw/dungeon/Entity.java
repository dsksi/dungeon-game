package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y, status;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.status = new SimpleIntegerProperty(0);
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }
    
    public IntegerProperty status() {
        return this.status;
    }
    
    public void delete() {
        status().set(1);
    }
    
    public void restore() {
    	status().set(0);
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }

	public abstract boolean movable(Entity obj);
	public abstract void interact(Entity obj);
}
