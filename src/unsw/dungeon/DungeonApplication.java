package unsw.dungeon;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class DungeonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Dungeon");

        MenuScreen menuScreen = new MenuScreen(primaryStage);
        DungeonControllerLoader dungeonLoader = new DungeonControllerLoader("testGoal.json");


        DungeonController controller = dungeonLoader.loadController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("trial.fxml"));
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        root.requestFocus();
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        String musicFile = "sounds/intro2.mp3";
//        
//        Media introMusic = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(introMusic);
//        //mediaPlayer.play();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
