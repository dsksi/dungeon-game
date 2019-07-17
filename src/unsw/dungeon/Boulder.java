package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity{
	
	private Dungeon dungeon;

	public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }
	
	public boolean checkMoveable(Player player) {
		int futureX = this.getX();
		int futureY = this.getY();
		
		
		int playerX = player.getX();
		int playerY = player.getY();
		
		if (playerX == this.getX()) {
			// player moving either up or down
			if (playerY > this.getY()) {
				// player moving down so check what's down of boulder
				futureY = this.getY() - 1;
			} else {
				// player moving up
				futureY = this.getY() + 1;
			}
		} else if (playerY == this.getY()) {
			// player moving either left or right
			if (playerX > this.getX()) {
				futureX = this.getX() - 1;
			} else {
				futureX = this.getX() + 1;
			}
		}
    	if(!((futureY < dungeon.getHeight() - 1) && (futureY >= 0)))	return false;
    	if(!((futureX < dungeon.getWidth() - 1) && (futureX >= 0)))		return false;
    	
    	ArrayList<Entity> list = dungeon.getEntity(futureX, futureY);
        if(!list.isEmpty()) {
        	for (Entity e: list) {
        		if(! e.movable()) return false;
            }
        }
        return true;
    }
	
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

	@Override
	public boolean movable(Entity obj) {
		return false;
	}

	@Override
	public void interact(Entity obj) {
		if (!(obj instanceof Player)) return;
		
		Player player = (Player) obj;
		
		int playerX = player.getX();
		int playerY = player.getY();
		
		if (playerX == this.getX()) {
			// player moving either up or down
			if (playerY > this.getY()) {
				// player moving down so check what's down of boulder
				this.moveUp();
			} else {
				// player moving up
				this.moveDown();
				
			}
		} else if (playerY == this.getY()) {
			// player moving either left or right
			if (playerX > this.getX()) {
				this.moveLeft();
			} else {
				this.moveRight();
			}
		}		
	}

}
