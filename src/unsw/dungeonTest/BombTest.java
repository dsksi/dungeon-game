package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class BombTest {

	Dungeon dungeon;
	Player player;
	Bomb bomb;
	@BeforeEach
	void setUp() throws Exception {
		dungeon = new Dungeon(20, 18);
		player = new Player(dungeon, 1, 1);
		
		dungeon.setPlayer(player);
		bomb = new Bomb(1, 2);
		
		GameState gameState = dungeon.getGameState();
		Exit exit = new Exit(19, 17);
		dungeon.addEntity(exit);
		gameState.addSimpleGoal("exit", dungeon);
		
		dungeon.addEntity(player);
		dungeon.addEntity(bomb);
	}

	@Test
	void BombShouldBePickedUp() {
		assertNull(player.getBomb());
		assertTrue(bomb.status().get() == 0);
		player.moveDown();
		assertTrue(player.getBomb() == bomb);
		assertTrue(bomb.status().get() == 1 );
		System.out.println("Passed");
	}
	
	@Test
	void BombShouldExplode() {
		player.moveDown();
		player.dropBomb();
		player.moveDown();
		player.moveDown();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(bomb.status().get() == 1);
		System.out.println("Passed");
	}
	
	@Test
	void BombShouldKillEntitiesSurroundingIt() {
		Player player1 = new Player(dungeon, 5, 6);
		Player player2 = new Player(dungeon, 6, 5);
		Player player3 = new Player(dungeon, 6, 6);
		Player player4 = new Player(dungeon, 6, 7);
		Player player5 = new Player(dungeon, 7, 6);
		
		dungeon.addEntity(player1);
		dungeon.addEntity(player2);
		dungeon.addEntity(player3);
		dungeon.addEntity(player4);
		dungeon.addEntity(player5);		
		
		assertNull(player.getBomb());
		player.moveDown();
		assertTrue(player.getBomb() == bomb);
		
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		
		assertTrue((player.getX() == 6) && (player.getY() == 6));
		player.dropBomb();
				
		player.moveLeft();
		player.moveLeft();
		player.moveLeft();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue((player1.status().get() == 1) &&
				(player2.status().get() == 1) &&
				(player3.status().get() == 1) &&
				(player4.status().get() == 1) &&
				(player5.status().get() == 1));
		System.out.println("Passed");
	}
	
	@Test
	void BombShouldNotDestroyWall() {
		
		Wall wall = new Wall(1, 3);
		dungeon.addEntity(wall);
		
		player.moveDown();
		player.dropBomb();
		player.moveRight();
		player.moveRight();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(dungeon.getEntity(1, 3).contains(wall));
		System.out.println("Passed");
	}
	
	@Test
	void BombShouldNotDestroyDoorAndKey() {
		Door door = new Door(1, 3, 1);
		Key key = new Key(2, 3, 1);
		
		dungeon.addEntity(door);
		dungeon.addEntity(key);
		
		player.moveDown();
		player.dropBomb();
		player.moveRight();
		player.moveRight();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(dungeon.getEntity(1, 3).contains(door));
		assertTrue(dungeon.getEntity(2, 3).contains(key));
		System.out.println("Passed");
		
	}
	
	@Test
	void BombShouldNotDestroyBombSwordPotion() {
		Sword sword = new Sword(1, 3);
		Bomb newBomb = new Bomb(2, 3);
		InvinciblePotion potion = new InvinciblePotion(3,3);
		
		dungeon.addEntity(sword);
		dungeon.addEntity(newBomb);
		dungeon.addEntity(potion);
		
		player.moveDown();
		player.dropBomb();
		player.moveRight();
		player.moveRight();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(dungeon.getEntity(1, 3).contains(sword));
		assertTrue(dungeon.getEntity(2, 3).contains(newBomb));
		assertTrue(dungeon.getEntity(3, 3).contains(potion));
		System.out.println("Passed");
		
	}
	
	@Test
	void BombShouldNotDestroySwitch() {
		Switch switch1 = new Switch(1, 3);
		dungeon.addEntity(switch1);
		
		player.moveDown();
		player.dropBomb();
		player.moveRight();
		player.moveRight();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(dungeon.getEntity(1, 3).contains(switch1));
		System.out.println("Passed");
	}
	
	@Test
	void BombShouldNotDestroyExit() {
		Exit exit = new Exit(1, 3);
		dungeon.addEntity(exit);
		
		player.moveDown();
		player.dropBomb();
		player.moveRight();
		player.moveRight();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(dungeon.getEntity(1, 3).contains(exit));
		System.out.println("Passed");
	}
	
	@Test
	void BombShouldKillEnemy() {
		Enemy enemy = new Enemy (dungeon, 1, 3);
		dungeon.addEntity(enemy);
		
		player.moveDown();
		player.dropBomb();
		player.moveRight();
		player.moveRight();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(enemy.status().get() == 1);
		assertFalse(dungeon.getEntity(1, 3).contains(enemy));
		System.out.println("Passed");
	}
	
	@Test
	void BombShouldDestroyBoulder() {
		Boulder boulder = new Boulder(dungeon, 1, 3);
		dungeon.addEntity(boulder);
		
		player.moveDown();
		player.dropBomb();
		player.moveRight();
		player.moveRight();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(boulder.status().get() == 1);
		assertFalse(dungeon.getEntity(1, 3).contains(boulder));
		System.out.println("Passed");
		
	}
	
	@Test
	void BombShouldNotActivateInSpawnedState() {
		bomb.activate(player);
				
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertTrue(bomb.status().get() == 0);
		System.out.println("Passed");
	}
	
	
}
