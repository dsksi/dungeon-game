package unsw.dungeon;

public class Sword extends Entity {

	
	public Sword(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean movable(Entity obj) {
		return true;
	}

	@Override
	public void interact(Entity obj) {
		if (!(obj instanceof Player)) return;
		Player pl = (Player) obj;
		pl.setAttackStrat(new SwordAttack());
		pl.pickUpSword(this);
	}


}
