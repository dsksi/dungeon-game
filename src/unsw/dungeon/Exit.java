package unsw.dungeon;

public class Exit extends Entity {
	
	public Exit(int x, int y) {
		super(x,y);
	}

	//------ Entity methods ------
	@Override
	public boolean movable(Entity obj) {
		if(!(obj instanceof Player)) return false;
		Player player = (Player) obj;
		if(player.completedNonExitGoals()) {
			return true;
		}
		return false;
	}

	@Override
	public void interact(Entity obj) {
		return;
	}
	
}
