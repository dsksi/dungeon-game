package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }
    
	@Override
	public boolean movable(Entity obj) {
		return false;
	}

	@Override
	public void interact(Entity obj) {
		return;
	}
}
