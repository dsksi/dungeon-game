package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }
    
	@Override
	public boolean movable() {
		System.out.println("wall can not move");
		return false;
	}
}
