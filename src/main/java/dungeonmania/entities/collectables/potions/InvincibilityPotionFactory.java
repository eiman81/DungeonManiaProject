package dungeonmania.entities.collectables.potions;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class InvincibilityPotionFactory implements Factory {
    private int duration;
    private Position position;

    public InvincibilityPotionFactory(Position position, JSONObject config) {
        this.duration = config.optInt("invincibility_potion_duration", InvincibilityPotion.DEFAULT_DURATION);
        this.position = position;
    }

    @Override
    public Potion create() {
        return new InvincibilityPotion(position, duration);
    }
}
