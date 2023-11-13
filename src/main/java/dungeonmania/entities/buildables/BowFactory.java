package dungeonmania.entities.buildables;

public class BowFactory implements BuildableFactory {
    private int durability;

    public BowFactory(int durability) {
        this.durability = durability;
    }

    @Override
    public Buildable createBuildable() {
        return new Bow(durability);
    }
}
