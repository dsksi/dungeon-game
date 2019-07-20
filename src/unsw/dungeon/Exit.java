package unsw.dungeon;

import java.util.ArrayList;

public class Exit extends Entity implements Subject{
	
	private ArrayList<Observer> observers;
	private boolean isActive;
	
	public Exit(int x, int y) {
		super(x,y);
		this.observers = new ArrayList<Observer>();
		this.isActive = false;
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
		if(!(obj instanceof Player)) return false;
		Player player = (Player) obj;
		if(player.completedNonExitGoals()) {
			return true;
		}
		return false;
	}

	@Override
	public void interact(Entity obj) {
		// TODO Auto-generated method stub
		
	}
}
