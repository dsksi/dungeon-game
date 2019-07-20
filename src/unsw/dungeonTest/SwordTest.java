package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class SwordTest {

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
	void testSuccessPickUpSword() {
		System.out.println("\n------ Testing player pick up sword successfully by moving to sword location ------");
		assertFalse(player.haveWeapon());
		player.moveDown();
		assertTrue(player.haveWeapon());
		ArrayList<Entity> tmpEntities = dungeon.getEntity(0, 1);
		assertTrue(tmpEntities.isEmpty());
	}
	
	@Test
	void testCanNotPickUpMultipleSword() {
		System.out.println("\n------ Testing player can not pickup more than one sword at a time ------");
		assertFalse(player.haveWeapon());
		player.moveDown();
		assertTrue(player.haveWeapon());
		ArrayList<Entity> tmpEntities = dungeon.getEntity(0, 1);
		assertTrue(tmpEntities.isEmpty());
		
		player.moveDown();
		assertTrue(player.haveWeapon());
		tmpEntities = dungeon.getEntity(0, 2);
		assertFalse(tmpEntities.isEmpty());
		assertTrue(tmpEntities.get(0) instanceof Sword );
	}
	
	@Test
	void testCanNotPickUpMultipleSwordAtSameLocation() {
		System.out.println("\n------ Testing player can not pickup more than one sword at same location------");
		assertFalse(player.haveWeapon());
		
		Sword testSword = new Sword(0, 1);
		dungeon.addEntity(testSword);
		player.moveDown();
		assertTrue(player.haveWeapon());
		
		ArrayList<Entity> tmpEntities = dungeon.getEntity(0, 1);
		assertFalse(tmpEntities.isEmpty());
		assertTrue(tmpEntities.get(0) instanceof Sword );
	}
	
	@Test
	void testEnemyCanNotPickUpSword() {
		System.out.println("\n------ Testing enemy cannot pickup sword ------");
		enemy.moveLeft();
		enemy.moveRight();
		ArrayList<Entity> tmpEntities = dungeon.getEntity(0, 1);
		assertFalse(tmpEntities.isEmpty());		
		assertTrue(tmpEntities.get(0) instanceof Sword );
	}
	
	@Test
	void testPlayerPickUpSwordAttack() {
		System.out.println("\n------ Testing player changes weapon strategy when pickup sword ------");
		assertTrue(player.getWeaponStrat() instanceof NoWeapon);
		assertFalse(player.haveWeapon());
		player.moveDown();
		assertTrue(player.getWeaponStrat() instanceof SwordAttack);
		assertTrue(player.haveWeapon());
	}

}
