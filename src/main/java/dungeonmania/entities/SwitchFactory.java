package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.util.Position;

public class SwitchFactory implements Factory {
    private Position position;

    public SwitchFactory(Position position, JSONObject config) {
        this.position = position;
    }

    @Override
    public Switch create() {
        return new Switch(position);
    }
}
