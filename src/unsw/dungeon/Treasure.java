package unsw.dungeon;

public class Treasure extends Entity{

	private boolean isCollected;
	
	public Treasure(int x, int y) {
		super(x, y);
		this.isCollected = false;
	}
	
	public void collect() {
		this.isCollected = true;
	}

	@Override
	public boolean movable() {
		return true;
	}

	@Override
	public void interact(Entity obj) {
		// Increment treasure count on player
		if (obj instanceof Player && isCollected == false) {
			Player player = (Player) obj;
			player.pickUpTreasure(this);
			this.delete();
		}
		
	}
	
	
}
