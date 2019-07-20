package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Bomb;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.GameState;
import unsw.dungeon.Player;

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
		assert(player.getBomb() == null);
		assert(bomb.status().get() == 0);
		player.moveDown();
		assert(player.getBomb() == bomb);
		assert(bomb.status().get() == 1 );
	}
	
	@Test
	void BombShouldExplode() {
		player.moveDown();
		player.dropBomb();
		player.moveDown();
		player.moveDown();
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				assert(player.getBomb() == null);
			}
		};
		
		timer.schedule(task, 3000);
	}
	
	@Test
	void BombShouldKillPlayer() {
		Player player1 = new Player(dungeon, 5, 5);
		Player player2 = new Player(dungeon, 5, 6);
		Player player3 = new Player(dungeon, 5, 7);
		Player player4 = new Player(dungeon, 6, 5);
		Player player5 = new Player(dungeon, 6, 6);
		Player player6 = new Player(dungeon, 6, 7);
		Player player7 = new Player(dungeon, 7, 5);
		Player player8 = new Player(dungeon, 7, 6);
		Player player9 = new Player(dungeon, 7, 7);
		
		dungeon.addEntity(player1);
		dungeon.addEntity(player2);
		dungeon.addEntity(player3);
		dungeon.addEntity(player4);
		dungeon.addEntity(player5);
		dungeon.addEntity(player6);
		dungeon.addEntity(player7);
		dungeon.addEntity(player8);
		dungeon.addEntity(player9);
		
		
		assert(player.getBomb() == null);
		player.moveDown();
		assert(player.getBomb() == bomb);
		
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		
		assert((player.getX() == 6) && (player.getY() == 6));
		player.dropBomb();
				
		player.moveLeft();
		player.moveLeft();
		player.moveLeft();
		
		try {
			Thread.sleep(3000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assert((player1.status().get() == 1) &&
				(player2.status().get() == 1) &&
				(player3.status().get() == 1) &&
				(player4.status().get() == 1) &&
				(player5.status().get() == 1) &&
				(player6.status().get() == 1) &&
				(player7.status().get() == 1) &&
				(player8.status().get() == 1) &&
				(player9.status().get() == 1));
	}

}
