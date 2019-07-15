package unsw.dungeon;

public class Unlit implements BombState {

	@Override
	public BombState pickUp(Player player, Bomb bomb) {
		return this;
	}

	@Override
	public BombState activateBomb(Bomb bomb, int x, int y) {
		bomb.x().set(x);
		bomb.y().set(y);
		return new Lit();
	}

	@Override
	public void explode(Dungeon dungeon, int x, int y) {
		return;
	}
}
