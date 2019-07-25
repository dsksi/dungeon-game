package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class MenuController {
	
	private DungeonScreen dungeonScreen;
	
	@FXML
	private Button StartButton;
	
	@FXML
	private Button ExitButton;
	
	
	@FXML
	private void handleStartButton(ActionEvent event) throws IOException {
		dungeonScreen.start();
	}
	
	@FXML
	private void handleExitButton(ActionEvent event) {
		System.exit(0);
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	 @FXML
	 public void handleKeyPress(KeyEvent event) {
	 	System.out.println("Do stuff");
	 }

}
