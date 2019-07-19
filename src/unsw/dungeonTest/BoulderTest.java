package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import unsw.dungeon.*;

public class BoulderTest {
	
	Dungeon dungeon;
	
	@Before
	public void createDungeon() {
		dungeon = new Dungeon(10,10);
	}
	
	@Test
	public void testCheckMovableBoundaries() {
		System.out.println("------- testCheckMovableBoundaries -------");
		Boulder boulder = new Boulder(dungeon, 0, 0);
		dungeon.addEntity(boulder);
		assertTrue(boulder.checkMoveable(1, 0));
		assertTrue(boulder.checkMoveable(0, 1));
		
		assertFalse(boulder.checkMoveable(-1, 0));
		assertFalse(boulder.checkMoveable(0, -1));
		System.out.println("Passed");
	}
	
	@Test
	public void testWallCollision() {
		System.out.println("------- testWallCollision ------");
		Boulder boulder = new Boulder(dungeon, 0,0);
		Wall wall = new Wall(0,1);
		dungeon.addEntity(boulder);
		dungeon.addEntity(wall);
		assertTrue(boulder.checkMoveable(1, 0));
		assertFalse(boulder.checkMoveable(0, 1));
		System.out.println("Passed");
	}
	
	@Test
	public void testBoulderCollision() {
		System.out.println("------- testWallCollision ------");
		Boulder boulder = new Boulder(dungeon, 0,0);
		Boulder boulder2 = new Boulder(dungeon, 1,0);
		dungeon.addEntity(boulder);
		dungeon.addEntity(boulder2);
		assertTrue(boulder.checkMoveable(0, 1));
		assertFalse(boulder.checkMoveable(1, 0));
		System.out.println("Passed");
	}
	
	@Test
	public void testBoulderMoveCollision() {
		System.out.println("------- testBoulderMoveCollision ------");
		Boulder boulder = new Boulder(dungeon, 1, 1);
		Player player = new Player(dungeon, 0, 1);
		dungeon.addEntity(boulder);
		dungeon.addEntity(player);
		
		assertTrue(boulder.movable(player));
		Boulder boulder2 = new Boulder(dungeon, 2,1);
		dungeon.addEntity(boulder2);
		assertFalse(boulder.movable(player));
		System.out.println("Passed");
	}
	
	@Test
	public void testBoulderMove() {
		// TODO 
		
	}
}
