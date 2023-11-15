package dungeonmania.entities.collectables.potions;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class InvisibilityPotionFactory implements Factory {
    private int duration;
    private Position position;

    public InvisibilityPotionFactory(Position position, JSONObject config) {
        this.duration = config.optInt("invisibility_potion_duration", InvisibilityPotion.DEFAULT_DURATION);
        this.position = position;
    }

    @Override
    public Potion create() {
        return new InvisibilityPotion(position, duration);
    }
}
