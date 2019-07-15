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
		this.activated = true;
	}
	
	public void deactivateSwitch() {
		this.activated = false;
	}
	
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

	@Override
	public boolean movable() {
		return true;
	}

	public boolean getActivated() {
		return this.activated;
	}

}
