package dungeonmania.goals;

import dungeonmania.Game;

public class EnemyGoal implements GoalStrategy {
    private int target;

    public EnemyGoal(String type, Integer target) {
        this.target = target;
    }

    @Override
    public boolean achieved(Game game) {
        return game.getEnemiesKilled() >= target;
    }

    @Override
    public String toString(Game game) {
        if (this.achieved(game)) {
            return "";
        }
        return ":enemies";
    }
}
