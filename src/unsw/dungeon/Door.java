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

		if (locked) {
			return false;
		} 

		return true;
	}
	
	public void interact(Entity obj) {
		if(!(obj instanceof Player)) {
			return;
		} else {
			Player player = (Player) obj;
			if (player.getKeyID() == this.ID) {
				this.locked = false;
				player.setKeyID(-1);
			}
		}
	}
}
