package unsw.dungeon;

public class ExitGoal implements Goal {

	private boolean status;
	private Player player;
	private Exit exit;
	public ExitGoal(Player pl, Exit exit) {
		this.status = false;
		this.player = pl;
		this.exit = exit;
	}
	
	@Override
	public boolean isComplete() {
		return this.status;
	}

	@Override
	public void update(Subject obj) {
		this.status = true;
		
	}
}
