package dungeonmania.entities.buildables;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.buffs.BowBuff;
import dungeonmania.entities.buffs.BuffStrategy;

public class Bow extends Buildable {
    private final BuffStrategy buffStrategy = new BowBuff();

    public Bow(int durability) {
        super(null, durability);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return buffStrategy.applyBuff(origin);
    }
}
