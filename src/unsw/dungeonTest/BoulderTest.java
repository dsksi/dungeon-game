package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import unsw.dungeon.*;


class BoulderTest {
	Dungeon dungeon;
	Boulder boulder;
	
	@BeforeEach
	void createDungeon() {
		dungeon = new Dungeon(10,10);
		boulder = new Boulder(dungeon, 1, 1);
		dungeon.addEntity(boulder);
	}
	
	@Test
	void testCheckMovableBoundaries() {
		System.out.println("------- Testing movable Boundaries -------");
		
		assertTrue(boulder.checkMoveable(1, 0));
		assertTrue(boulder.checkMoveable(0, 1));
		
		assertTrue(boulder.checkMoveable(1, 2));
		assertTrue(boulder.checkMoveable(2, 1));
		System.out.println("Passed");
	}
	
	@Test
	void testCheckmoveableWall() {
		System.out.println("------- Testing boulder to wall movability ------");
		Wall wall = new Wall(1,2);
		dungeon.addEntity(wall);
		assertTrue(boulder.checkMoveable(2,1));
		assertFalse(boulder.checkMoveable(1, 2));
		System.out.println("Passed");
	}
	
	@Test
	void testCheckmoveableBB() {
		System.out.println("------- Testing boulder to boulder movability ------");
		Boulder boulder2 = new Boulder(dungeon, 1,2);
		dungeon.addEntity(boulder2);
		assertTrue(boulder.checkMoveable(2, 1));
		assertFalse(boulder.checkMoveable(1, 2));
		System.out.println("Passed");
	}
	
	@Test
	void testMovable() {
		System.out.println("------- Tesing Movable ------");
		Boulder boulder1 = new Boulder(dungeon, 1, 1);
		Player player = new Player(dungeon, 0, 1);
		dungeon.addEntity(boulder1);
		dungeon.addEntity(player);
		assertTrue(boulder1.movable(player));
		
		Boulder boulder2 = new Boulder(dungeon, 2,1);
		dungeon.addEntity(boulder2);
		assertFalse(boulder1.movable(player));

		System.out.println("Passed");
	}
	
	@Test
	void testInteract() {
		System.out.println("------- Testing Interact ------");
		System.out.println("Boulder at (" + boulder.getX() + "," + boulder.getY() + ")");
		Player player = new Player(dungeon, 0, 1);
		boulder.interact(player);
		System.out.println("Boulder now at (" + boulder.getX() + "," + boulder.getY() + ")");
		assertEquals(boulder.getX(), 2);
		assertEquals(boulder.getY(), 1);
		System.out.println("Passed");

	}
	
	@Test
	void testActivateSwitch() {
		System.out.println("------- Testing boulder activates switch ------");
		Switch switches = new Switch(2,1);
		dungeon.addEntity(switches);
		boulder.checkMoveable(2,1);
		assertTrue(switches.getActivated());
		System.out.println("Passed");
	}

}
