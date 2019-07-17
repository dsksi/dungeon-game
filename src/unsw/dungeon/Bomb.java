package unsw.dungeon;

public class Bomb extends Entity {

	private BombState state;
	public Bomb(int x, int y) {
		super(x, y);
		this.state = new Spawned();
	}

	@Override
	public boolean movable(Entity obj) {
		return true;
	}
	
	public void interact(Entity obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;
			state = state.pickUp(this, player);
		}
	}
	
	public void activate(Player player) {
		state = state.activateBomb(this, player);
		// wait x seconds
		state.explode(this, player);
		// Remove self from lists etc.
	}

}
