package dungeonmania.movement;

import dungeonmania.Game;
import dungeonmania.entities.Player;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.entities.enemies.Mercenary;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class AlliedMovement implements MovementBehaviour {
    private boolean isAdjacentToPlayer;

    public AlliedMovement(boolean isAdjacentToPlayer) {
        this.isAdjacentToPlayer = isAdjacentToPlayer;
    }

    @Override
    public Position move(Enemy enemy, Game game) {
        Position nextPos;
        GameMap map = game.getMap();
        Player player = game.getPlayer();
        nextPos = isAdjacentToPlayer ? player.getPreviousDistinctPosition()
                : map.dijkstraPathFind(enemy.getPosition(), player.getPosition(), enemy);
        if (enemy instanceof Mercenary) {
            ((Mercenary) enemy).updateIsAdjacentToPlayer(nextPos, player);
        }
        return nextPos;
    }
}
