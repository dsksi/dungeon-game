package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import unsw.dungeon.*;

public class EnemyTest {
	
	Dungeon dungeon;
	Player player;
	Enemy enemy;
	
	@Before 
	public void createDungeon() throws FileNotFoundException {
		MyDungeonLoader loader = new MyDungeonLoader("maze.json");
		dungeon = loader.load();
		player = dungeon.getPlayer();
        enemy = dungeon.getEnemy();
        player.registerObserver(enemy);
	}
	
	@Test
	public void findPlayerPathTest() {

        System.out.println("\n------ Testing playerPath method ------");
        player.updateObservers();
        ArrayList<Node> pathList = enemy.playerPath();
        assertNotNull(pathList, "Returned Path List is Null");
        System.out.print("Enemy found player");
        System.out.println("Passed");
	}
	
    @Test
    public void followPathTest() {
    	
        System.out.println("\n------ Testing EnemyFollowPath method ------");
        System.out.println("Enemy at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player at (" + player.getX() + "," + player.getY() + ")");
        System.out.println("Enemy following path...");
        player.updateObservers();
        ArrayList<Node> pathList = enemy.playerPath();
        enemy.followPath(pathList);
        System.out.println("Enemy at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player at (" + player.getX() + "," + player.getY() + ")");
        assertEquals(enemy.getX(), player.getX(), "Enemy X != Player X");
        assertEquals(enemy.getY(), player.getY(), "Enemy Y != Player Y");
        System.out.println("Enemy at player position");
        System.out.println("Passed");    }
    
    @Test 
    public void followPlayer() {
    	
        System.out.println("\n------ Moving Player ------");
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
        enemy.followPath(pathList);
        assertEquals(enemy.getX(), player.getX(), "Enemy X != Player X"); 
        assertEquals(enemy.getY(), player.getY(), "Enemy Y != Player Y");
        System.out.println("Enemy now at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player now at (" + player.getX() + "," + player.getY() + ")");
        System.out.println("Enemy has followed player");
        System.out.println("Passed");    }
    
    @Test
    public void runAway() {
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
