package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.util.Position;

public class WallFactory implements Factory {
    private Position position;

    public WallFactory(Position position, JSONObject config) {
        this.position = position;
    }

    @Override
    public Wall create() {
        return new Wall(position);
    }

}
