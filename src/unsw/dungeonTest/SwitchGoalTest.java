package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.GameState;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;
import unsw.dungeon.SwitchGoal;


class SwitchGoalTest {
	Dungeon dungeon;
	Player player;
	
	@BeforeEach
	void setUp() {
		dungeon = new Dungeon(10, 10);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
	}
	
	@Test
	void testSuccessSwitchGoalNoSwitch() {
		SwitchGoal sg = new SwitchGoal(0);
		assertTrue(sg.isComplete());
		assertTrue(sg.getSwitchActivated() == 0);
		assertTrue(sg.getTotalSwitches() == 0);
	}
	
	@Test
	void testSuccessSwitchGoalWithSwitch() {
		SwitchGoal sg = new SwitchGoal(1);
		assertFalse(sg.isComplete());
		assertTrue(sg.getSwitchActivated() == 0);
		assertTrue(sg.getTotalSwitches() == 1);
		
		Switch s1 = new Switch(0, 1);
		s1.registerObserver(sg);
		assertFalse(s1.getActivated());
		
		s1.activateSwitch();
		assertTrue(s1.getActivated());
		assertTrue(sg.isComplete());
		
		assertTrue(sg.getSwitchActivated() == 1);
		
		s1.deactivateSwitch();
		assertFalse(s1.getActivated());
		assertFalse(sg.isComplete());
		assertTrue(sg.getSwitchActivated() == 0);
	}
	
	@Test
	void testSuccessSwitchGoalMultipleActivation() {
		SwitchGoal sg = new SwitchGoal(2);
		assertFalse(sg.isComplete());
		assertTrue(sg.getSwitchActivated() == 0);
		assertTrue(sg.getTotalSwitches() == 2);
		
		Switch s1 = new Switch(0, 1);
		s1.registerObserver(sg);
		s1.activateSwitch();
		s1.activateSwitch();
		assertTrue(sg.getSwitchActivated() == 1);
		
		s1.deactivateSwitch();
		assertFalse(s1.getActivated());
		assertFalse(sg.isComplete());
		assertTrue(sg.getSwitchActivated() == 0);
	}
	
	@Test
	void testSuccessSwitchGoalCreation() {
		Switch s1 = new Switch(0,1);
		dungeon.addEntity(s1);
		Switch s2 = new Switch(0,2);
		dungeon.addEntity(s2);
		Switch s3 = new Switch(0,3);
		dungeon.addEntity(s3);
		
		GameState gs = dungeon.getGameState();
		gs.addSimpleGoal("boulders", dungeon);
		Goal g = gs.getGoal();
		assertTrue(g instanceof SwitchGoal);
		SwitchGoal sg = (SwitchGoal) g;
		System.out.println(sg.getSwitchActivated());
		assertTrue(sg.getTotalSwitches() == 3);
	}
	
	@Test
	void testSuccessSwitchGoalCreationGameStateActivation() {
		Switch s1 = new Switch(0,1);
		dungeon.addEntity(s1);
		Switch s2 = new Switch(0,2);
		dungeon.addEntity(s2);
		Switch s3 = new Switch(0,3);
		dungeon.addEntity(s3);
		
		GameState gs = dungeon.getGameState();
		gs.addSimpleGoal("boulders", dungeon);
		Goal g = gs.getGoal();
		
		assertTrue(g instanceof SwitchGoal);
		SwitchGoal sg = (SwitchGoal) g;
		System.out.println(sg.getSwitchActivated());
		assertTrue(sg.getTotalSwitches() == 3);
		
		s1.activateSwitch();
		assertFalse(sg.isComplete());
		assertTrue(sg.getSwitchActivated() == 1);
		s2.activateSwitch();
		assertFalse(sg.isComplete());
		assertTrue(sg.getSwitchActivated() == 2);
		
		s2.deactivateSwitch();
		assertFalse(sg.isComplete());
		assertTrue(sg.getSwitchActivated() == 1);
		
		s2.activateSwitch();
		assertTrue(sg.getSwitchActivated() == 2);
		assertFalse(sg.isComplete());
		s3.activateSwitch();
		System.out.println("activated " + sg.getSwitchActivated());
		assertTrue(sg.getSwitchActivated() == 3);
		assertTrue(sg.isComplete());
	}
}