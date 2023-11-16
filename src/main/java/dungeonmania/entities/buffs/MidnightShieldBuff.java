package dungeonmania.entities.buffs;

import dungeonmania.battles.BattleStatistics;

public class MidnightShieldBuff implements BuffStrategy {
    private final double defence;
    private final double attack;

    public MidnightShieldBuff(double defence, double attack) {
        this.defence = defence;
        this.attack = attack;
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return BattleStatistics.applyBuff(origin, new BattleStatistics(0, attack, defence, 1, 1));
    }
}
