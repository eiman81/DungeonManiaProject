package dungeonmania.entities.buildables;

import java.util.List;

import dungeonmania.Game;
import dungeonmania.battles.BattleStatistics;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.buffs.BuffStrategy;
import dungeonmania.entities.buffs.SceptreBuff;
import dungeonmania.entities.collectables.Arrow;
import dungeonmania.entities.collectables.Key;
import dungeonmania.entities.collectables.Sunstone;
import dungeonmania.entities.collectables.Treasure;
import dungeonmania.entities.collectables.Wood;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;

public class Sceptre extends Buildable {
    private final BuffStrategy buffStrategy;
    private int durability = 1;
    private int mindControlDuration;

    public Sceptre(int mindControlDuration) {
        super(null, 1);
        this.mindControlDuration = mindControlDuration;
        this.buffStrategy = new SceptreBuff();
    }

    @Override
    public void use(Game game) {
        durability--;
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
        return (inventory.count(Wood.class) >= 1 || inventory.count(Arrow.class) >= 2)
                && (inventory.count(Treasure.class) >= 1 || inventory.count(Key.class) >= 1)
                && (inventory.count(Sunstone.class) >= 1);
    }

    @Override
    public InventoryItem build(EntityFactory factory) {
        return factory.buildSceptre();
    }

    @Override
    public void consumeItems(Inventory inventory) {
        List<Wood> wood = inventory.getEntities(Wood.class);
        List<Treasure> treasure = inventory.getEntities(Treasure.class);
        List<Key> keys = inventory.getEntities(Key.class);
        List<Sunstone> sunstones = inventory.getEntities(Sunstone.class);
        List<Arrow> arrows = inventory.getEntities(Arrow.class);

        if (wood.size() >= 1)
            inventory.remove(wood.get(0));
        else {
            inventory.remove(arrows.get(0));
            inventory.remove(arrows.get(1));
        }

        if (keys.size() >= 1)
            inventory.remove(keys.get(0));
        else
            inventory.remove(treasure.get(0));

        inventory.remove(sunstones.get(0));

    }

    @Override
    public int getDurability() {
        return durability;
    }

    public int getMindControlDuration() {
        return mindControlDuration;
    }
}
