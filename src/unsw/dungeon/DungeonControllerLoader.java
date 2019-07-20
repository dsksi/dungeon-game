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
    private Image mageImage;
    private Image playerSwordImage;
    private Image wallImage;
    private Image bombImage;
    private Image explodeImage;
    private Image enemyImage;
    private Image exitImage;
    private Image switchImage;
    private Image treasureImage;
    private Image boulderImage;
    private Image swordImage;
    private Image invImage;
    private Image playerBombImage;
    private Image closedImage;
    private Image openImage;
    private Image keyImage;


    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/player.png");
        wallImage = new Image("/brick_brown_0.png");
        bombImage = new Image("/bomb_unlit.png");
        explodeImage = new Image("bomb_lit_4.png");
        playerSwordImage = new Image("/playerSword.png");
        enemyImage = new Image("/hound.png");
        exitImage = new Image("/exit.png");
        switchImage = new Image("/pressure_plate.png");
        treasureImage = new Image("/gold_pile.png");
        boulderImage = new Image("/boulder.png");
        swordImage = new Image("/greatsword_1_new.png");
        invImage = new Image("brilliant_blue_new.png");
        mageImage = new Image("/gnome.png");
        playerBombImage = new Image("/playerBomb.png");
        closedImage = new Image("/closed_door.png");
        openImage = new Image("/open_door.png");
        keyImage = new Image("key.png");
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
    public void onLoad(Switch gameSwitch) {
        ImageView view = new ImageView(switchImage);
        addEntity(gameSwitch, view);
    }

    @Override
    public void onLoad(InvinciblePotion inv) {
    	ImageView view = new ImageView(invImage);
    	addEntity(inv, view);
    }
    
    @Override
    public void onLoad(Bomb bomb) {
    	ImageView view = new ImageView(bombImage);
    	addBomb(bomb, view);
	}
    
    @Override
    public void onLoad(Door door) {
    	ImageView view = new ImageView(closedImage);
    	addDoor(door, view);
    }

    @Override
    public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
    	addEntity(key, view);
    }

    private void addPlayer(Entity player, ImageView view) {
    	trackPlayer(player, view);
    	trackPosition(player, view);
    	entities.add(view);
    }
    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(0, view);
    }
    private void addDoor(Entity door, ImageView view) {
    	trackDoor(door, view);
    	entities.add(0, view);
    }
    private void addBomb(Entity bomb, ImageView view) {
    	trackBomb(bomb, view);
    	entities.add(0, view);
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
            	if(newValue.equals(0)) {
            		node.setVisible(true);
            	} else if (newValue.equals(1)) {
            		node.setVisible(false);
            	}
            }
        });
    }
    
    private void trackPlayer(Entity entity, Node node) {
    	Player player = (Player) entity;

        player.visualStatus().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
            	System.out.println("manipulate player image");
            	ImageView view = (ImageView) node;
            	if(newValue.equals(0)) {
                	System.out.println("no sword image");
                	view.setImage(playerImage);
            	} else if(newValue.equals(1)) {
            		System.out.println("sword image");
            		view.setImage(playerSwordImage);
            	} else if(newValue.equals(2)) {
            		System.out.println("mage image");
            		view.setImage(mageImage);
            	} else if(newValue.equals(3)) {
            		System.out.println("player with bomb image");
            		view.setImage(playerBombImage);
            	}
            }
        });
    }
    
    private void trackDoor(Entity entity, Node node) {
    	Door door = (Door) entity;
    	GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        
        door.locked().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				ImageView view = (ImageView) node;
				if (newValue.equals(true)) {
					view.setImage(closedImage);
				} else {
					view.setImage(openImage);
				}
			}
        });
    }
    
    private void trackBomb(Entity entity, Node node) {
        
    	if(entity instanceof Bomb) {
    		Bomb bomb = (Bomb) entity;
    		GridPane.setColumnIndex(node, entity.getX());
            GridPane.setRowIndex(node, entity.getY());
            bomb.x().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                        Number oldValue, Number newValue) {
                    GridPane.setColumnIndex(node, newValue.intValue());
                }
            });
            bomb.y().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                        Number oldValue, Number newValue) {
                    GridPane.setRowIndex(node, newValue.intValue());
                }
            });
            bomb.getVisualStatus().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable,
                        Number oldValue, Number newValue) {
                	if(newValue.equals(0)) {
                		node.setVisible(true);
                	} else if (newValue.equals(1)) {
                		node.setVisible(false);
                	} else if (newValue.equals(2)) {
                		ImageView view = (ImageView) node;
                		view.setImage(explodeImage);
                	}
                }
            });
    	}
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
