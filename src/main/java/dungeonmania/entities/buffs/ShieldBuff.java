package dungeonmania.entities.buffs;

import dungeonmania.battles.BattleStatistics;

public class ShieldBuff implements BuffStrategy {
    private final double defence;

    public ShieldBuff(double defence) {
        this.defence = defence;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, 0, defence, 1, 1));
    }

}
