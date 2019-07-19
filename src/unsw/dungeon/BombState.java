package unsw.dungeon;

public interface BombState {

		public BombState pickUp(Bomb bomb, Player player);
		public BombState activateBomb(Bomb bomb, Player player);
		public void explode(Bomb bomb, Player player);
		public void exploding(Bomb bomb, Player player);
}
