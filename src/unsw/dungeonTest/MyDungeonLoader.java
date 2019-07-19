package unsw.dungeonTest;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import unsw.dungeon.*;

public class MyDungeonLoader {

    private JSONObject json;

    public MyDungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            //onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            //onLoad(wall);
            entity = wall;
            break;
        case "enemy":
            Enemy enemy = new Enemy(dungeon, x, y);
            //onLoad(enemy);
            entity = enemy;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            //onLoad(exit);
            entity = exit;
            break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	//onLoad(treasure);
        	entity = treasure;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	//onLoad(sword);
        	entity = sword;
        	break;
        case "invincibility":
        	InvinciblePotion inv = new InvinciblePotion(x, y);
        	//onLoad(inv);
        	entity = inv;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon,x,y);
        	//onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	Switch gameSwitch = new Switch(x, y);
        	//onLoad(gameSwitch);
        	entity = gameSwitch;
        	break;
        }
        dungeon.addEntity(entity);
    }
}


