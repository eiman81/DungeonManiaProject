package dungeonmania.entities;

import org.json.JSONObject;

import dungeonmania.util.Position;

public class ExitFactory implements Factory {
    private Position position;

    public ExitFactory(Position position, JSONObject config) {
        this.position = position;
    }

    @Override
    public Exit create() {
        return new Exit(position);
    }
}
