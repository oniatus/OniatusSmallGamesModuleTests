package org.terasology.oniatussmallgames.menschaergeredichnicht;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.math.geom.Vector3i;
import org.terasology.moduletestingenvironment.ModuleTestingEnvironment;
import org.terasology.oniatussmallgames.menschaergeredichnicht.terasology.GenerateBoardBlocksEvent;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class BoardSpawnTest extends ModuleTestingEnvironment {

    public static final String MODULE_NAME = "OniatusSmallGames";

    @Override
    public Set<String> getDependencies() {
        return Sets.newHashSet("engine", MODULE_NAME);
    }

    @Override
    public String getWorldGeneratorUri() {
        return MODULE_NAME + ":LobbyWorldGenerator";
    }

    @Test
    public void shouldGenerateBoardOnEvent() {
        //board is 13x13, generate chunks first, otherwise everything will be unloaded
        forceAndWaitForGeneration(new Vector3i(0,10,0));
        forceAndWaitForGeneration(new Vector3i(13,10,13));

        WorldProvider worldProvider = getHostContext().get(WorldProvider.class);
        EntityRef worldEntity = worldProvider.getWorldEntity();
        worldEntity.send(new GenerateBoardBlocksEvent(new Vector3i(0, 10, 0)));
        Block upperLeftCorner = worldProvider.getBlock(0, 10, 0);
        assertEquals(MODULE_NAME + ":boardDefault", upperLeftCorner.getURI().toString());
        Block lowerRightCorner = worldProvider.getBlock(12, 10, 12);
        assertEquals(MODULE_NAME + ":boardDefault", lowerRightCorner.getURI().toString());
        Block field51 = worldProvider.getBlock(5, 10, 8);
        assertEquals(MODULE_NAME + ":boardSW", field51.getURI().toString());
    }
}