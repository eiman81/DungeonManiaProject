package dungeonmania.entities.buildables;

import org.json.JSONObject;

import dungeonmania.entities.Factory;
import dungeonmania.util.Position;

public class SceptreFactory implements Factory {
    private int mindControlDuration;

    public SceptreFactory(Position position, JSONObject config) {
        this.mindControlDuration = config.optInt("mind_control_duration");
    }

    @Override
    public Buildable create() {
        return new Sceptre(mindControlDuration);
    }
}
