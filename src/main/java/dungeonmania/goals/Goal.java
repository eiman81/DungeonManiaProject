package dungeonmania.goals;

import dungeonmania.Game;

public class Goal {
    private GoalStrategy strategy;

    public Goal(String type) {
        if (type.equals("exit"))
            strategy = new ExitGoal();
        else if (type.equals("boulders"))
            strategy = new BouldersGoal();
        else {
            strategy = new DefaultGoal();
        }
    }

    public Goal(String type, int target) {
        if (type.equals("treasure"))
            strategy = new TreasureGoal(target);
        else {
            strategy = new DefaultGoal();
        }
    }

    public Goal(String type, Goal goal1, Goal goal2) {
        if (type.equals("AND"))
            strategy = new AndGoal(goal1, goal2);
        else if (type.equals("OR"))
            strategy = new OrGoal(goal1, goal2);
        else {
            strategy = new DefaultGoal();
        }
    }

    public boolean achieved(Game game) {
        return strategy.achieved(game);
    }

    public String toString(Game game) {
        return strategy.toString(game);
    }
}
