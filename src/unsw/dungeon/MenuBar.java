package unsw.dungeon;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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
