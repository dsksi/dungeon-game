package unsw.dungeon;

public class SwitchGoal implements Goal, Observer {

	private int totalSwitches;
	private int switchActivated;
	
	public SwitchGoal(int count) {
		this.totalSwitches = count;
		this.switchActivated = 0;
	}
	
	
	public int getTotalSwitches() {
		return totalSwitches;
	}


	public int getSwitchActivated() {
		return switchActivated;
	}


	@Override
	public boolean isComplete() {
		if(this.totalSwitches == this.switchActivated) {
			return true;
		}
		return false;
	}

	@Override
	public void update(Subject obj) {
		if(!(obj instanceof Switch)) return;
		Switch sw = (Switch) obj;
		if(sw.getActivated()) {
			this.switchActivated += 1;
		} else {
			this.switchActivated -= 1;
		}
	}

}
