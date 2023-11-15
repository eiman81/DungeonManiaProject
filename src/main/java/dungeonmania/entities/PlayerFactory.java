package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.util.Position;

public class PlayerFactory implements Factory {
    private Position position;
    private double health;
    private double attack;

    public PlayerFactory(Position position, JSONObject config) {
        this.health = config.optDouble("player_health", Player.DEFAULT_HEALTH);
        this.attack = config.optDouble("player_attack", Player.DEFAULT_ATTACK);
        this.position = position;
    }

    @Override
    public Player create() {
        return new Player(position, health, attack);
    }
}
