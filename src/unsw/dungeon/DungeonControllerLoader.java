package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image playerSwordImage;
    private Image wallImage;
    private Image enemyImage;
    private Image exitImage;
    private Image switchImage;
    private Image treasureImage;
    private Image boulderImage;
    private Image swordImage;
    private Image invImage;
    
    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/player.png");
        wallImage = new Image("/brick_brown_0.png");
        playerSwordImage = new Image("/playerSword.png");
        enemyImage = new Image("/hound.png");
        exitImage = new Image("/exit.png");
        switchImage = new Image("/pressure_plate.png");
        treasureImage = new Image("/gold_pile.png");
        boulderImage = new Image("/boulder.png");
        swordImage = new Image("/greatsword_1_new.png");
        invImage = new Image("brilliant_blue_new.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addPlayer(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
    	ImageView view = new ImageView(enemyImage);
    	addEntity(enemy, view);
    }
    
    @Override
    public void onLoad(Switch sw) {
    	ImageView view = new ImageView(switchImage);
    	addEntity(sw, view);
    }
    
    @Override
    public void onLoad(Treasure treasure) {
    	ImageView view = new ImageView(treasureImage);
    	addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
    	ImageView view = new ImageView(boulderImage);
    	addEntity(boulder, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
    	ImageView view = new ImageView(swordImage);
    	addEntity(sword, view);
    }

    @Override
    public void onLoad(InvinciblePotion inv) {
    	ImageView view = new ImageView(invImage);
    	addEntity(inv, view);
    }

    private void addPlayer(Entity player, ImageView view) {
    	trackPlayer(player, view);
    	trackPosition(player, view);
    	entities.add(view);
    }
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        entity.status().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if(!newValue.equals(oldValue))
            		node.setVisible(false);
            }
        });
    }
    
    private void trackPlayer(Entity entity, Node node) {
    	Player player = (Player) entity;
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        
        player.sword().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	if(!newValue.equals(oldValue)) {
            		ImageView view = (ImageView) node;
            		view.setImage(playerSwordImage);
            	}
            }
        });
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }


}
