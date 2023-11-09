package dungeonmania.movement;

import java.util.List;

import dungeonmania.entities.Boulder;
import dungeonmania.entities.enemies.Enemy;
import dungeonmania.util.Position;
import dungeonmania.map.GameMap;
import dungeonmania.entities.Entity;

public class SpiderMovement implements MovementBehaviour {
    private List<Position> movementTrajectory;
    private int nextPositionElement;
    private boolean forward;

    public SpiderMovement(Position position) {
        /**
         * Establish spider movement trajectory Spider moves as follows:
         *  8 1 2       10/12  1/9  2/8
         *  7 S 3       11     S    3/7
         *  6 5 4       B      5    4/6
         */
        movementTrajectory = position.getAdjacentPositions();
        nextPositionElement = 1;
        forward = true;
    }

    @Override
    public Position move(Enemy enemy, GameMap map) {
        Position nextPos = movementTrajectory.get(nextPositionElement);
        List<Entity> entities = map.getEntities(nextPos);
        if (entities != null && entities.size() > 0 && entities.stream().anyMatch(e -> e instanceof Boulder)) {
            forward = !forward;
            updateNextPosition();
            updateNextPosition();
        }
        nextPos = movementTrajectory.get(nextPositionElement);
        entities = map.getEntities(nextPos);
        if (entities == null || entities.size() == 0 || entities.stream().allMatch(e -> e.canMoveOnto(map, enemy))) {
            updateNextPosition();
            return nextPos;
        }
        return enemy.getPosition();
    }

    private void updateNextPosition() {
        if (forward) {
            nextPositionElement++;
            if (nextPositionElement == 8) {
                nextPositionElement = 0;
            }
        } else {
            nextPositionElement--;
            if (nextPositionElement == -1) {
                nextPositionElement = 7;
            }
        }
    }
}
