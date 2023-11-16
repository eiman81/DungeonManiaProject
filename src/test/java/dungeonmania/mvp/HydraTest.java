package dungeonmania.mvp;

import dungeonmania.DungeonManiaController;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.response.models.EntityResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HydraTest {
    @Test
    @Tag("19-1")
    @DisplayName("Testing hydras movement")
    public void movement() {
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_hydraTest_movement", "c_hydraTest_movement");

        assertEquals(1, getHydra(res).size());

        // Teams may assume that random movement includes choosing to stay still, so we should just
        // check that they do move at least once in a few turns
        boolean hydraMoved = false;
        Position prevPosition = getHydra(res).get(0).getPosition();
        for (int i = 0; i < 5; i++) {
            res = dmc.tick(Direction.UP);
            if (!prevPosition.equals(getHydra(res).get(0).getPosition())) {
                hydraMoved = true;
                break;
            }
        }
        assertTrue(hydraMoved);
    }

    @Test
    @Tag("19-2")
    @DisplayName("Testing hydras cannot move through closed doors and walls")
    public void doorsAndWalls() {
        //  W   W   W   W
        //  P   W   Z   W
        //      W   D   W
        //          K
        DungeonManiaController dmc = new DungeonManiaController();
        DungeonResponse res = dmc.newGame("d_hydraTest_doorsAndWalls", "c_hydraTest_doorsAndWalls");
        assertEquals(1, getHydra(res).size());
        Position position = getHydra(res).get(0).getPosition();
        res = dmc.tick(Direction.UP);
        assertEquals(position, getHydra(res).get(0).getPosition());
    }

    private List<EntityResponse> getHydra(DungeonResponse res) {
        return TestUtils.getEntities(res, "hydra");
    }

}
