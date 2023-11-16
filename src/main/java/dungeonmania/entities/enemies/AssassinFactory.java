package dungeonmania.entities.enemies;

import dungeonmania.util.Position;

import org.json.JSONObject;

import dungeonmania.entities.Factory;

public class AssassinFactory implements Factory {
    private Position position;
    private double health;
    private double attack;
    private double allyAttack;
    private double allyDefence;
    private int bribeAmount;
    private int bribeRadius;
    private double bribeFailRate;

    public AssassinFactory(Position position, JSONObject config) {
        this.health = config.optDouble("assassin_health", Assassin.DEFAULT_HEALTH);
        this.attack = config.optDouble("assassin_attack", Assassin.DEFAULT_ATTACK);
        this.allyAttack = config.optDouble("ally_attack", Assassin.DEFAULT_HEALTH);
        this.allyDefence = config.optDouble("ally_defence", Assassin.DEFAULT_ATTACK);
        this.bribeAmount = config.optInt("bribe_amount", Assassin.DEFAULT_BRIBE_AMOUNT);
        this.bribeRadius = config.optInt("bribe_radius", Assassin.DEFAULT_BRIBE_RADIUS);
        this.bribeFailRate = config.optDouble("bribe_fail_rate", Assassin.DEFAULT_BRIBE_FAIL_RATE);
        this.position = position;
    }

    @Override
    public Enemy create() {
        return new Assassin(position, health, attack, bribeAmount, bribeRadius, allyAttack, allyDefence, bribeFailRate);
    }
}
