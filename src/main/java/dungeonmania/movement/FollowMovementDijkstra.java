package dungeonmania.movement;

import dungeonmania.Game;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public class FollowMovementDijkstra implements MovementBehaviour {
    @Override
    public Position move(Enemy enemy, Game game) {
        GameMap map = game.getMap();
        return map.dijkstraPathFind(enemy.getPosition(), map.getPlayer().getPosition(), enemy);
    }
}
