package dungeonmania.entities.collectables;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class SunstoneFactory implements Factory {
    private Position position;

    public SunstoneFactory(Position position, JSONObject config) {
        this.position = position;
    }

    @Override
    public SunStone create() {
        return new SunStone(position);
    }
}
