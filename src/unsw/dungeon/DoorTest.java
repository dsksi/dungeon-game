package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		
		dungeon.addEntity(player);
		dungeon.addEntity(key);
		dungeon.addEntity(door);
		dungeon.setPlayer(player);
	}

	@Test
	void PlayerShouldNotOpenDoorWithoutKey() {
		player.moveDown();
		assert((player.getX() == 1) && (player.getY() == 2));
		assert(!door.movable(player));
		player.moveDown();
		assert((player.getX() == 1) && (player.getY() == 2));
	}
	
	@Test
	void PlayerShouldOpenDoorWithCorrectKey() {
		player.moveDown();
		player.moveRight();
		player.moveLeft();
		player.moveDown();
		player.moveDown();
		assert((player.getX() == 1) && (player.getY() == 3));
		player.moveDown();
		assert((player.getX() == 1) && (player.getY() == 4));
	}
	
	@Test
	void PlayerShouldNotOpeDoorWithWrongKey() {
		key = new Key(2, 2, 2);
		player.moveDown();
		player.moveRight();
		player.moveLeft();
		player.moveDown();
		assert((player.getX() == 1) && (player.getY() == 2));
	}
}
