package unsw.dungeon;

public class TreasureGoal implements Goal, Observer {

	private int treasureGoal;
	private int treasureCount;
	
	public TreasureGoal(int goal) {
		this.treasureGoal = goal;
		this.treasureCount = 0;
	}
	
	@Override
	public boolean isComplete() {

		if(this.treasureCount == this.treasureGoal) return true;
		return false;
	
	}

	@Override
	public void update(Subject obj) {

		Player pl = (Player) obj;
		this.treasureCount = pl.getTreasureCollected();
		
	}

	public int getTreasureGoal() {
		return treasureGoal;
	}

}
