package dungeonmania.goals;

import dungeonmania.Game;

public class DefaultGoal implements GoalStrategy {
    @Override
    public boolean achieved(Game game) {
        return false;
    }

    @Override
    public String toString(Game game) {
        return "";
    }
}
