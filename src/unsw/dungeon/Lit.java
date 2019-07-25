package unsw.dungeon;

public class Lit implements BombState {

	/**
	 * Lit bomb should not be able to be picked up.
	 * @param bomb Bomb to be picked up
	 * @param player Player picking up bomb
	 * @return Returns the Lit state
	 */
	@Override
	public BombState pickUp(Bomb bomb, Player player) {
		return this;
	}

	/**
	 * Lit bomb should not be able to be activated.
	 * @param bomb Bomb to be activated
	 * @param player Player to be activated
	 * @return Returns the Lit state.
	 */
	@Override
	public BombState activateBomb(Bomb bomb, Player player) {
		return this;
	}

	/**
	 * Kills player, enemies and boulders in coordinates directly adjacent to and on the bomb.
	 * Removes the bomb from the list of entities.
	 * Sets the visual status of the bomb to invisible.
	 * @param bomb Bomb to be exploded
	 * @param player Player to explode bomb
	 */
	@Override
	public void explode(Bomb bomb, Player player) {
		
		int x = bomb.getX();
		int y = bomb.getY();
		Dungeon dungeon = player.getDungeon();
		
//		for (int i = (x - 1); i <= (x + 1); i++) {
//			for (int j = (y - 1); j <= (y + 1); j++) {
//				for (Entity k : dungeon.getEntity(i, j)) {
//					if (k instanceof Enemy) {
//						k.delete();
//					} else if (k instanceof Player) {
//						k.delete();
//					} else if (k instanceof Boulder) {
//						k.delete();
//					}
//				}
//			}
//		}
		
		int[] diffx = {-1, +1, 0, 0, 0};
		int[] diffy = {0, 0, +1, -1, 0};
		
		for (int i = 0; i < 5; i++) {
			int relativePosX = bomb.getX() + diffx[i];
			int relativePosY = bomb.getY() + diffy[i];
			
			for (Entity k : dungeon.getEntity(relativePosX, relativePosY)) {
				if ((k instanceof Player)
				|| (k instanceof Boulder)) {
					k.delete();
				}
				if (k instanceof Enemy) {
					Enemy enemy = (Enemy) k;
					enemy.isDead();
				}
			}
		}
		bomb.setVisualStatus(1);
		return;
	}

	/**
	 * Sets the visual status of the bomb to show explosion
	 * @param bomb Bomb to be exploded
	 * @param player Player activating the bomb
	 */
	@Override
	public void exploding(Bomb bomb, Player player) {
		bomb.setVisualStatus(2);
	}
}
