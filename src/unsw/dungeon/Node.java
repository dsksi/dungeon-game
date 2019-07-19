package unsw.dungeon;

public class Node {
	private int parent_index;
	private int x;
	private int y;
	
	public Node(int index, int x, int y) {
		super();
		this.parent_index = index;
		this.x = x;
		this.y = y;
	}
	
	public int getParentIndex() {
		return this.parent_index;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}
