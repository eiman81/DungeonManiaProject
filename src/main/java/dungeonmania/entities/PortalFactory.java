package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.util.Position;

public class PortalFactory implements Factory {
    private Position position;
    private ColorCodedType color;

    public PortalFactory(Position position, JSONObject config) {
        this.color = ColorCodedType.valueOf(config.getString("colour"));
        this.position = position;
    }

    @Override
    public Portal create() {
        return new Portal(position, color);
    }
}
