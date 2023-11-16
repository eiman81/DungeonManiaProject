package dungeonmania.entities.buildables;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class MidnightArmourFactory implements Factory {
    private int attack;
    private int defence;

    public MidnightArmourFactory(Position position, JSONObject config) {
        this.attack = config.optInt("midnight_armour_attack");
        this.defence = config.optInt("midnight_armour_defence");
    }

    @Override
    public Buildable create() {
        return new MidnightArmour(attack, defence);
    }
}
