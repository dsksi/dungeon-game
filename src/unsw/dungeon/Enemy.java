package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements Subject, Observer {

	private ArrayList<Observer> observers;
	private int playerXPos;
	private int playerYPos;
	
	public Enemy(int x, int y) {
		super(x, y);
		this.observers = new ArrayList<Observer>();
		this.playerXPos = 0;
		this.playerYPos = 0;
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
	
	//------ Subject methods ------
	@Override
	public void update(Subject obj) {
		if (!(obj instanceof Player)) return;
		
		Player player = (Player) obj;
		this.playerXPos = player.getX();
		this.playerYPos = player.getY();
	}
	
	//------ Entity methods ------
	@Override
	public boolean movable(Entity obj) {
		return true;
	}

	@Override
	public void interact(Entity obj) {
		if (!(obj instanceof Player)) return;
		
		Player player = (Player) obj;
		switch (player.getState()) {
		case "swordAttack": /* Do something */ break;
		case "invinsibleAttack": /* Do something */break;
		default: /* Do something */ break;
		}
	}


}