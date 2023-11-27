package dungeonmania.entities.buildables;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.buffs.BuffStrategy;
import dungeonmania.entities.buffs.MidnightShieldBuff;
import dungeonmania.entities.collectables.SunStone;
import dungeonmania.entities.collectables.Sword;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class MidnightArmour extends Buildable {
    private int durability = 1; // Durabulity never goes down
    private double attack;
    private double defence;
    private final BuffStrategy buffStrategy;

    public MidnightArmour(double attack, double defence) {
        super(null, 1);
        this.attack = attack;
        this.defence = defence;
        this.buffStrategy = new MidnightShieldBuff(defence, attack);
    }

    @Override
    public void use(Game game) {
        if (durability <= 0) {
            game.getPlayer().remove(this);
        }
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return buffStrategy.applyBuff(origin);
    }

    @Override
    public boolean canBuild(Inventory inventory) {
        return (inventory.count(Sword.class) >= 1 && inventory.count(SunStone.class) >= 1);
    }

    @Override
    public InventoryItem build(EntityFactory factory) {
        return factory.buildMidnightArmour();
    }

    @Override
    public void consumeItems(Inventory inventory) {
        List<Sword> swords = inventory.getEntities(Sword.class);
        List<SunStone> sunstones = inventory.getEntities(SunStone.class);

        inventory.remove(swords.get(0));
        inventory.remove(sunstones.get(0));
    }

    @Override
    public int getDurability() {
        return durability;
    }
}
