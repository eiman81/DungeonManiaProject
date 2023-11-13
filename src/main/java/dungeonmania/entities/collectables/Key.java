package dungeonmania.entities.collectables;

import dungeonmania.entities.inventory.InventoryItem;
import dungeonmania.util.Position;

public class Key extends Collectables implements InventoryItem {
    private int number;

    public Key(Position position, int number) {
        super(position);
        this.number = number;
    }

    public int getnumber() {
        return number;
    }

}
