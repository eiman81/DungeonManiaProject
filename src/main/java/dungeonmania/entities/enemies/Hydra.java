package dungeonmania.entities.enemies;

import java.util.Random;

import dungeonmania.Game;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.movement.FollowMovementBasic;
import dungeonmania.movement.RandomMovement;
import dungeonmania.util.Position;

public class Hydra extends Enemy {
    public static final double DEFAULT_HEALTH = 5.0;
    public static final double DEFAULT_ATTACK = 6.0;
    public static final double DEFAULT_HEALTH_INCREASE_RATE = 0.5;
    public static final double DEFAULT_HEALTH_INCREASE_AMOUNT = 3.0;
    public static final int DEFAULT_SEED = 21;

    private double increaseRate = Hydra.DEFAULT_HEALTH_INCREASE_RATE;
    private double increaseAmount = Hydra.DEFAULT_HEALTH_INCREASE_AMOUNT;
    private Random random;
    private double health = Hydra.DEFAULT_HEALTH;

    public Hydra(Position position, double health, double attack, double increaseRate) {
        super(position, health, attack);
        this.increaseRate = increaseRate;
        this.health = health;
        random = new Random(DEFAULT_SEED);
        this.setMovementBehaviour(new RandomMovement());
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        double originalHealth = this.health;
        if (entity instanceof Player) {
            Player player = (Player) entity;
            map.getGame().battle(player, this);

            double temp = random.nextFloat();
            if (temp < increaseRate) {
                this.health = originalHealth + increaseAmount;
            }
        }
    }

    @Override
    public void move(Game game) {
        GameMap map = game.getMap();
        // Use the strategy to get the next position
        chooseMovementBehaviour(map);
        Position nextPos = getMovementBehaviour().move(this, game);
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
