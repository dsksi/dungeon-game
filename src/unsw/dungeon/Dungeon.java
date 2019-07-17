/** ==
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public ArrayList<Entity> getEntity(int x, int y) {
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	for (Entity e: entities) {
    		if (e == null) continue;
    		System.out.println(x + "  X = " + e.getX() + "    "+y + "  Y = " + e.getY());
    		if(e.getX() == x && e.getY() == y) {
    			System.out.println("Found");
    			list.add(e);

    		}
    	}
    	return list;
    }
    
    public void interactItems(Entity obj, int x, int y) {
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	list = this.getEntity(x, y);
    	for (Entity e : list) {
    		e.interact(obj);
    	}
    }
}
