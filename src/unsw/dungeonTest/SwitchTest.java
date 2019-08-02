package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;

class SwitchTest {

	Dungeon dungeon;
	
	@BeforeEach
	void Initialize() {
		dungeon = new Dungeon(10, 10);
	}
	
	@Test
	void testActivateSwitch() {
		System.out.println("----- testActivateSwitch -----");
		Switch switches = new Switch(0,0);
		dungeon.addEntity(switches);
		assertFalse(switches.getActivated(), "Switch is activated");
		switches.activateSwitch();
		assertTrue(switches.getActivated(), "Switch not activated");
		System.out.println("Passed");
	}
	
	@Test
	void testDeactivateSwitch() {
		System.out.println("----- testdeactivateSwitch -----");
		Switch switches = new Switch(0,0);
		dungeon.addEntity(switches);
		assertFalse(switches.getActivated(), "Switch is activated");
		switches.deactivateSwitch();
		assertFalse(switches.getActivated(), "Switch not activated");
		System.out.println("Passed");
	}
	
	@Test
	void testBoulderInteractSwitch() {
		System.out.println("----- testBoulderInteractSwitch -----");
		Switch switch1 = new Switch(1,0);
		Boulder boulder1 = new Boulder(dungeon,1,0);
		dungeon.addEntity(switch1);
		dungeon.addEntity(boulder1);
		switch1.interact(boulder1);
		assertTrue(switch1.getActivated(), "Switch not activated");
		System.out.println("Passed");
	}
	
	@Test
	void testPlayerInteractSwitch() {
		System.out.println("----- testPlayerInteractSwitch -----");
		Switch switches = new Switch(1,0);
		Boulder boulder = new Boulder(dungeon,1,0);
		Player player = new Player(dungeon, 0, 0);
		dungeon.addEntity(switches);
		dungeon.addEntity(boulder);
		dungeon.addEntity(player);
		
		switches.interact(boulder);
		assertTrue(switches.getActivated(), "Switch not activated");
		switches.interact(player);
		assertFalse(switches.getActivated(), "Switch is activated");
		System.out.println("Passed");
	}
}
