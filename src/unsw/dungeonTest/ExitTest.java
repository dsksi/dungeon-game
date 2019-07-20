package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class ExitTest {
	
	Dungeon dungeon;
	Player player;
	Exit exit;
	GameState gameState;
	JSONObject treasureGoal;
	JSONObject enemyGoal;
	JSONObject exitGoal;
	JSONObject switchGoal;
	
	@BeforeEach
	void setUp() throws Exception {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		exit = new Exit(0, 1);
		dungeon.addEntity(exit);
		gameState = dungeon.getGameState();
		
		treasureGoal = new JSONObject();
		treasureGoal.put("goal", "treasure");
		enemyGoal = new JSONObject();
		enemyGoal.put("goal", "enemies");
		switchGoal = new JSONObject();
		switchGoal.put("goal", "boulders");
		exitGoal = new JSONObject();
		exitGoal.put("goal", "exit");
	}

	@Test
	void testPlayerCanMoveToExit() {
		player.moveDown();
		assertTrue(player.getY() == 1);
		assertTrue(player.getX() == 0);
	}
	
	@Test
	void testPlayerCanNotMoveToExitWhenOtherGoalsNotComplete() {
		JSONArray subgoals = new JSONArray();

		subgoals.put(treasureGoal);
		subgoals.put(exitGoal);
	    Treasure t1 = new Treasure(1,4);
	    dungeon.addEntity(t1);
		gameState.addCompositeGoal("AND", subgoals, dungeon);
		Goal g = gameState.getGoal();
		assertFalse(g.isComplete());
		
		player.moveDown();
		assertTrue(player.getY() == 0);
		assertTrue(player.getX() == 0);
	}

}
