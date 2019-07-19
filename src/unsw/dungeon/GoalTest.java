package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoalTest {
	
	Dungeon dungeon;
	Player player;
	
	@BeforeEach
	void setUp() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}

	
	@Test
	void testSuccessCompositeGoal(){
		fail();
	}

}
