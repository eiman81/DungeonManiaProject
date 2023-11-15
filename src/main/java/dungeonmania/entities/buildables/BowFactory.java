package dungeonmania.entities.buildables;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class BowFactory implements Factory {
    private int durability;

    public BowFactory(Position position, JSONObject config) {
        this.durability = config.optInt("bow_durability");
    }

    @Override
    public Buildable create() {
        return new Bow(durability);
    }
}
