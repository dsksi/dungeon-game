package unsw.dungeon;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MenuItem extends StackPane {
	
	public MenuItem(String name) {
		LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
			new Stop(0.1, Color.BLUE),
			new Stop(0.2, Color.DARKBLUE),
			new Stop(0.8, Color.DARKBLUE),
			new Stop(0.9, Color.BLUE),
		});
		
		Text itemName = new Text(name);
		itemName.setFill(Color.DARKGRAY);
		itemName.setFont(Font.font("Century Gothic", FontWeight.SEMI_BOLD, 20));
		
		setAlignment(Pos.CENTER);
		Rectangle box =  new Rectangle(150, 30);
		box.setOpacity(0.35);
		
		getChildren().addAll(itemName, box);

		this.setOnMouseExited(event -> {
			box.setFill(gradient);
			itemName.setFill(Color.DARKGRAY);
		});
		
		this.setOnMouseReleased(event -> {
			box.setFill(gradient);
			itemName.setFill(Color.DARKGRAY);
		});
		
		this.setOnMouseEntered(event -> {
			box.setFill(gradient);
			itemName.setFill(Color.WHITE);
		});
		
		this.setOnMousePressed(event -> {
			box.setFill(Color.BLUE);
		});
		
		
	}
	
}