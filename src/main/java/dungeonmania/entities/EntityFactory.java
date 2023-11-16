package dungeonmania.entities;

import dungeonmania.Game;
import dungeonmania.entities.buildables.Bow;
import dungeonmania.entities.buildables.BowFactory;
import dungeonmania.entities.buildables.Shield;
import dungeonmania.entities.buildables.ShieldFactory;
import dungeonmania.entities.collectables.*;
import dungeonmania.entities.enemies.*;
import dungeonmania.map.GameMap;
import dungeonmania.entities.collectables.potions.InvincibilityPotionFactory;
import dungeonmania.entities.collectables.potions.InvisibilityPotionFactory;
import dungeonmania.util.Position;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class EntityFactory {
    private JSONObject config;
    private Random ranGen = new Random();
    private Factory factory;

    public EntityFactory(JSONObject config) {
        this.config = config;
    }

    public Entity createEntity(JSONObject jsonEntity) {
        setFactory(jsonEntity, config);
        return constructEntity();
    }

    public void spawnSpider(Game game) {
        GameMap map = game.getMap();
        int tick = game.getTick();
        int rate = config.optInt("spider_spawn_interval", 0);
        if (rate == 0 || (tick + 1) % rate != 0)
            return;
        int radius = 20;
        Position player = map.getPlayer().getPosition();

        Spider dummySpider = (Spider) constructEntity(new SpiderFactory(new Position(0, 0), config));
        List<Position> availablePos = map.getAvailablePositions(dummySpider, player, radius);
        Position initPosition = availablePos.get(ranGen.nextInt(availablePos.size()));
        Spider spider = (Spider) constructEntity(new SpiderFactory(initPosition, config));
        map.addEntity(spider);
        game.register(() -> spider.move(game), Game.AI_MOVEMENT, spider.getId());
    }

    public void spawnZombie(Game game, ZombieToastSpawner spawner) {
        GameMap map = game.getMap();
        int tick = game.getTick();
        Random randGen = new Random();
        int spawnInterval = config.optInt("zombie_spawn_interval", ZombieToastSpawner.DEFAULT_SPAWN_INTERVAL);
        if (spawnInterval == 0 || (tick + 1) % spawnInterval != 0)
            return;
        List<Position> pos = spawner.getPosition().getCardinallyAdjacentPositions();
        pos = pos.stream().filter(p -> !map.getEntities(p).stream().anyMatch(e -> (e instanceof Wall)))
                .collect(Collectors.toList());
        if (pos.size() == 0)
            return;

        ZombieToast zt = (ZombieToast) constructEntity(
                new ZombieToastFactory(pos.get(randGen.nextInt(pos.size())), config));
        map.addEntity(zt);
        game.register(() -> zt.move(game), Game.AI_MOVEMENT, zt.getId());
    }

    public Bow buildBow() {
        return (Bow) constructEntity(new BowFactory(null, config));
    }

    public Shield buildShield() {
        return (Shield) constructEntity(new ShieldFactory(null, config));
    }

    public Entity constructEntity(Factory factory) {
        this.factory = factory;
        return factory.create();
    }

    public Entity constructEntity() {
        return factory.create();
    }

    /**
     * Sets the factory based on the type of entity
     * @param jsonEntity
     * @param config
     */
    private void setFactory(JSONObject jsonEntity, JSONObject config) {
        Position pos = new Position(jsonEntity.getInt("x"), jsonEntity.getInt("y"));

        switch (jsonEntity.getString("type")) {
        case "player":
            factory = new PlayerFactory(pos, config);
            break;
        case "zombie_toast":
            factory = new ZombieToastFactory(pos, config);
            break;
        case "zombie_toast_spawner":
            factory = new ZombieToastSpawnerFactory(pos, config);
            break;
        case "mercenary":
            factory = new MercenaryFactory(pos, config);
            break;
        case "assassin":
            factory = new AssassinFactory(pos, config);
            break;
        case "wall":
            factory = new WallFactory(pos, config);
            break;
        case "boulder":
            factory = new BoulderFactory(pos, config);
            break;
        case "switch":
            factory = new SwitchFactory(pos, config);
            break;
        case "exit":
            factory = new ExitFactory(pos, config);
            break;
        case "treasure":
            factory = new TreasureFactory(pos, config);
            break;
        case "wood":
            factory = new WoodFactory(pos, config);
            break;
        case "arrow":
            factory = new ArrowFactory(pos, config);
            break;
        case "bomb":
            factory = new BombFactory(pos, config);
            break;
        case "invisibility_potion":
            factory = new InvisibilityPotionFactory(pos, config);
            break;
        case "invincibility_potion":
            factory = new InvincibilityPotionFactory(pos, config);
            break;
        case "portal":
            factory = new PortalFactory(pos, jsonEntity);
            break;
        case "sword":
            factory = new SwordFactory(pos, config);
            break;
        case "spider":
            factory = new SpiderFactory(pos, config);
            break;
        case "door":
            factory = new DoorFactory(pos, jsonEntity);
            break;
        case "key":
            factory = new KeyFactory(pos, jsonEntity);
            break;
        default:
            factory = null;
        }
    }

}
