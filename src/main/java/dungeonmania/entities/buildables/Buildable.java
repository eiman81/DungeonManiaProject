package dungeonmania.entities.buildables;

import dungeonmania.entities.BattleItem;
import dungeonmania.entities.Entity;
import dungeonmania.entities.EntityFactory;
import dungeonmania.entities.inventory.Inventory;
import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;
import dungeonmania.Game;

public abstract class Buildable extends Entity implements InventoryItem, BattleItem {
    private int durability;

    public Buildable(Position position, int durability) {
        super(position);
        this.durability = durability;
    }

    @Override
    public void use(Game game) {
        durability--;
        if (durability <= 0) {
            game.getPlayer().remove(this);
        }
    }

    @Override
    public int getDurability() {
        return durability;
    }

    public abstract boolean canBuild(Inventory inventory);

    public abstract InventoryItem build(EntityFactory factory);

    public abstract void consumeItems(Inventory inventory);
}
