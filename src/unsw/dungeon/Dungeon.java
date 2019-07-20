/** ==
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private GameState gameState;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.gameState = new GameState();
        this.player = null;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        trackGameState(player);
        trackEntityStatus(player);
    }

    public void addEntity(Entity entity) {
    	trackEntityStatus(entity);
        entities.add(entity);
    }
    
    public void trackGameState(Player player) {
    	player.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (gameState.checkGameComplete()) {
                	gameWon();
                }
            }
        });
        player.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if(gameState.checkGameComplete()) {
                	gameWon();
                }
            }
        });
    }
    
    public void trackEntityStatus(Entity obj) {
    	obj.status().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                if (obj instanceof Player) {
                	gameLost();
                	return;
                }
                removeEntity(obj);
            }
        });
    }
    
    private void gameWon() {
    	if (gameState.isGameInProgress()) {
    		gameState.gameEnded();
    		System.out.println("You completed all goals: You have WON!");
    	}
	}
    
    private void gameLost() {
    	if (gameState.isGameInProgress()) {
    		gameState.gameEnded();
    		System.out.println("You died. Game lost!");
    	}
	}

	public ArrayList<Entity> getEntity(int x, int y) {
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	for (Entity e: entities) {
    		if (e == null) continue;
    		if(e.getX() == x && e.getY() == y) {
    			list.add(e);
    		}
    	}
    	return list;
    }
    
    public void interactItems(Entity obj, int x, int y) {
    	ArrayList<Entity> list = new ArrayList<Entity>();
    	list = this.getEntity(x, y);
    	for (Entity e : list) {
    		e.interact(obj);
    	}
    }


	public int getTreasureCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Treasure) {
				count++;
			}
		}
		System.out.println("treasure = " + count);
		return count;
	}
	
	public Exit getExit() {
		for(Entity e : this.entities) {
			if (e instanceof Exit) {
				return (Exit) e;
			}
		}
		return null;
	}
	
	public Enemy getEnemy() {
		for(Entity e : this.entities) {
			if (e instanceof Enemy) {
				return (Enemy) e;
			}
		}
		return null;
	}

	public int getEnemyCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Enemy) {
				count++;
			}
		}
		return count;
	}

	public int getSwitchCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Switch) {
				count++;
			}
		}
		return count;
	}

	public void removeEntity(Entity obj) {
		if(entities.contains(obj))
			entities.remove(obj);
	}

	public GameState getGameState() {
		return this.gameState;
	}

	public void setUpObserverEnemyGoal(EnemyGoal goal) {
		for (Entity e : entities) {
			if (e instanceof Enemy) {
				Enemy enemy = (Enemy) e;
				enemy.registerObserver(goal);
			}
		}
		
	}
	
	public void setUpObserverSwitchGoal(SwitchGoal goal) {
		for (Entity e : entities) {
			if (e instanceof Switch) {
				Switch sw = (Switch) e;
				sw.registerObserver(goal);
			}
		}
	}
	
	public void setUpObserverTreasureGoal(TreasureGoal goal) {
		player.registerObserver(goal);
	}
	
	public void setUp() {
		for (Entity e : entities) {
			if (e instanceof Enemy) {
				Enemy enemy = (Enemy) e;
				player.registerObserver(enemy);
			}
		}
	}

	public boolean completedNonExitGoals() {
		return gameState.completedNonExitGoals();
	}

	public boolean getGameInProgress() {
		return this.gameState.isGameInProgress();
	}
}
