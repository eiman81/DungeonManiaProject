package dungeonmania.entities.buildables;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class ShieldFactory implements Factory {
    private int durability;
    private double defence;

    public ShieldFactory(Position position, JSONObject config) {
        this.durability = config.optInt("shield_durability");
        this.defence = config.optInt("shield_defence");
    }

    @Override
    public Buildable create() {
        return new Shield(durability, defence);
    }
}
