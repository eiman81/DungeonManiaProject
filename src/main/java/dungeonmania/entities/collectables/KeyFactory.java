package dungeonmania.entities.collectables;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class KeyFactory implements Factory {
    private Position position;
    private int number;

    public KeyFactory(Position position, JSONObject config) {
        this.position = position;
        this.number = config.getInt("key");
    }

    @Override
    public Key create() {
        return new Key(position, number);
    }
}
