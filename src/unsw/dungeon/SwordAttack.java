package unsw.dungeon;

/**
 * A weapon strategy stating the player has a Sword.
 * @author Amy
 *
 */
public class SwordAttack implements WeaponStrategy {

	int durability;
	
	/**
	 * Constructor for the SwordAttack strategy. gives a sword 5 durability.
	 */
	public SwordAttack() {
		this.durability = 5;
	}
	
	/**
	 * Attacks an enemy and reducing its durability  with each attack.
	 */
	@Override
	public boolean attack(Entity obj) {
		if (!(obj instanceof Enemy)) return false;
		Enemy enemy = (Enemy) obj;
		if(this.durability < 1) return false;
		enemy.delete();
		this.durability--;
		return true;
	}
	
	/**
	 * Checks if a sword has enough durability to attack.
	 */
	@Override
	public boolean hasDurability() {
		if (durability != 0) return true;
		return false;
	}
	
}
