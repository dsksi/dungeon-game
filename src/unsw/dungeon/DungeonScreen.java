package unsw.dungeon;


import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DungeonScreen {
	private Stage stage;
	private String title;
	private Scene scene;
	
	public DungeonScreen(Stage stage) throws IOException {
		this.stage = stage;
		this.title = "Dungeon";
		
		
	}
	
	public void start() throws IOException {
		DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testGoals.json");
		DungeonController controller = dungeonLoader.loadController();
	    
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("DungeonView.fxml"));
		loader.setController(controller);
		Parent root = loader.load();
		root.requestFocus();
		this.scene = new Scene(root);
		
		stage.setTitle(title);
	    stage.setScene(scene);
	    stage.show();
	}
}
