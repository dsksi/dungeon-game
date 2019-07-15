package unsw.dungeon;

public class Lit implements BombState {

	@Override
	public BombState pickUp(Player player, Bomb bomb) {
		return this;
	}

	@Override
	public BombState activateBomb(Bomb bomb, int x, int y) {
		return this;
	}

	@Override
	public void explode(Dungeon dungeon, int x, int y) {
		
		for (Entity i : dungeon.get)
		return;
	}

}
