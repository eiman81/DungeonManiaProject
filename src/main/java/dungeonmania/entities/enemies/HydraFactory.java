package dungeonmania.entities.enemies;

import dungeonmania.util.Position;

import org.json.JSONObject;

import dungeonmania.entities.Factory;

public class HydraFactory implements Factory {
    private Position position;
    private double health;
    private double attack;
    private double increaseRate;

    public HydraFactory(Position position, JSONObject config) {
        this.health = config.optDouble("hydra_health", Hydra.DEFAULT_HEALTH);
        this.attack = config.optDouble("hydra_attack", Hydra.DEFAULT_ATTACK);
        this.health = config.optDouble("hydra_health_increase_rate", Hydra.DEFAULT_HEALTH);
        this.attack = config.optDouble("hydra_health_increase_amount", Hydra.DEFAULT_ATTACK);
        this.position = position;
    }

    @Override
    public Enemy create() {
        return new Hydra(position, health, attack, increaseRate);
    }
}
