package unsw.dungeon;

public class NoWeapon implements WeaponStrategy {

	@Override
	public boolean attack(Entity obj) {
		return false;
	}

	@Override
	public boolean hasDurability() {
		return true;
	}

}
