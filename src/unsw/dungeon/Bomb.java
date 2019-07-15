package unsw.dungeon;

public class Bomb extends Entity {

	private BombState state;
	public Bomb(int x, int y) {
		super(x, y);
		this.state = new Unlit();
	}

	@Override
	public boolean movable() {
		return true;
	}
	
	public void interact(Entity obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;
			state = state.pickUp(player, this);
		}
	}
	
	public void activate(Dungeon dungeon, int x, int y) {
		state.activateBomb(this, x, y);		
		state.explode(dungeon, x, y);
		// Remove self from lists etc.
	}

}
