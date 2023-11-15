package dungeonmania.entities.collectables;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class BombFactory implements Factory {
    private Position position;
    private int radius;

    public BombFactory(Position position, JSONObject config) {
        this.position = position;
        this.radius = config.optInt("bomb_radius", Bomb.DEFAULT_RADIUS);
    }

    @Override
    public Bomb create() {
        return new Bomb(position, radius);
    }
}
