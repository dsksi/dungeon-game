package unsw.dungeon;

import java.util.concurrent.TimeUnit;

public class InvinciblePotion extends Entity {

	public InvinciblePotion(int x, int y) {
		super(x, y);
	}

	@Override
	public boolean movable() {
		return true;
	}

	@Override
	public void interact(Entity obj) {
		if (!(obj instanceof Player)) return;
		Player pl = (Player) obj;
		pl.setAttackStrategy(new InvincibleAttack());
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pl.setAttackStrategy(new NoAttack());
	}

}
