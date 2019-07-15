package unsw.dungeon;

public interface BombState {

		public BombState pickUp(Player player, Bomb bomb);
		public BombState activateBomb(Bomb bomb, int x, int y);
		public void explode(Dungeon dungeon, int x, int y);
}
