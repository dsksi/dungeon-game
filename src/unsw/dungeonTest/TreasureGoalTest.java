package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class TreasureGoalTest {
	
	Dungeon dungeon;
	Player player;
	
	@BeforeEach
	void setUp() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}

	@Test
	void testTreasureGoalSuccessInitial() {
		TreasureGoal tg = new TreasureGoal(0);
		System.out.println("Testing success at completing goal with 0 treasure");
		assertTrue(tg.isComplete());
		Treasure t1 = new Treasure(0, 1);
		assertTrue(player.getTreasureCollected() == 0);
		player.pickUpTreasure(t1);
		assertTrue(player.getTreasureCollected() == 1);
		assertTrue(tg.isComplete());
		System.out.println("Finish test 1");
	}
	
	@Test
	void testTreasureGoalSuccessComplete() {
		System.out.println("Testing success at completing goal");
		TreasureGoal tg = new TreasureGoal(1);
		assertFalse(tg.isComplete());
		Treasure t1 = new Treasure(0, 1);
		assertTrue(player.getTreasureCollected() == 0);
		player.registerObserver(tg);
		player.pickUpTreasure(t1);
		assertTrue(player.getTreasureCollected() == 1);
		assertTrue(tg.isComplete());
		System.out.println("Finish test 2");
	}
	
	@Test
	void testTreasureGoalFail() {
		System.out.println("Testing fail to pickup same treasure multiple times");
		TreasureGoal tg = new TreasureGoal(5);
		assertFalse(tg.isComplete());
		Treasure t1 = new Treasure(0, 1);
		player.pickUpTreasure(t1);
		player.pickUpTreasure(t1);
		player.pickUpTreasure(t1);
		player.pickUpTreasure(t1);
		assertTrue(player.getTreasureCollected() == 1);
		assertFalse(tg.isComplete());
		System.out.println("Finish test 3");
	}
	
	@Test
	void testTreasureGoalSuccessCreationNoTreasure() {
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("treasure", dungeon);
		Goal g = gameState.getGoal();
		assertTrue(g instanceof TreasureGoal);
		TreasureGoal tg = (TreasureGoal) g;
		assertTrue(tg.getTotalTreasure() == 0);
	}
	
	@Test
	void testTreasureGoalSuccessCreationMultipleGoals() {
		Treasure t1 = new Treasure(0,1);
		dungeon.addEntity(t1);
		Treasure t2 = new Treasure(0,2);
		dungeon.addEntity(t2);
		Treasure t3 = new Treasure(0,3);
		dungeon.addEntity(t3);
		Treasure t4 = new Treasure(0,4);
		dungeon.addEntity(t4);
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("treasure", dungeon);
		Goal g = gameState.getGoal();
		assertTrue(g instanceof TreasureGoal);
		TreasureGoal tg = (TreasureGoal) g;
		assertTrue(tg.getTotalTreasure() == 4);
	}
	
	@Test
	void testTreasureGoalSuccessCreationMultipleComplete() {
		Treasure t1 = new Treasure(0,1);
		dungeon.addEntity(t1);
		Treasure t2 = new Treasure(0,2);
		dungeon.addEntity(t2);
		Treasure t3 = new Treasure(0,3);
		dungeon.addEntity(t3);
		Treasure t4 = new Treasure(0,4);
		dungeon.addEntity(t4);
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("treasure", dungeon);
		Goal g = gameState.getGoal();
		TreasureGoal tg = (TreasureGoal) g;
		player.registerObserver(tg);
		player.pickUpTreasure(t1);
		assertTrue(t1.isCollected());
		player.pickUpTreasure(t2);
		assertTrue(t2.isCollected());
		player.pickUpTreasure(t3);
		assertTrue(t3.isCollected());
		
		assertFalse(tg.isComplete());
		assertFalse(gameState.checkGameComplete());
		
		player.pickUpTreasure(t4);
		assertTrue(t4.isCollected());
		assertTrue(tg.isComplete());
		assertTrue(gameState.checkGameComplete());
	}
	
	@Test
	void testTreasureGoalSuccessInteraction() {
		Treasure t1 = new Treasure(0,1);
		dungeon.addEntity(t1);
		Treasure t2 = new Treasure(0,2);
		dungeon.addEntity(t2);
		Treasure t3 = new Treasure(0,3);
		dungeon.addEntity(t3);
		Treasure t4 = new Treasure(0,4);
		dungeon.addEntity(t4);
		
		
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("treasure", dungeon);
		Goal g = gameState.getGoal();
		TreasureGoal tg = (TreasureGoal) g;
		player.registerObserver(tg);
		
		for(int i = 1; i < 5; i++) {
			assertFalse(tg.isComplete());
			assertFalse(gameState.checkGameComplete());
			player.moveDown();
			assertTrue(tg.getTreasureCount() == i);
		}
		assertTrue(tg.isComplete());
		assertTrue(gameState.checkGameComplete());
	}
	
	@Test
	void testTreasureGoalNoMultipleInteraction() {
		Treasure t1 = new Treasure(0,1);
		dungeon.addEntity(t1);
		
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("treasure", dungeon);
		Goal g = gameState.getGoal();
		TreasureGoal tg = (TreasureGoal) g;
		player.registerObserver(tg);
		
		assertFalse(tg.isComplete());
		assertFalse(gameState.checkGameComplete());
		player.moveDown();
		assertTrue(tg.getTreasureCount() == 1);
		player.moveUp();
		player.moveDown();
		assertTrue(tg.getTreasureCount() == 1);
		
		assertTrue(tg.isComplete());
		assertTrue(gameState.checkGameComplete());
	}

}

