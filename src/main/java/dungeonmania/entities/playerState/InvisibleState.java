package dungeonmania.entities.playerState;

import dungeonmania.entities.Player;

public class InvisibleState extends PlayerState {
    public InvisibleState(Player player) {
        super(player);
    }

    @Override
    public PlayerStateType getStateType() {
        return PlayerStateType.INVISIBLE;
    }
}
