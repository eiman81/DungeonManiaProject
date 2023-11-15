package dungeonmania.entities.enemies;

import dungeonmania.util.Position;

import org.json.JSONObject;

import dungeonmania.entities.Factory;

public class SpiderFactory implements Factory {
    private Position position;
    private double health;
    private double attack;

    public SpiderFactory(Position position, JSONObject config) {
        this.health = config.optDouble("spider_health", Spider.DEFAULT_HEALTH);
        this.attack = config.optDouble("spider_attack", Spider.DEFAULT_ATTACK);
        this.position = position;
    }

    @Override
    public Enemy create() {
        return new Spider(position, health, attack);
    }
}
