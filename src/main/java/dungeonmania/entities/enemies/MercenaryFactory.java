package dungeonmania.entities.enemies;

import dungeonmania.util.Position;

import org.json.JSONObject;

import dungeonmania.entities.Factory;

public class MercenaryFactory implements Factory {
    private Position position;
    private double health;
    private double attack;
    private double allyAttack;
    private double allyDefence;
    private int bribeAmount;
    private int bribeRadius;
    private int mindControlDuration;

    public MercenaryFactory(Position position, JSONObject config) {
        this.health = config.optDouble("mercenary_health", Mercenary.DEFAULT_HEALTH);
        this.attack = config.optDouble("mercenary_attack", Mercenary.DEFAULT_ATTACK);
        this.allyAttack = config.optDouble("ally_attack", Mercenary.DEFAULT_HEALTH);
        this.allyDefence = config.optDouble("ally_defence", Mercenary.DEFAULT_ATTACK);
        this.bribeAmount = config.optInt("bribe_amount", Mercenary.DEFAULT_BRIBE_AMOUNT);
        this.bribeRadius = config.optInt("bribe_radius", Mercenary.DEFAULT_BRIBE_RADIUS);
        this.mindControlDuration = config.optInt("mind_control_duration", Mercenary.DEFAULT_MIND_CONTROL_DURATION);
        this.position = position;
    }

    @Override
    public Enemy create() {
        return new Mercenary(position, health, attack, bribeAmount, bribeRadius, allyAttack, allyDefence,
                mindControlDuration);
    }
}
