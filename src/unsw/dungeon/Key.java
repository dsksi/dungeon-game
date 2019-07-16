package unsw.dungeon;

public class Key extends Entity {

	private int ID;
	
	public Key(int x, int y, int ID) {
		super(x, y);
		this.ID = ID;
	}

	@Override
	public boolean movable() {
		return true;
	}

	@Override
	public void interact(Entity obj) {
		
		if (obj instanceof Player) {
			Player player = (Player) obj;
			pickUp(player);
		}
		
	}
	
	public void pickUp(Player player) {
		if (player.getKeyID() != -1) {
			player.setKeyID(ID);
			// dungeon.remove(this);
		}
	}

}
