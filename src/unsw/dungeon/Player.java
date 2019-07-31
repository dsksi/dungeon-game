package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Subject{

    private Dungeon dungeon;
    private IntegerProperty visualStatus;
    private int keyID;
    private Bomb bomb;
    private int treasureCollected;
    private ArrayList<Observer> observers;
    private WeaponStrategy weaponStrat;
    private boolean invincible;
	private IntegerProperty prevVisualStatus;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.visualStatus = new SimpleIntegerProperty(0);
        this.weaponStrat = new NoWeapon();
        this.invincible = false;
        this.keyID = -1;
        this.bomb = null;
        this.treasureCollected = 0;
        this.observers = new ArrayList<Observer>();
        this.treasureCollected = 0;
    }

    
    /**
     * Check if the game is still in progress
     * @return
     */
    public boolean getGameInProgress() {
    	return dungeon.getGameInProgress();
    }
    
    /**
     * Given the coordinate, check if player can move onto the location
     * @param x
     * @param y
     * @return true or false
     */
    private boolean checkMoveable(int x, int y) {

    	if(!getGameInProgress()) return false;
    	if(!((y < dungeon.getHeight()) && (y >= 0)))	return false;
    	if(!((x < dungeon.getWidth()) && (x >= 0)))		return false;

    	ArrayList<Entity> list = dungeon.getEntity(x, y);
        if(!list.isEmpty()) {
        	boolean result = true;
        	for (Entity e: list) {
        		if(! e.movable(this)) return false;
            }
        	return result;
        }
        return true;
    }
    
    /**
     * Move the player up
     */
    public void moveUp() {
    	if(this.checkMoveable(this.getX(), this.getY() - 1)) {
    		dungeon.interactItems(this, this.getX(), this.getY() - 1);
    		y().set(getY() - 1);
    		updateObservers();
    	} else if (this.checkDoor(this.getX(), this.getY() - 1)){
    		dungeon.interactItems(this, this.getX(), this.getY() - 1);
    	}
    }

    /**
     * Move the player down
     */
    public void moveDown() {
    	if(this.checkMoveable(this.getX(), this.getY() + 1)) {
    		dungeon.interactItems(this, this.getX(), this.getY() + 1);
    		y().set(getY() + 1);
    		updateObservers();
    	} else if (this.checkDoor(this.getX(), this.getY() + 1)){
    		dungeon.interactItems(this, this.getX(), this.getY() + 1);
    	}
    }

    /**
     * Move the player left
     */
    public void moveLeft() {
    	if(this.checkMoveable(this.getX() - 1, this.getY())) {
    		dungeon.interactItems(this, this.getX() - 1, this.getY());
    		x().set(getX() - 1);
    		updateObservers();
    	} else if (this.checkDoor(this.getX() - 1, this.getY())){
    		dungeon.interactItems(this, this.getX() - 1, this.getY());
    	}
    }

    /**
     * Move the player right
     */
    public void moveRight() {
    	if(this.checkMoveable(this.getX() + 1, this.getY())) {
    		dungeon.interactItems(this, this.getX()+1, this.getY());
    		x().set(getX() + 1);
    		updateObservers();
    	} else if (this.checkDoor(this.getX() + 1, this.getY())){
    		dungeon.interactItems(this, this.getX()+1, this.getY());
    	}
    }

	@Override
	public boolean movable(Entity obj) {
		return true;
	}
	
	/**
	 * Get the key id of the player's key
	 * @return keyID
	 */
	public int getKeyID() {
		return keyID;
	}

	/**
	 * Set the key id when the player picks up the key
	 * @param keyID
	 */
	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}

	/**
	 * Get the bomb from the player
	 * @return bomb
	 */
	public Bomb getBomb() {
		return bomb;
	}

	/**
	 * Set the bomb of the player when player picks up a bomb
	 * @param bomb
	 */
	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}
	
	/**
	 * Allow the player to drop the bomb at the current coordinate
	 */
	public void dropBomb() {
		if (bomb != null) {
			bomb.activate(this);
		}
	}

	/**
	 * Given treasure, player picks up the treasure
	 * @param treasure
	 */
	public void pickUpTreasure(Treasure treasure) {
		if (treasure.isCollected()) return;
		System.out.println("Pick up treasure");
		treasure.collect();
		this.treasureCollected++;
		this.updateObservers();
	}
	
	/**
	 * Get the dungeon of the game
	 * @return dungeon
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	/*
	 * Get number of treasure collected
	 */
	public int getTreasureCollected() {
		return this.treasureCollected;
	}

	@Override
	public void interact(Entity obj) {
		if (! this.getGameInProgress()) return;
		if (obj instanceof Enemy) {
			Enemy enemy = (Enemy) obj;
			if(!enemy.isAlive()) return;
			if (!(attack(enemy))) {
				System.out.println("can not attack enemy");
				this.delete();
			}
		}
		return;
	}

	/**
	 * Get the current visual status of the player
	 * @return visualStatus
	 */
	public IntegerProperty visualStatus() {
		return this.visualStatus;
	}
	
	/**
	 * Remember the current visual status as previous
	 */
	public void setPrevVisualStatus() {
		this.prevVisualStatus = new SimpleIntegerProperty(this.visualStatus.intValue());
	}
	
	/**
	 * Get the previous visual status of the player
	 * @return prevVisualStatus
	 */
	public IntegerProperty getPrevVisualStatus() {
		return this.prevVisualStatus;
	}
	
	/**
	 * Set the visual status of the player
	 * @param num
	 */
	public void setVisualStatus(int num) {
		visualStatus().set(num);
	}	

	/**
	 * Check if player is invincible
	 * @return true or false
	 */
	public boolean isInvincible() {
		return this.invincible;
	}
	
	/**
	 * Check if player has weapon
	 * @return true or false
	 */
	public boolean haveWeapon() {
		if (!(this.weaponStrat instanceof NoWeapon)) return true;
		return false;
	}
	
	/**
	 * Given sword, player picks up the sword
	 * @param sword
	 */
	public void pickUpSword(Sword sword) {
		setVisualStatus(1);
		System.out.println("pick up sword");
		sword.delete();
	}
	
	
	@Override
	public void registerObserver(Observer o) {
		//System.out.println("add observer");
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void updateObservers() {
		if (observers.isEmpty()) {
			return;
		}
		for (Observer o : observers) {
			o.update(this);
		}
	}
	
	/**
	 * Player drops the current weapon
	 */
	public void dropWeapon() {
		setWeaponStrategy(new NoWeapon());
		setVisualStatus(0);
	}
	

	public void attack() {
		if(this.isInvincible()) return;
		if(!this.haveWeapon()) return;
		setVisualStatus(4);
	}
	
	/**
	 * Given the entity obj, the player attempts to attack the entity
	 * @param obj
	 * @return true if player succeeds at attacking the entity, else false
	 */
	public boolean attack(Entity obj) {
		if (!(obj instanceof Enemy)) return false;
		Enemy enemy = (Enemy) obj;
		if (isInvincible()) {
			enemy.isDead();
			return true;
		} else if (weaponStrat.attack(obj)) {
			this.setVisualStatus(4);
			if(!(weaponStrat.hasDurability())) {
				dropWeapon();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Get the current weapon strategy of the player
	 * @return weaponStrat
	 */
	public WeaponStrategy getWeaponStrat() {
		return weaponStrat;
	}
	
	/**
	 * Set the weapon strategy of the player
	 * @param wstr
	 */
	public void setWeaponStrategy(WeaponStrategy wstr) {
		this.weaponStrat = wstr;
	}
	
	/**
	 * Given the invincible potion, player drinks the potion
	 * Player becomes invincible for 10 seconds
	 * Player returns to the previous visual status after the invincibility wears off
	 * If player changes to other status during invincibility, player keeps the new status.
	 * @param potion
	 */
	public void drinkPotion(InvinciblePotion potion) {
		Timer timer = new Timer();
		System.out.println("drink potion");
		this.invincible = true;
		setPrevVisualStatus();
		setVisualStatus(2);
		potion.delete();
		
		TimerTask task = new TimerTask() {
	        public void run() {
	        		invincible = false;
	        		if(visualStatus().getValue().equals(2)) {
	        			System.out.println("still invincible");
	        			System.out.println(getPrevVisualStatus().intValue());
	        			setVisualStatus(getPrevVisualStatus().intValue());
			        	System.out.println("invincibility wears off");
	        		}
	        	}
		};
	    
	    timer.schedule(task, 10000);		
	}
	
	/**
	 * Check if a door exist at a given coordinate
	 * @param x
	 * @param y
	 * @return true or false
	 */
	private boolean checkDoor(int x, int y) {
		for(Entity i : dungeon.getEntity(x, y)){
			if(i instanceof Door) {
				return true;
			}
		}
		return false;
	}

}
