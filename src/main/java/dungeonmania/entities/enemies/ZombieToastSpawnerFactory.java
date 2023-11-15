package dungeonmania.entities.enemies;

import dungeonmania.util.Position;

import org.json.JSONObject;

import dungeonmania.entities.Factory;

public class ZombieToastSpawnerFactory implements Factory {
    private Position position;
    private int spawnRate;

    public ZombieToastSpawnerFactory(Position position, JSONObject config) {
        this.position = position;
        this.spawnRate = config.optInt("zombie_spawn_interval", ZombieToastSpawner.DEFAULT_SPAWN_INTERVAL);
    }

    @Override
    public ZombieToastSpawner create() {
        return new ZombieToastSpawner(position, spawnRate);
    }
}
