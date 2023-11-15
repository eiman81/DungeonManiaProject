package dungeonmania.entities.buildables;

import java.util.List;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.buffs.BowBuff;
import dungeonmania.entities.buffs.BuffStrategy;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class Bow extends Buildable {
    private final BuffStrategy buffStrategy = new BowBuff();

    public Bow(int durability) {
        super(null, durability);
    }

    public Bow() {
        super(null, 10);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return buffStrategy.applyBuff(origin);
    }

    @Override
    public boolean canBuild(Inventory inventory) {
        return inventory.count(Wood.class) >= 1 && inventory.count(Arrow.class) >= 3;
    }

    @Override
    public InventoryItem build(EntityFactory factory) {
        return factory.buildBow();
    }

    @Override
    public void consumeItems(Inventory inventory) {
        List<Wood> wood = inventory.getEntities(Wood.class);
        List<Arrow> arrows = inventory.getEntities(Arrow.class);
        inventory.remove(wood.get(0));
        inventory.remove(arrows.get(0));
        inventory.remove(arrows.get(1));
        inventory.remove(arrows.get(2));
    }
}
