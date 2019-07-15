package unsw.dungeon;

public class ExitGoal implements Goal, Observer {

	private boolean status;
	
	public ExitGoal() {
		this.status = false;
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
