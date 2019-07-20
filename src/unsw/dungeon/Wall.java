package unsw.dungeon;

/**
 * A wall that blocks the movement of all other entities in the dungeon
 * @author Siyin Zhou
 *
 */
public class Wall extends Entity {

	/**
	 * Create a wall given the coordinate
	 * @param x
	 * @param y
	 */
    public Wall(int x, int y) {
        super(x, y);
    }
    
	@Override
	public boolean movable(Entity obj) {
		return false;
	}

	@Override
	public void interact(Entity obj) {
		return;
	}
}
