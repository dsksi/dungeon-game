package unsw.dungeon;

public class ExitGoal implements Goal {

	private Player player;
	private Exit exit;
	
	/**
	 * Create an exit goal for the dungeon game
	 * @param player of the game
	 * @param exit of the game
	 * @precondition the dungeon has exactly one exit and one player
	 */
	public ExitGoal(Player player, Exit exit) {
		this.player = player;
		this.exit = exit;
	}
	
	/**
	 * Return if game is complete
	 */
	@Override
	public boolean isComplete() {
		if(player.getX() == exit.getX() && player.getY() == exit.getY()) {
			return true;
		}
		return false;
	}

}
