package unsw.dungeon;

public class NoAttack implements AttackStrategy {

	@Override
	public boolean attack(Player pl) {
		return false;
	}

}
