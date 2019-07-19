package unsw.dungeon;

import java.util.ArrayList;

public class Switch extends Entity implements Subject{
	
	private ArrayList<Observer> observers;
	private boolean activated;
	
	public Switch(int x, int y) {
		super(x,y);
		this.observers = new ArrayList<Observer>();
		this.activated = false;
	}
	
	public void activateSwitch() {
		if(this.activated == true) return;
		this.activated = true;
		updateObservers();
	}
	
	public void deactivateSwitch() {
		if(this.activated == false) return;
		this.activated = false;
		updateObservers();
	}
	
	public boolean getActivated() {
		return this.activated;
	}
	
	
	//------ Observer methods ------
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void updateObservers() {
		for (Observer o : observers) {
			o.update(this);
		}
	}
	
	//------ Entity methods ------
	@Override
	public boolean movable(Entity obj) {
		return true;
	}

	@Override
	public void interact(Entity obj) {
		if ((obj instanceof Boulder)) {
			activateSwitch();
			System.out.println("switch active");
		}
		
		if ((obj instanceof Player)) {
			deactivateSwitch();
			System.out.println("switch not active");
		}
	}
	
}
