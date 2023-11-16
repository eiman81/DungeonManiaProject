package dungeonmania.entities.enemies;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Entity;
import dungeonmania.entities.Interactable;
import dungeonmania.entities.Player;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.potions.InvincibilityPotion;
import dungeonmania.entities.collectables.potions.InvisibilityPotion;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;
import dungeonmania.movement.AlliedMovement;
import dungeonmania.movement.FollowMovementBasic;
import dungeonmania.movement.FollowMovementDijkstra;
import dungeonmania.movement.RandomMovement;
import java.util.Random;

public class Assassin extends Enemy implements Interactable {
    public static final int DEFAULT_BRIBE_AMOUNT = 1;
    public static final int DEFAULT_BRIBE_RADIUS = 1;
    public static final double DEFAULT_ATTACK = 5.0;
    public static final double DEFAULT_HEALTH = 10.0;
    public static final double DEFAULT_BRIBE_FAIL_RATE = 0.5;
    public static final long DEFAULT_SEED = 21;

    private int bribeAmount = Assassin.DEFAULT_BRIBE_AMOUNT;
    private int bribeRadius = Assassin.DEFAULT_BRIBE_RADIUS;
    private double bribeFailRate = Assassin.DEFAULT_BRIBE_FAIL_RATE;

    private double allyAttack;
    private double allyDefence;
    private boolean allied = false;
    private boolean isAdjacentToPlayer = false;
    private Random random;

    public Assassin(Position position, double health, double attack, int bribeAmount, int bribeRadius,
            double allyAttack, double allyDefence, double bribeFailRate) {
        super(position, health, attack);
        this.bribeAmount = bribeAmount;
        this.bribeRadius = bribeRadius;
        this.allyAttack = allyAttack;
        this.allyDefence = allyDefence;
        this.bribeFailRate = bribeFailRate;
        random = new Random(DEFAULT_SEED);
        setMovementBehaviour(new FollowMovementDijkstra());
    }

    public boolean isAllied() {
        return allied;
    }

    @Override
    public void onOverlap(GameMap map, Entity entity) {
        if (allied)
            return;
        super.onOverlap(map, entity);
    }

    /**
     * check whether the current ass can be bribed
     * @param player
     * @return
     */
    private boolean canBeBribed(Player player) {
        return bribeRadius >= 0 && player.countEntityOfType(Treasure.class) >= bribeAmount;
    }

    /**
     * bribe the ass
     */
    private void bribe(Player player) {
        for (int i = 0; i < bribeAmount; i++) {
            player.use(Treasure.class);
        }
    }

    @Override
    public void interact(Player player, Game game) {
        double temp = random.nextFloat();
        if (temp > bribeFailRate) {
            allied = true;
        } else {
            allied = false;
        }
        bribe(player);
        updateIsAdjacentToPlayer(getPosition(), player);
    }

    @Override
    public void move(Game game) {
        // Use the strategy to get the next position
        Position nextPos;
        GameMap map = game.getMap();
        chooseMovementBehaviour(map);
        nextPos = getMovementBehaviour().move(this, game);
        map.moveTo(this, nextPos);
    }

    public void chooseMovementBehaviour(GameMap map) {
        if (allied) {
            setMovementBehaviour(new AlliedMovement(isAdjacentToPlayer));
        } else if (map.getPlayer().getEffectivePotion() instanceof InvisibilityPotion) {
            setMovementBehaviour(new RandomMovement());
        } else if (map.getPlayer().getEffectivePotion() instanceof InvincibilityPotion) {
            setMovementBehaviour(new FollowMovementBasic());
        } else {
            setMovementBehaviour(new FollowMovementDijkstra());
        }
    }

    @Override
    public boolean isInteractable(Player player) {
        return !allied && canBeBribed(player);
    }

    @Override
    public BattleStatistics getBattleStatistics() {
        if (!allied)
            return super.getBattleStatistics();
        return new BattleStatistics(0, allyAttack, allyDefence, 1, 1);
    }

    /**
     * update whether the merc is adjacent to the player
     * @param nextPos
     * @param player
     */
    public void updateIsAdjacentToPlayer(Position nextPos, Player player) {
        if (!isAdjacentToPlayer && Position.isAdjacent(player.getPosition(), nextPos)) {
            isAdjacentToPlayer = true;
        }
    }

}
