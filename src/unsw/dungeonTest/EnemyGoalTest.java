package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.EnemyGoal;
import unsw.dungeon.GameState;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

class EnemyGoalTest {

	Dungeon dungeon;
	Player player;
	
	@BeforeEach
	void setUp() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}

	@Test
	void testEnemyGoalSuccessNoEnemy() {
		System.out.println("------ Tests enemy goal no enemy--------");
		EnemyGoal eg = new EnemyGoal(0);
		assertTrue(eg.getDefeated() == 0);
		assertTrue(eg.getTotalEnemies() == 0);
		assertTrue(eg.isComplete());
		System.out.println("Passed");
	}
	
	@Test
	void testEnemyGoalSuccessInitial() {
		System.out.println("------ Tests enemy goal initial--------");
		Enemy e1 = new Enemy(dungeon, 0,1);
		Enemy e2 = new Enemy(dungeon, 0,2);
		Enemy e3 = new Enemy(dungeon, 0,3);
		
		dungeon.addEntity(e1);
		dungeon.addEntity(e2);
		dungeon.addEntity(e3);
		
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("enemies", dungeon);
		
		Goal g = gameState.getGoal();
		
		assertTrue(g instanceof EnemyGoal);
		EnemyGoal eg = (EnemyGoal) g;
		assertTrue(eg.getDefeated() == 0);
		assertTrue(eg.getTotalEnemies() == 3);
		System.out.println("Passed");
	}
	
	@Test
	void testEnemyGoalSuccessCompleteOneEnemy() {
		System.out.println("------ Tests enemy goal interaction success --------");
		Enemy e1 = new Enemy(dungeon, 0,1);
		
		dungeon.addEntity(e1);
		
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("enemies", dungeon);
		assertFalse(gameState.checkGameComplete());
		
		Goal g = gameState.getGoal();
		assertTrue(g instanceof EnemyGoal);
		EnemyGoal eg = (EnemyGoal) g;
		assertTrue(eg.getDefeated() == 0);
		assertTrue(eg.getTotalEnemies() == 1);
		assertFalse(eg.isComplete());

		player.moveDown();
		player.moveRight();
		assertTrue(eg.getDefeated() == 0);
		assertTrue(eg.getTotalEnemies() == 1);
		assertFalse(gameState.checkGameComplete());
		assertFalse(gameState.isGameInProgress());
		System.out.println("Passed");
	}

	@Test
	void testEnemyGoalSuccessCompleteInteractionOneEnemy() {
		System.out.println("------ Tests enemy goal interaction success --------");
		Enemy e1 = new Enemy(dungeon, 0,3);
		
		dungeon.addEntity(e1);
		
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("enemies", dungeon);
		assertFalse(gameState.checkGameComplete());
		
		Goal g = gameState.getGoal();
		assertTrue(g instanceof EnemyGoal);
		EnemyGoal eg = (EnemyGoal) g;
		assertTrue(eg.getDefeated() == 0);
		assertTrue(eg.getTotalEnemies() == 1);
		assertFalse(eg.isComplete());

		Sword sw = new Sword(0, 1);
		dungeon.addEntity(sw);
		
		player.moveDown();
		player.moveDown();
		player.moveDown();
		assertTrue(eg.getDefeated() == 1);
		assertTrue(eg.getTotalEnemies() == 1);
		assertTrue(gameState.checkGameComplete());
		System.out.println("Passed");
	}
	
	@Test
	void testEnemyGoalSuccessCompleteInteractionThreeEnemy() {
		System.out.println("------ Tests enemy goal interaction three enemies success --------");

		Enemy e1 = new Enemy(dungeon, 0,2);
		
		dungeon.addEntity(e1);
		
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("enemies", dungeon);
		assertFalse(gameState.checkGameComplete());
		
		Goal g = gameState.getGoal();
		assertTrue(g instanceof EnemyGoal);
		EnemyGoal eg = (EnemyGoal) g;
		assertTrue(eg.getDefeated() == 0);
		assertTrue(eg.getTotalEnemies() == 1);
		assertFalse(eg.isComplete());

		Sword sw = new Sword(0, 1);
		dungeon.addEntity(sw);
		player.moveDown();
		System.out.println(player.getX() + " " + player.getY());
		player.moveDown();

		assertTrue(eg.getDefeated() == 1);
		assertTrue(eg.getTotalEnemies() == 1);
		assertTrue(gameState.checkGameComplete());
		assertFalse(gameState.isGameInProgress());
		System.out.println("Passed");

	}
}

