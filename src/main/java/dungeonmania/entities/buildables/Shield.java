package dungeonmania.entities.buildables;

import java.util.List;

import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.buffs.BuffStrategy;
import dungeonmania.entities.buffs.ShieldBuff;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class Shield extends Buildable {
    private final BuffStrategy buffStrategy;

    public Shield(int durability, double defence) {
        super(null, durability);
        this.buffStrategy = new ShieldBuff(defence);
    }

    public Shield() {
        super(null, 10);
        this.buffStrategy = new ShieldBuff(0.5);
    }

    @Override
    public BattleStatistics applyBuff(BattleStatistics origin) {
        return buffStrategy.applyBuff(origin);
    }

    @Override
    public boolean canBuild(Inventory inventory) {
        return inventory.count(Wood.class) >= 2
                && (inventory.count(Treasure.class) >= 1 || inventory.count(Key.class) >= 1);
    }

    @Override
    public InventoryItem build(EntityFactory factory) {
        return factory.buildShield();
    }

    @Override
    public void consumeItems(Inventory inventory) {
        List<Wood> wood = inventory.getEntities(Wood.class);
        List<Treasure> treasure = inventory.getEntities(Treasure.class);
        List<Key> keys = inventory.getEntities(Key.class);

        inventory.remove(wood.get(0));
        inventory.remove(wood.get(1));
        if (treasure.size() >= 1) {
            inventory.remove(treasure.get(0));
        } else {
            inventory.remove(keys.get(0));
        }
    }

}
