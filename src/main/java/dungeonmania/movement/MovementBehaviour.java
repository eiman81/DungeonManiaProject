package dungeonmania.movement;

import dungeonmania.entities.enemies.*;
import dungeonmania.util.Position;
import dungeonmania.Game;

public interface MovementBehaviour {
    Position move(Enemy enemy, Game game);
}
