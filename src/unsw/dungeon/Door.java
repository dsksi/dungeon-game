package unsw.dungeon;

public class Door extends Entity {
	
	private int ID;
	private boolean locked;

	public Door(int x, int y, int ID) {
		super(x, y);
		this.ID = ID;
		this.locked = true;
	}

	@Override
	public boolean movable() {

		if (!isLocked()) {
			return true;
		} 

		return false;
	}
	
	public void interact(Entity obj) {
		if(!(obj instanceof Player)) {
			return;
		}
		
		Player player = (Player) obj;
		open(player.getKey());
	}
	
	public boolean open(Key key) {
		
		if (key.getID() == this.ID) {
			this.locked = false;
			return true;
		}
		
		return false;
	}

	public boolean isLocked() {
		return locked;
	}
}
