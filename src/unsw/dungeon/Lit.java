package unsw.dungeon;

public class Lit implements BombState {

	@Override
	public BombState pickUp(Bomb bomb, Player player) {
		return this;
	}

	@Override
	public BombState activateBomb(Bomb bomb, Player player) {
		return this;
	}

	@Override
	public void explode(Bomb bomb, Player player) {
		
		int x = bomb.getX();
		int y = bomb.getY();
		Dungeon dungeon = player.getDungeon();
		
		for (int i = (x - 1); i <= (x + 1); i++) {
			for (int j = (y - 1); j <= (y + 1); j++) {
				for (Entity k : dungeon.getEntity(x, y)) {
					if (k instanceof Enemy) {
						// kill enemy
					} else if (k instanceof Player) {
						// kill player
					}
				}
			}
		}
		// suggest dungeon.remove(Entity);
		// removes the need for entities to get a list of entities from the dungeon
		// when it needs to be deleted.
		return;
	}

}
