package dungeonmania.movement;

import dungeonmania.entities.enemies.Enemy;
import dungeonmania.map.GameMap;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class FollowMovementBasic implements MovementBehaviour {
    @Override
    public Position move(Enemy enemy, GameMap map) {

        Position currPos = enemy.getPosition();
        Position plrDiff = Position.calculatePositionBetween(map.getPlayer().getPosition(), currPos);

        Position moveX = (plrDiff.getX() >= 0) ? Position.translateBy(currPos, Direction.RIGHT)
                : Position.translateBy(currPos, Direction.LEFT);
        Position moveY = (plrDiff.getY() >= 0) ? Position.translateBy(currPos, Direction.UP)
                : Position.translateBy(currPos, Direction.DOWN);
        Position offset = currPos;
        if (plrDiff.getY() == 0 && map.canMoveTo(enemy, moveX))
            offset = moveX;
        else if (plrDiff.getX() == 0 && map.canMoveTo(enemy, moveY))
            offset = moveY;
        else if (Math.abs(plrDiff.getX()) >= Math.abs(plrDiff.getY())) {
            if (map.canMoveTo(enemy, moveX))
                offset = moveX;
            else if (map.canMoveTo(enemy, moveY))
                offset = moveY;
            else
                offset = currPos;
        } else {
            if (map.canMoveTo(enemy, moveY))
                offset = moveY;
            else if (map.canMoveTo(enemy, moveX))
                offset = moveX;
            else
                offset = currPos;
        }
        return offset;
    }
}
