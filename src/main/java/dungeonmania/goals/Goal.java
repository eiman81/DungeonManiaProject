package dungeonmania.goals;

import dungeonmania.Game;

public class Goal {
    private GoalStrategy strategy;
    private String type;
    private int target;
    private Goal goal1;
    private Goal goal2;

    public Goal(String type) {
        this.type = type;
    }

    public Goal(String type, int target) {
        this.type = type;
        this.target = target;
    }

    public Goal(String type, Goal goal1, Goal goal2) {
        this.type = type;
        this.goal1 = goal1;
        this.goal2 = goal2;
    }

    /**
     * Picks the correct strategy based on the type of the goal
     */
    private void pickGoalStrategy() {
        if (type.equals("exit"))
            strategy = new ExitGoal();
        else if (type.equals("boulders"))
            strategy = new BouldersGoal();
        else if (type.equals("treasure"))
            strategy = new TreasureGoal(target);
        else if (type.equals("AND"))
            strategy = new AndGoal(goal1, goal2);
        else if (type.equals("OR"))
            strategy = new OrGoal(goal1, goal2);
        else {
            strategy = new DefaultGoal();
        }
    }

    public boolean achieved(Game game) {
        if (game.getPlayer() == null)
            return false;
        pickGoalStrategy();
        return strategy.achieved(game);
    }

    public String toString(Game game) {
        pickGoalStrategy();
        if (this.achieved(game))
            return "";
        return strategy.toString(game);
    }
}
