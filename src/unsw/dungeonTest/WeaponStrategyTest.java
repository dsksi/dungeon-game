package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class WeaponStrategyTest {

	Dungeon dungeon;
	Player player;
	GameState gameState;
	Enemy enemy;
	Sword sw1;
	Sword sw2;
	Sword sw3;
	
	@BeforeEach
	void setUp() throws Exception {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		gameState = dungeon.getGameState();
		enemy = new Enemy(dungeon, 1, 1);
		dungeon.addEntity(enemy);
		gameState.addSimpleGoal("enemies", dungeon);
		sw1 = new Sword(0, 1);
		sw2 = new Sword(0, 2);
		sw3 = new Sword(0, 3);
		dungeon.addEntity(sw1);
		dungeon.addEntity(sw2);
		dungeon.addEntity(sw3);
	}
	
	@Test
	void testPlayerCanNotAttack() {
		System.out.println("\n------ Testing player cannot attack when NoWeapon ------");
		assertTrue(player.getWeaponStrat() instanceof NoWeapon);
		assertFalse(player.attack(enemy));
	}
	
	@Test
	void testPlayerPickUpSwordAttack() {
		System.out.println("\n------ Testing player can attack when SwordAttack ------");
		assertTrue(player.getWeaponStrat() instanceof NoWeapon);
		player.moveDown();
		assertTrue(player.getWeaponStrat() instanceof SwordAttack);
		assertTrue(player.haveWeapon());
		assertTrue(dungeon.getEnemyCount() == 1);
		assertTrue(player.attack(enemy));
		assertTrue(dungeon.getEnemyCount() == 0);
	}

}
