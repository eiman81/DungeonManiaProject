package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.entities.collectables.Wood;
import dungeonmania.util.Position;

public class WoodFactory implements Factory {
    private Position position;

    public WoodFactory(Position position, JSONObject config) {
        this.position = position;
    }

    @Override
    public Wood create() {
        return new Wood(position);
    }
}
