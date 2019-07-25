package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class FuckHimUp implements ChaseBehavior {
		
	Timer timer;
	
	public FuckHimUp() {
		this.timer = new Timer();
	}

	@Override
	public void start(Enemy enemy) {
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {	
				if (!enemy.getGameInProgress()) stop();
				if (enemy.isAlive()) {
					ArrayList<Node> pathList = playerPath(enemy);
					if (pathList.size() == 0) {
						enemy.setChaseBehavior(new Wander());
						stop();
						enemy.start();
					} else if (enemy.getPlyIsInvincible()) {
						enemy.setChaseBehavior(new RunAway());
						stop();
						enemy.start();
					}
					enemy.followPath(pathList);
				}
			}
		}, 0, 50);		
	}

	@Override
	public void stop() {
		timer.cancel();		
	}
	
	private ArrayList<Node> playerPath(Enemy enemy) {
		return enemy.findPathTo(enemy.getPlayerX(), enemy.getPlayerY());
	}

}


