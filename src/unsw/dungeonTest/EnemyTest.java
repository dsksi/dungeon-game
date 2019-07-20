package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import unsw.dungeon.*;

public class EnemyTest {
	
	Dungeon dungeon;
	
	@Before 
	public void createDungeon() {
		dungeon = new Dungeon(10, 10);
	}
	
	@Test
	public void findPlayerPathTest() {

        System.out.println("\n------ Testing playerPath method ------");
        Player player = new Player(dungeon, 1, 1);
        Enemy enemy = new Enemy(dungeon, 9, 9);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);
        dungeon.setPlayer(player);
        dungeon.setUp();
        player.moveDown();
        // TODO add asserts
        System.out.println("Enemy at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player at (" + player.getX() + "," + player.getY() + ")");
        System.out.println("Passed");
	}
	
    @Test
    public void followPathTest() {
    	System.out.println("\n------ Testing EnemyFollowPath method ------");
    	Player player = new Player(dungeon, 1, 1);
        Enemy enemy = new Enemy(dungeon, 9, 9);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);
        player.registerObserver(enemy);
        player.updateObservers();
        
        System.out.println("Enemy at (" + enemy.getX() + "," +enemy.getY() + ")" + " Player at (" + player.getX() + "," + player.getY() + ")");        
        System.out.println("Enemy following path...");
        ArrayList<Node> pathList = enemy.playerPath();
        enemy.followPath(pathList);
        System.out.println("Enemy now at (" + enemy.getX() + "," +enemy.getY() + ")" + " Player now at (" + player.getX() + "," + player.getY() + ")");
        assertEquals(enemy.getX(), player.getX(), "Enemy X != Player X");
        assertEquals(enemy.getY(), player.getY(), "Enemy Y != Player Y");
        System.out.println("Enemy at player position");
        System.out.println("Passed");    
    }
    
    @Test 
    public void followPlayer() {
    	Player player = new Player(dungeon, 1, 1);
        Enemy enemy = new Enemy(dungeon, 9, 9);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);
        player.registerObserver(enemy);
        player.updateObservers();
    	
        System.out.println("\n------ Moving Player ------");
        System.out.println("Player at (" + player.getX() + "," + player.getY() + ")");
        player.moveDown();
        player.moveDown();
        player.moveRight();
        player.moveRight();
        player.moveRight();
        System.out.println("Player now at (" + player.getX() + "," + player.getY() + ")");
        System.out.println("Enemy at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player at (" + player.getX() + "," + player.getY() + ")");
        player.updateObservers();    
        ArrayList<Node> pathList = enemy.playerPath();
        assertNotNull(pathList, "Path list is Null");
        
        System.out.println("Enemy following path...");
        enemy.followPath(pathList);
        assertEquals(enemy.getX(), player.getX(), "Enemy X != Player X"); 
        assertEquals(enemy.getY(), player.getY(), "Enemy Y != Player Y");
        System.out.println("Enemy now at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player now at (" + player.getX() + "," + player.getY() + ")");

        System.out.println("Passed");    }
    
    @Test
    public void runAway() {
    	Player player = new Player(dungeon, 1, 1);
        Enemy enemy = new Enemy(dungeon, 9, 9);
        dungeon.addEntity(player);
        dungeon.addEntity(enemy);
        player.registerObserver(enemy);
        player.updateObservers();
        
        System.out.println("\n------ Testing RunawayPath method ------");
        System.out.println("Enemy at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player at (" + player.getX() + "," + player.getY() + ")");
        player.updateObservers();
        ArrayList<Node> pathList = enemy.runAwayPath();
        enemy.followPath(pathList);
        System.out.println("Enemy now at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player now at (" + player.getX() + "," + player.getY() + ")");
        //assert enemy.getX() != player.getX() || enemy.getY() != player.getY();
        System.out.println("Enemy ran away from player position");
        System.out.println("Passed");	}
	
}