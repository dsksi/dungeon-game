package unsw.dungeon;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javafx.stage.*;

public class MenuScreen {

	private Stage stage;
	private String title;
	private MenuController controller;
	private Scene scene;
	
	public MenuScreen(Stage stage) throws IOException{
		this.stage = stage;
		this.title = "Start Screen";
		this.controller = new MenuController();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
		loader.setController(this.controller);
		
		Parent root = loader.load();
		root.requestFocus();
		this.scene = new Scene(root, 600, 400);
		
	}
	
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	public MenuController getController() {
		return controller;
	}
}