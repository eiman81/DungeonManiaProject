package dungeonmania.entities.buildables;

public class ShieldFactory implements BuildableFactory {
    private int durability;
    private double defence;

    public ShieldFactory(int durability, double defence) {
        this.durability = durability;
        this.defence = defence;
    }

    @Override
    public Buildable createBuildable() {
        return new Shield(durability, defence);
    }
}
