package dungeonmania.goals;

import dungeonmania.Game;

public interface GoalStrategy {
    boolean achieved(Game game);

    String toString(Game game);
}
