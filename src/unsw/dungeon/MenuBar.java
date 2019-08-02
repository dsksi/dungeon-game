package unsw.dungeon;

import java.util.ArrayList;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class MenuBar extends VBox {

	public MenuBar(ArrayList<MenuItem> items) {
		this.getChildren().add(getLine());
		for(MenuItem name : items) {
			this.getChildren().addAll(name, getLine());
		}
		
	}
	
	private Line getLine() {
		Line line = new Line();
		line.setEndX(150);
		line.setStroke(Color.DARKGRAY);
		return line;
	}
	
}
