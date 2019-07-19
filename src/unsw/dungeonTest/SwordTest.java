package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class SwordTest {

	Dungeon dungeon;
	Player player;
	Enemy enemy;
	Sword sw1;
	Sword sw2;
	Sword sw3;
	
	@BeforeEach
	void setUp() throws Exception {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		
		enemy = new Enemy(dungeon, 1, 1);
		
		sw1 = new Sword(0, 1);
		sw2 = new Sword(0, 2);
		sw3 = new Sword(0, 3);
		dungeon.addEntity(sw1);
		dungeon.addEntity(sw2);
		dungeon.addEntity(sw3);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
