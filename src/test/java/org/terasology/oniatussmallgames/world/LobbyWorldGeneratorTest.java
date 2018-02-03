package org.terasology.oniatussmallgames.world;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.terasology.math.geom.Vector3f;
import org.terasology.math.geom.Vector3i;
import org.terasology.moduletestingenvironment.ModuleTestingEnvironment;
import org.terasology.world.WorldProvider;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class LobbyWorldGeneratorTest extends ModuleTestingEnvironment {

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet("engine", "OniatusSmallGames");
    }

    @Override
    public String getWorldGeneratorUri() {
        return "OniatusSmallGames:LobbyWorldGenerator";
    }

    @Test
    public void shouldGenerateGlassFloor() throws Exception {
        WorldProvider worldProvider = getHostContext().get(WorldProvider.class);
        int y = 0;
        for (int x = -30; x <= 30; x++) {
            for (int z = -30; z <= 30; z++) {
                forceAndWaitForGeneration(new Vector3i(x, y, z));
                assertEquals("engine:air", worldProvider.getBlock(new Vector3i(x, y + 2, z)).getURI().toString());
                assertEquals("engine:air", worldProvider.getBlock(new Vector3i(x, y + 1, z)).getURI().toString());
                assertEquals("OniatusSmallGames:simpleGlass", worldProvider.getBlock(new Vector3i(x, y, z)).getURI().toString());
            }
        }
    }
}
