package dungeonmania.entities.collectables;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class SwordFactory implements Factory {
    private Position position;
    private double attack;
    private int durability;

    public SwordFactory(Position position, JSONObject config) {
        this.attack = config.optDouble("sword_attack", Sword.DEFAULT_ATTACK);
        this.durability = config.optInt("sword_durability", Sword.DEFAULT_DURABILITY);
        this.position = position;
    }

    @Override
    public Sword create() {
        return new Sword(position, attack, durability);
    }
}
