package dungeonmania.entities.playerState;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.Player;

public abstract class PlayerState {
    private Player player;

    public PlayerState(Player player) {
        this.player = player;
    }

    public abstract PlayerStateType getStateType();
    /* 
    public void transitionTo(PlayerStateType stateType) {
        switch (stateType) {
        case BASE:
            player.changeState(new BaseState(player));
        case INVINCIBLE:
            player.changeState(new InvincibleState(player));
        case INVISIBLE:
            player.changeState(new InvisibleState(player));
        default:
            break;
        }
    }
    */

}
