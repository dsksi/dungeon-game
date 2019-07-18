package unsw.dungeon;

public class InvinciblePotion extends Entity {

	
	
	public InvinciblePotion(int x, int y) {
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
		pl.drinkPotion(this);
	}

}