package unsw.dungeon;

public class ExitGoal implements Goal {

	private Player player;
	private Exit exit;
	
	public ExitGoal(Player pl, Exit exit) {
		this.player = pl;
		this.exit = exit;
	}
	
	@Override
	public boolean isComplete() {
		if(player.getX() == exit.getX() && player.getY() == exit.getY()) {
			return true;
		}
		return false;
	}

}
