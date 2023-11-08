package dungeonmania.movement;

import dungeonmania.entities.enemies.*;
import dungeonmania.map.GameMap;
import dungeonmania.util.Position;

public interface MovementBehaviour {
    Position move(Enemy enemy, GameMap map);
}
