package unsw.dungeon;

public class Key extends Entity {

	private int ID;
	private boolean used;
	
	public Key(int x, int y, int ID) {
		super(x, y);
		this.ID = ID;
		this.used = false;
	}

	@Override
	public boolean movable() {
		return true;
	}
	
	public void pickUp(Player player) {
		//player.iteract
	}

	public int getID() {
		return ID;
	}

}
