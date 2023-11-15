package dungeonmania.entities.buffs;

import dungeonmania.battles.BattleStatistics;

public interface BuffStrategy {
    BattleStatistics applyBuff(BattleStatistics origin);
}
