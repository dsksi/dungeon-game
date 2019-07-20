package unsw.dungeon;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameState {

	private Goal goal;
	private boolean gameInProgress;
	
	public GameState() {
		this.goal = null;
		this.gameInProgress = true;
	}
	
	public Goal getGoal() {
		return this.goal;
	}
	public boolean isGameInProgress() {
		return this.gameInProgress;
	}
	
	public void gameEnded() {
		this.gameInProgress = false;
	}
	
	public boolean checkGameComplete() {
		if(this.goal == null) return true;
		return this.goal.isComplete();
	}
	
	private TreasureGoal createTreasureGoal(Dungeon dungeon) {
		int count = dungeon.getTreasureCount();
		TreasureGoal tg = new TreasureGoal(count);
		dungeon.getPlayer().registerObserver(tg);
		return tg;
	}
	
	private ExitGoal createExitGoal(Dungeon dungeon) {
		Exit exit = dungeon.getExit();
		Player player = dungeon.getPlayer();
		ExitGoal eg = new ExitGoal(player, exit);
		return eg;
	}
	
	private SwitchGoal createSwitchGoal(Dungeon dungeon) {
		int count = dungeon.getSwitchCount();
		SwitchGoal sg = new SwitchGoal(count);
		dungeon.setUpObserverSwitchGoal(sg);
		return sg;
	}
	
	private EnemyGoal createEnemyGoal(Dungeon dungeon) {
		int count = dungeon.getEnemyCount();
		EnemyGoal eg = new EnemyGoal(count);
		dungeon.setUpObserverEnemyGoal(eg);
		return eg;
	}
	
	private Goal createSimpleGoal(String type, Dungeon dungeon) {
		Goal g = null;
		
		if(type.equals("treasure")) {
			g = this.createTreasureGoal(dungeon);
		} else if (type.equals("enemies")) {
			g = this.createEnemyGoal(dungeon);
		} else if (type.equals("exit")) {
			g = this.createExitGoal(dungeon);
		} else if (type.equals("boulders")) {
			g = this.createSwitchGoal(dungeon);
		}
		
		return g;	
	}
	
	private CompositeGoal createCompositeGoal(String type, JSONArray subgoals, Dungeon dungeon) {
		CompositeGoal cg = new CompositeGoal(type);
		cg = addSubGoals(cg, subgoals, dungeon);
		return cg;
	}
	
	private CompositeGoal addSubGoals(CompositeGoal cg, JSONArray subgoals, Dungeon dungeon) {
		for (int i = 0; i < subgoals.length(); i++) {
            JSONObject goal = subgoals.getJSONObject(i);
            String sg = goal.getString("goal");
            Goal g;
            if(sg.equals("OR") || sg.equals("AND")) {
            	JSONArray tmpSub = goal.getJSONArray("subgoals");
            	g = createCompositeGoal(sg, tmpSub, dungeon);
            } else {
            	g = createSimpleGoal(sg, dungeon);
            }
             
            cg.addGoal(g);
        }
		return cg;
	}
	
	public void addCompositeGoal(String type, JSONArray subgoals, Dungeon dungeon) {
		CompositeGoal cg = createCompositeGoal(type, subgoals, dungeon);
		this.goal = cg;
	}
	
	public void addSimpleGoal(String type, Dungeon dungeon) {
		this.goal = createSimpleGoal(type, dungeon);
	}
	
}
