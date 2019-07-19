package unsw.dungeon;

public class Spawned implements BombState {

	@Override
	public BombState pickUp(Bomb bomb, Player player) {
		if (player.getBomb() == null) {
			player.setBomb(bomb);
			bomb.delete();
			return new Unlit();
		} else {
			return this;
		}

	}

	@Override
	public BombState activateBomb(Bomb bomb, Player player) {
		return this;
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
