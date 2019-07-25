package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

public class MenuController {
	
	private DungeonScreen dungeonScreen;
	private MenuScreen menuScreen;
	private WinScreen winScreen;
	private LostScreen lostScreen;
	
	@FXML
	private Button StartButton;
	
	@FXML
	private Button ExitButton;

	
	public void setMenuScreen(MenuScreen ms) {
		this.menuScreen = ms;
	}
	
	@FXML
	private void handleStartButton(ActionEvent event) throws IOException {
		dungeonScreen.start();
	    dungeonScreen.getDungeonController().setMenuScreen(this.menuScreen);
		dungeonScreen.getDungeonController().setWinScreen(winScreen);
		dungeonScreen.getDungeonController().setLostScreen(lostScreen);

	}
	
	@FXML
	private void handleExitButton(ActionEvent event) {
		System.exit(0);
	}

	public void setDungeonScreen(DungeonScreen dungeonScreen) {
		this.dungeonScreen = dungeonScreen;
	}
	
	public void setWinScreen(WinScreen winScreen) {
		this.winScreen = winScreen;
	}
	
	 @FXML
	 public void handleKeyPress(KeyEvent event) {
	 	System.out.println("Do stuff");
	 }

	public void setLostScreen(LostScreen lostScreen) {
		this.lostScreen = lostScreen;
	}

}
