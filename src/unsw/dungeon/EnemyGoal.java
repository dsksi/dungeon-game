package unsw.dungeon;

public class EnemyGoal implements Goal, Observer {

	private int defeated;
	private int totalEnemies;
	
	public EnemyGoal(int count) {
		this.defeated = 0;
		this.totalEnemies = count;
	}
	
	@Override
	public void update(Subject obj) {
		if (obj instanceof Enemy) {
			this.defeated++;
		}
	}

	public int getDefeated() {
		return defeated;
	}

	public int getTotalEnemies() {
		return totalEnemies;
	}

	@Override
	public boolean isComplete() {
		if (this.defeated == this.totalEnemies)
			return true;
		return false;
	}

}
