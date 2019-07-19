package unsw.dungeon;

public class TreasureGoal implements Goal, Observer {

	public int getTreasureCount() {
		return treasureCount;
	}

	private int totalTreasure;
	private int treasureCount;
	
	public TreasureGoal(int goal) {
		this.totalTreasure = goal;
		this.treasureCount = 0;
	}
	
	@Override
	public boolean isComplete() {
		if(this.treasureCount >= this.totalTreasure) {
			System.out.println("Treasure goal is complete");
			return true;
		}
		return false;
	}

	@Override
	public void update(Subject obj) {
		Player pl = (Player) obj;
		this.treasureCount = pl.getTreasureCollected();	
	}

	public int getTotalTreasure() {
		return totalTreasure;
	}
	
	public int getTreasureCount1() {
		return this.treasureCount;
	}

}
