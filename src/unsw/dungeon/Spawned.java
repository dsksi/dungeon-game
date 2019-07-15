package unsw.dungeon;

public class Spawned implements BombState {

	@Override
	public BombState pickUp(Player player, Bomb bomb) {
		if (player.getBomb() == null) {
			player.setBomb(bomb);
			return new Lit();
		} else {
			
			return this;
		}

	}

	@Override
	public BombState activateBomb(Bomb bomb, int x, int y) {
		return this;
	}

	@Override
	public void explode(Dungeon dungeon, int x, int y) {
	}

}
