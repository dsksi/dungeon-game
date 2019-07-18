package unsw.dungeon;

public class SwordAttack implements WeaponStrategy {

	int durability;
	
	public SwordAttack() {
		this.durability = 5;
	}
	
	@Override
	public boolean attack(Entity obj) {
		if (!(obj instanceof Enemy)) return false;
		Enemy enemy = (Enemy) obj;
		if(this.durability < 1) return false;
		enemy.delete();
		this.durability--;
		return true;
	}

	@Override
	public boolean hasDurability() {
		if (durability != 0) return true;
		return false;
	}
	
}
