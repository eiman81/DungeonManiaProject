package dungeonmania.entities.buffs;

import dungeonmania.battles.BattleStatistics;

public class SceptreBuff implements BuffStrategy {
    public SceptreBuff() {
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, 0, 1, 1));
    }
}
