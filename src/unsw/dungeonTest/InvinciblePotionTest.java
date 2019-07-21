package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class InvinciblePotionTest {
	Dungeon dungeon;
	Player player;
	InvinciblePotion potion;
	@BeforeEach
	void setUp() throws Exception {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		
		GameState gameState = dungeon.getGameState();
		Exit exit = new Exit(9, 9);
		dungeon.addEntity(exit);
		gameState.addSimpleGoal("exit", dungeon);

		potion = new InvinciblePotion(0, 1);
		dungeon.addEntity(potion);
	}

	@Test
	void testPlayerPickupInvinciblePotion() {
		System.out.println("-----Test Player Successfully Become Invincible for 10 seconds------");
		assertFalse(player.isInvincible());
		player.moveDown();
		assertTrue(player.isInvincible());
		
		try {
			Thread.sleep(10010);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertFalse(player.isInvincible());
		System.out.println("Passed");
	}
	
	@Test
	void testInvinciblePlayerKillEnemy() {
		System.out.println("-----Test Invincible Player Kill Enemy------");
		assertFalse(player.isInvincible());
		Enemy enemy = new Enemy(dungeon, 1, 1);
		dungeon.addEntity(enemy);
		player.moveDown();
		assertTrue(player.isInvincible());
		player.moveRight();
		player.moveLeft();
		assertTrue(dungeon.getEntity(1, 1).isEmpty());
		System.out.println("Passed");
	}

}
