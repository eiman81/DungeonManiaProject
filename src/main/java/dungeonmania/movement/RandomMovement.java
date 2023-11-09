package dungeonmania.movement;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.Game;

import dungeonmania.util.Position;

public class RandomMovement implements MovementBehaviour {
    @Override
    public Position move(Enemy enemy, Game game) {
        Position currPos = enemy.getPosition();
        GameMap map = game.getMap();
        Random randGen = new Random();
        List<Position> pos = currPos.getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> map.canMoveTo(enemy, p)).collect(Collectors.toList());
        if (pos.size() == 0) {
            return enemy.getPosition();
        } else {
            return pos.get(randGen.nextInt(pos.size()));
        }
    }
}
