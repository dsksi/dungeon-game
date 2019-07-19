package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Bomb extends Entity {

	private BombState state;
	public Bomb(int x, int y) {
		super(x, y);
		this.state = new Spawned();
	}

	@Override
	public boolean movable(Entity obj) {
		return true;
	}
	
	public void interact(Entity obj) {
		if (obj instanceof Player) {
			Player player = (Player) obj;
			state = state.pickUp(this, player);
		}
	}
	
	public void activate(Player player) {
		state = state.activateBomb(this, player);
		Bomb bombRef = this;
		
		Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			public void run() {
				state.explode(bombRef, player);
			}
		};
		
		TimerTask task2 = new TimerTask() {
			public void run() {
				state.exploding(bombRef, player);
			}
		};
		
		timer.schedule(task2, 1500);
		timer.schedule(task, 2000);
		
		player.getDungeon().removeEntity(this);
	}
	
	public void exploding() {
		status().set(2);
	}

}
