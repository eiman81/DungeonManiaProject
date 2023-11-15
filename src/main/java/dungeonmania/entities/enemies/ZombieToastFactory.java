package dungeonmania.entities.enemies;

import dungeonmania.util.Position;

import org.json.JSONObject;

import dungeonmania.entities.Factory;

public class ZombieToastFactory implements Factory {
    private Position position;
    private double health;
    private double attack;

    public ZombieToastFactory(Position position, JSONObject config) {
        this.health = config.optDouble("zombie_health", ZombieToast.DEFAULT_HEALTH);
        this.attack = config.optDouble("zombie_attack", ZombieToast.DEFAULT_ATTACK);
        this.position = position;
    }

    @Override
    public Enemy create() {
        return new ZombieToast(position, health, attack);
    }
}
