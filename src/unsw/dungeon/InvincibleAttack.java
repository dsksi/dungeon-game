package unsw.dungeon;

public class InvincibleAttack implements AttackStrategy {

	@Override
	public boolean attack(Player pl) {
		return true;
	}

}
