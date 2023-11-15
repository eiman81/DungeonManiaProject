package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.util.Position;

public class BoulderFactory implements Factory {
    private Position position;

    public BoulderFactory(Position position, JSONObject config) {
        this.position = position;
    }

    @Override
    public Boulder create() {
        return new Boulder(position);
    }
}
