package dungeonmania.entities.buffs;

import dungeonmania.battles.BattleStatistics;

public class BowBuff implements BuffStrategy {
    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 2, 1));
    }
}
