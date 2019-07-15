package unsw.dungeon;

import java.util.ArrayList;

public class GameState {

	private Goal goal;
	
	public GameState() {
		this.goal = null;
	}
	
	public boolean checkGameComplete() {
		if(this.goal == null) return false;
		return this.goal.isComplete();
	}
	
	private TreasureGoal createTreasureGoal(int count) {
		TreasureGoal tg = new TreasureGoal(count);
		return tg;
	}
	
	private ExitGoal createExitGoal() {
		ExitGoal eg = new ExitGoal();
		return eg;
	}
	
	private SwitchGoal createSwitchGoal(int count) {
		SwitchGoal sg = new SwitchGoal(count);
		return sg;
	}
	
	private EnemyGoal createEnemyGoal(int count) {
		EnemyGoal eg = new EnemyGoal(count);
		return eg;
	}
	
	private Goal createSimpleGoal(String type, Dungeon dungeon) {
		Goal g = null;
		
		if(type.equals("treasure")) {
			int count = dungeon.getTreasureCount();
			g = this.createTreasureGoal(count);
		} else if (type.equals("enemies")) {
			int count = dungeon.getEnemyCount();
			g = this.createEnemyGoal(count);
		} else if (type.equals("exit")) {
			g = this.createExitGoal();
		} else if (type.equals("boulders")) {
			int count = dungeon.getSwitchCount();
			g = this.createSwitchGoal(count);
		}
		
		return g;	}
	
	private CompositeGoal createCompositeGoal(String type) {
		CompositeGoal cg = new CompositeGoal(type);
		return cg;
	}
	
	public void addCompositeGoal(String type, ArrayList<String> subgoals, Dungeon dungeon) {
		CompositeGoal cg = createCompositeGoal(type);
		for(String sg : subgoals) {
			Goal g = createSimpleGoal(sg, dungeon);
		}
		cg.addGoal(g);
		this.goal = cg;
	}
	
	public void addSimpleGoal(String type, Dungeon dungeon) {
		this.goal = createSimpleGoal(type, dungeon);
	}
}
