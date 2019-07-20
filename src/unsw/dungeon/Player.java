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

    public boolean getGameInProgress() {
    	return dungeon.getGameInProgress();
    }
    
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
    
    public void moveUp() {
    	if(this.checkMoveable(this.getX(), this.getY() - 1)) {
    		dungeon.interactItems(this, this.getX(), this.getY() - 1);
    		y().set(getY() - 1);
    		updateObservers();
    	} else if (this.checkDoor(this.getX(), this.getY() - 1)){
    		dungeon.interactItems(this, this.getX(), this.getY() - 1);
    	}
    }

    public void moveDown() {
    	if(this.checkMoveable(this.getX(), this.getY() + 1)) {
    		dungeon.interactItems(this, this.getX(), this.getY() + 1);
    		y().set(getY() + 1);
    		updateObservers();
    	} else if (this.checkDoor(this.getX(), this.getY() + 1)){
    		dungeon.interactItems(this, this.getX(), this.getY() + 1);
    	}
    }

    public void moveLeft() {
    	if(this.checkMoveable(this.getX() - 1, this.getY())) {
    		dungeon.interactItems(this, this.getX() - 1, this.getY());
    		x().set(getX() - 1);
    		updateObservers();
    	} else if (this.checkDoor(this.getX() - 1, this.getY())){
    		dungeon.interactItems(this, this.getX() - 1, this.getY());
    	}
    }

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
	
	public int getKeyID() {
		return keyID;
	}

	public void setKeyID(int keyID) {
		this.keyID = keyID;
	}

	public Bomb getBomb() {
		return bomb;
	}

	public void setBomb(Bomb bomb) {
		this.bomb = bomb;
	}
	
	public void dropBomb() {
		if (bomb != null) {
			bomb.activate(this);
		}
	}

	public void pickUpTreasure(Treasure treasure) {
		if (treasure.isCollected()) return;
		System.out.println("Pick up treasure");
		treasure.collect();
		this.treasureCollected++;
		this.updateObservers();
	}
	
	public Dungeon getDungeon() {
		return dungeon;
	}

	public int getTreasureCollected() {
		return this.treasureCollected;
	}

	@Override
	public void interact(Entity obj) {
		if (obj instanceof Enemy) {
			Enemy enemy = (Enemy) obj;
			if (!(attack(enemy))) {
				//System.out.println("can not attack enemy");
				this.delete();
			}
		}
		return;
	}

	public IntegerProperty visualStatus() {
		return this.visualStatus;
	}
	
	public void setPrevVisualStatus() {
		this.prevVisualStatus = new SimpleIntegerProperty(this.visualStatus.intValue());
	}
	
	public IntegerProperty getPrevVisualStatus() {
		return this.prevVisualStatus;
	}
	
	public void setVisualStatus(int num) {
		visualStatus().set(num);
	}	

	public boolean isInvincible() {
		return this.invincible;
	}
	
	public boolean haveWeapon() {
		if (!(this.weaponStrat instanceof NoWeapon)) return true;
		return false;
	}
	
	
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
	
	public void dropWeapon() {
		setWeaponStrategy(new NoWeapon());
		setVisualStatus(0);
	}
	
	public boolean attack(Entity obj) {
		if (!(obj instanceof Enemy)) return false;
		if (isInvincible()) {
			obj.delete();
			return true;
		} else if (weaponStrat.attack(obj)) {
			if(!(weaponStrat.hasDurability())) {
				dropWeapon();
			}
			obj.delete();
			return true;
		}
		return false;
	}
	
	public WeaponStrategy getWeaponStrat() {
		return weaponStrat;
	}
	
	public void setWeaponStrategy(WeaponStrategy wstr) {
		this.weaponStrat = wstr;
	}
	
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
	
	private boolean checkDoor(int x, int y) {
		for(Entity i : dungeon.getEntity(x, y)){
			if(i instanceof Door) {
				return true;
			}
		}
		return false;
	}

	public boolean completedNonExitGoals() {
		return dungeon.completedNonExitGoals();
	}

}
