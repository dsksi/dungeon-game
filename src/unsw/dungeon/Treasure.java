package unsw.dungeon;

import java.util.ArrayList;

public class Treasure extends Entity implements Subject{

	private ArrayList<Observer> observers;
	private boolean isCollected;
	
	public Treasure(int x, int y) {
		super(x, y);
		this.observers = new ArrayList<Observer>();
		this.isCollected = false;
	}
	
	public void collect() {
		this.isCollected = true;
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
	
	
}
