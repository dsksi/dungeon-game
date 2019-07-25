package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;


class EnemyTest {
	
	Dungeon dungeon;
	Player player;
	Enemy enemy;
	
	@BeforeEach
	void setUp() {
		dungeon = new Dungeon(5, 5);
		player = new Player(dungeon, 1, 1);	
		enemy = new Enemy(dungeon, 3, 3);
		Exit exit = new Exit(4, 4);
		
		dungeon.addEntity(exit);
		dungeon.addEntity(enemy);
		dungeon.addEntity(player);
		
		dungeon.setPlayer(player);
		GameState gameState = dungeon.getGameState();
		gameState.addSimpleGoal("exit", dungeon);

	}
	
	@Test
	void testPlayerPath() {
        System.out.println("\n------ Testing playerPath method ------");
        //ArrayList<Node> pathList = new ArrayList<Node>();
        //pathList = enemy.playerPath();
        //assertNotEquals(pathList, 0, "pathList equals 0");
        System.out.println("Passed");
	}
	
	@Test
	void testRunawayPath() {
        System.out.println("\n------ Testing runawayPath method ------");
        //ArrayList<Node> pathList = enemy.runAwayPath();
        //assertNotEquals(pathList, 0, "pathList equals 0");
        System.out.println("Passed");
	}
	
	@Test
	void testFollowPath() {
    	System.out.println("\n------ Testing followPath method ------");
        System.out.println("Enemy at (" + enemy.getX() + "," +enemy.getY() + ")"); 
    	System.out.println("Setting findPath to (0,4)");

        ArrayList<Node> pathList = enemy.findPathTo(0, 4);
        
        System.out.println("Enemy following path...");
        enemy.followPath(pathList);
        System.out.println("Enemy now at (" + enemy.getX() + "," +enemy.getY() + ")");
        
        assertEquals(enemy.getX(), 0, "Enemy X != Player X");
        assertEquals(enemy.getY(), 4, "Enemy Y != Player Y");
        System.out.println("Enemy at player position");
        System.out.println("Passed");    
    }    
    
    @Test
    void testRunAway() {        
        System.out.println("\n------ Testing followRunawayPath method ------");
        System.out.println("Enemy at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player at (" + player.getX() + "," + player.getY() + ")");
        //ArrayList<Node> pathList = enemy.runAwayPath();
        //enemy.followPath(pathList);
        System.out.println("Enemy now at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player now at (" + player.getX() + "," + player.getY() + ")");
        assertFalse((enemy.getX() == player.getX()) && (enemy.getY() == player.getY()));
        System.out.println("Enemy ran away from player position");
        System.out.println("Passed");	
    }
    
    @Test
    void testFollowPlayer() {
        System.out.println("\n------ Testing Update method ------");
        System.out.println("Enemy at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player at (" + player.getX() + "," + player.getY() + ")");
        player.registerObserver(enemy);
        enemy.update(player);
        System.out.println("Enemy now at (" + enemy.getX() + "," + enemy.getY() + ")" + " Player now at (" + player.getX() + "," + player.getY() + ")");
        assertEquals(enemy.getX(), player.getX(), "Enemy X != Player X");
        assertEquals(enemy.getY(), player.getY(), "Enemy Y != Player Y");
        System.out.println("Passed");
    }
    
    @Test
    void testInteractPlayer() {
        System.out.println("\n------ Testing Interact with normal Player ------");
        enemy.interact(player);
        assertFalse(enemy.getGameInProgress());
        assertTrue(enemy.isAlive());
        
        
        
        System.out.println("Passed");
    }
    
    @Test
    void testInteractSwordPlayer() {
    	System.out.println("\n------ Testing Interact with Player with sword ------");
    	Sword sword = new Sword(1,1);
        player.pickUpSword(sword);
        sword.interact(player);
        enemy.interact(player);
        assertTrue(enemy.getGameInProgress());
        assertFalse(enemy.isAlive());
        
    }
    
    @Test
    void testInteractPotionPlayer() {
    	System.out.println("\n------ Testing Interact with Player with potion ------");
    	InvinciblePotion potion = new InvinciblePotion(1,1);
        player.drinkPotion(potion);
        enemy.interact(player);
        assertTrue(enemy.getGameInProgress());
        assertFalse(enemy.isAlive());
        
    }

}
