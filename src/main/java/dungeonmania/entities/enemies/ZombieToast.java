package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.movement.FollowMovementBasic;
import dungeonmania.movement.RandomMovement;
import dungeonmania.util.Position;

public class ZombieToast extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;

    public ZombieToast(Position position, double health, double attack) {
        super(position, health, attack);
        setMovementBehaviour(new RandomMovement());
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        // Use the strategy to get the next position
        chooseMovementBehaviour(map);
        Position nextPos = movementBehaviour.move(this, map);
        map.moveTo(this, nextPos);
    }

    public void chooseMovementBehaviour(GameMap map) {
        if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            setMovementBehaviour(new FollowMovementBasic());
        } else {
            setMovementBehaviour(new RandomMovement());
        }
    }

}
