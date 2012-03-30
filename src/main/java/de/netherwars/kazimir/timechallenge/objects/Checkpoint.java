package de.netherwars.kazimir.timechallenge.objects;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.Map;

/**
 * User: kazimir
 * Date: 30.03.12
 * Time: 21:19
 */
public class Checkpoint implements ConfigurationSerializable {
    private CheckpointTypes type;
    private int xPos;
    private int yPos;
    private int zPos;

    @Override
    public Map<String, Object> serialize() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
