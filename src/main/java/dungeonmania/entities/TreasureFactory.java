package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.entities.collectables.Treasure;
import dungeonmania.util.Position;

public class TreasureFactory implements Factory {
    private Position position;

    public TreasureFactory(Position position, JSONObject config) {
        this.position = position;
    }

    @Override
    public Treasure create() {
        return new Treasure(position);
    }
}
