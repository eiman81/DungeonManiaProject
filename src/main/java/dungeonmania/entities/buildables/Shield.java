package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.buffs.BuffStrategy;
import dungeonmania.entities.buffs.ShieldBuff;

public class Shield extends Buildable {
    private final BuffStrategy buffStrategy;

    public Shield(int durability, double defence) {
        super(null, durability);
        this.buffStrategy = new ShieldBuff(defence);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return buffStrategy.applyBuff(origin);
    }

}
