package unsw.dungeon;

import java.util.ArrayList;

public class CompositeGoal implements Goal {

	private ArrayList<Goal> goals = new ArrayList<Goal>();
	private String type;
	
	/**
	 * 
	 * @param type takes in either "AND" or "OR" to decide on isComplete evaluation
	 */
	public CompositeGoal(String type) {
		this.type = type.toUpperCase();
	}

	@Override
	public boolean isComplete() {
		if(type.equals("OR")) {
			return this.isOrComplete();
		}
		return this.isAndComplete();
	}
	
	private boolean isAndComplete() {
		if(goals.isEmpty()) return true;
		
		for(Goal g: goals) {
			if(!g.isComplete()) return false;
		}
		return true;
	}
	private boolean isOrComplete() {
		if(goals.isEmpty()) return true;
		
		for(Goal g: goals) {
			if(g.isComplete()) return true;
		}
		return false;
	}
	
	public void addGoal(Goal g) {
		goals.add(g);
	}
	
	public void removeGoal(Goal g) {
		goals.remove(g);
	}
}
