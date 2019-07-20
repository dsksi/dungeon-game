package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class GameStateTest {
	
	Dungeon dungeon;
	GameState gameState;
	Player player;
	JSONObject treasureGoal;
	JSONObject enemyGoal;
	JSONObject exitGoal;
	JSONObject switchGoal;
	Switch s1;
	Switch s2;
	
	@BeforeEach
	void setUp() throws Exception {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		Treasure t1 = new Treasure(0, 1);
		Treasure t2 = new Treasure(0, 2);
		Exit exit = new Exit(0, 3);
		dungeon.addEntity(exit);
		dungeon.addEntity(t1);
		dungeon.addEntity(t2);
		s1 = new Switch(1, 1);
		s2 = new Switch(1, 2);
		dungeon.addEntity(s1);
		dungeon.addEntity(s2);
		
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
	void testGameStateInitial() {
		assertTrue(gameState.isGameInProgress());
		assertTrue(gameState.checkGameComplete());
		Treasure t1 = new Treasure(1, 1);
		dungeon.addEntity(t1);
		gameState.addSimpleGoal("treasure", dungeon);
		assertFalse(gameState.checkGameComplete());
	}
	
	@Test
	void testGameNonExitGoalCompletion() {
		
	}

}
