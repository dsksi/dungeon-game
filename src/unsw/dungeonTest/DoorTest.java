package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Exit;
import unsw.dungeon.GameState;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class DoorTest {
	
	Dungeon dungeon;
	Player player;
	Key key;
	Door door;

	@BeforeEach
	void setUp() throws Exception {
		dungeon = new Dungeon(20, 18);
		player = new Player(dungeon, 1, 1);
		key = new Key(2, 2, 1);
		door = new Door(1, 3, 1);
		dungeon.setPlayer(player);
		
		GameState gameState = dungeon.getGameState();
		Exit exit = new Exit(19, 17);
		dungeon.addEntity(exit);
		gameState.addSimpleGoal("exit", dungeon);
		
		dungeon.addEntity(player);
		dungeon.addEntity(key);
		dungeon.addEntity(door);
	}

	@Test
	void PlayerShouldNotOpenDoorWithoutKey() {
		player.moveDown();
		assertTrue((player.getX() == 1) && (player.getY() == 2));
		assertTrue(!door.movable(player));
		player.moveDown();
		assertTrue((player.getX() == 1) && (player.getY() == 2));
	}
	
	@Test
	void PlayerShouldOpenDoorWithCorrectKey() {
		player.moveDown();
		player.moveRight();
		assertTrue(key.status().get() == 1);
		player.moveLeft();
		player.moveDown();
		player.moveDown();
		assertTrue((player.getX() == 1) && (player.getY() == 3));
		player.moveDown();
		assertTrue((player.getX() == 1) && (player.getY() == 4));
	}
	
	@Test
	void PlayerShouldNotOpeDoorWithWrongKey() {
		key = new Key(1, 2, 2);
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		assertTrue((player.getX() == 1) && (player.getY() == 2));
		assertFalse(door.movable(player));
	}
	
	@Test
	void BoulderShouldPassThroughOpenDoor() {
		Boulder boulder = new Boulder(dungeon, 1, 5);
		dungeon.addEntity(boulder);
		
		player.moveDown();
		player.moveRight();
		assertTrue(key.status().get() == 1);
		player.moveLeft();
		player.moveDown();
		player.moveDown();
		assertTrue((player.getX() == 1) && (player.getY() == 3));
		player.moveDown();
		assertTrue((player.getX() == 1) && (player.getY() == 4));
		
		player.moveRight();
		player.moveDown();
		player.moveDown();
		player.moveLeft();
		assertTrue((player.getX() == 1) && (player.getY() == 6));
		player.moveUp();
		player.moveUp();
		player.moveUp();
		player.moveUp();
		assertTrue((player.getX() == 1) && (player.getY() == 2));
		assertTrue(dungeon.getEntity(1, 1).contains(boulder));
	}
	
	@Test
	void BoulderShouldNotPassThroughLockedDoor() {
		Boulder boulder = new Boulder(dungeon, 1, 2);
		dungeon.addEntity(boulder);
		
		player.moveDown();
		player.moveDown();
		assertTrue((player.getX() == 1) && (player.getY() == 1));
	}
	
	@Test
	void EnemyShouldNotPassThroughLockedDoor() {
		Door door2 = new Door(1, 5, 2);
		Door door3 = new Door(2, 4, 3);
		Door door4 = new Door (0, 4, 4);
		Enemy enemy = new Enemy(dungeon, 1, 4);
		
		dungeon.addEntity(door2);
		dungeon.addEntity(door3);
		dungeon.addEntity(door4);
		dungeon.addEntity(enemy);
		dungeon.setUpEnemy();
				
		assertTrue((enemy.getX() == 1) && (enemy.getY() == 4));
		player.moveDown();
		assertTrue((enemy.getX() == 1) && (enemy.getY() == 4));
		assertTrue(enemy.findPathTo(player.getX(), player.getY()).isEmpty());
	}
	
	@Test
	void EnemyShouldPassThroughOpenDoor() {
		Door door2 = new Door(1, 5, 2);
		Door door3 = new Door(2, 4, 3);
		Door door4 = new Door (0, 4, 4);
		Enemy enemy = new Enemy(dungeon, 1, 4);
		
		dungeon.addEntity(door2);
		dungeon.addEntity(door3);
		dungeon.addEntity(door4);
		dungeon.addEntity(enemy);
		dungeon.setUpEnemy();		
		
		assertTrue((enemy.getX() == 1) && (enemy.getY() == 4));
		player.moveDown();
		player.moveRight();
		assertTrue(player.getKeyID() == 1);
		player.moveLeft();
		player.moveDown();
		assertTrue(door.movable(player));
		assertTrue(door.movable(enemy));
		player.moveUp();
		assertFalse((enemy.getX() == 1) && (enemy.getY() == 4));
	}
}
