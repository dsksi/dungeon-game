package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class ExitGoalTest {
	Dungeon dungeon;
	Player player;
	GameState gameState;
	
	@BeforeEach
	void setUp() throws Exception {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		gameState = dungeon.getGameState();
	}

	@Test
	void testSuccessExitGoalInitial() {
		Exit exit = new Exit(0, 1);
		dungeon.addEntity(exit);
		gameState.addSimpleGoal("exit", dungeon);
		
		Goal g = gameState.getGoal();
		assertTrue(g instanceof ExitGoal);
		assertFalse(g.isComplete());
		assertFalse(gameState.checkGameComplete());
		assertTrue(gameState.isGameInProgress());
	}
	
	@Test
	void testSuccessExitGoalComplete() {
		Exit exit = new Exit(0, 1);
		dungeon.addEntity(exit);
		gameState.addSimpleGoal("exit", dungeon);
		
		Goal g = gameState.getGoal();

		player.moveDown();
		assertTrue(g.isComplete());
		assertTrue(gameState.checkGameComplete());
		assertFalse(gameState.isGameInProgress());
	}

}
