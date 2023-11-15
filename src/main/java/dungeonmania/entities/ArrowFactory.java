package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.entities.collectables.Arrow;
import dungeonmania.util.Position;

public class ArrowFactory implements Factory {
    private Position position;

    public ArrowFactory(Position position, JSONObject config) {
        this.position = position;
    }

    @Override
    public Arrow create() {
        return new Arrow(position);
    }
}
