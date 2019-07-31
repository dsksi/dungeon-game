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
		WinScreen winScreen = new WinScreen(primaryStage);
		LostScreen lostScreen = new LostScreen(primaryStage);
		LevelScreen levelScreen = new LevelScreen(primaryStage);
		
		menuScreen.getController().setMenuScreen(menuScreen);
		menuScreen.getController().setDungeonScreen(dungeonScreen);
		menuScreen.getController().setWinScreen(winScreen);
		menuScreen.getController().setLostScreen(lostScreen);
		menuScreen.getController().setLevelScreen(levelScreen);
		
		winScreen.getEndController().setMenuScreen(menuScreen);
		lostScreen.getEndController().setMenuScreen(menuScreen);
		
		levelScreen.getController().setMenuScreen(menuScreen);
		menuScreen.start();
		
	}

	public static void main (String[] args) {
		launch(args);
	}
	
}
