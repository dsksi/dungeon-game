package unsw.dungeon;

public class Unlit implements BombState {

	@Override
	public BombState pickUp(Bomb bomb, Player player) {
		return this;
	}

	@Override
	public BombState activateBomb(Bomb bomb, Player player) {
		bomb.x().set(player.getX());
		bomb.y().set(player.getY());
		bomb.restore();
		player.setBomb(null);
		return new Lit();
	}

	@Override
	public void explode(Bomb bomb, Player player) {
		return;
	}

	@Override
	public void exploding(Bomb bomb, Player player) {
		return;
	}
}
