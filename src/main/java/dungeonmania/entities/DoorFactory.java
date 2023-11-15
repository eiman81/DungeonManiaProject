package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.util.Position;

public class DoorFactory implements Factory {
    private Position position;
    private int number;

    public DoorFactory(Position position, JSONObject config) {
        this.position = position;
        this.number = config.getInt("key");
    }

    @Override
    public Door create() {
        return new Door(position, number);
    }
}
