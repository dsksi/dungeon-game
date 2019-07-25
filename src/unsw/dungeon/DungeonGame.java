package unsw.dungeon;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DungeonGame extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		MenuScreen menuScreen = new MenuScreen(primaryStage);
		DungeonScreen dungeonScreen = new DungeonScreen(primaryStage);
//		WinScreen winScreen = new WinScreen(primaryStage);
		
		menuScreen.getController().setDungeonScreen(dungeonScreen);
		menuScreen.start();
		
	}

	public static void main (String[] args) {
		launch(args);
	}
	
}
