package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Player;

public abstract class PlayerState {
    private Player player;

    public PlayerState(Player player) {
        this.player = player;
    }

    public abstract BattleStatistics applyBuff(BattleStatistics origin);

}
