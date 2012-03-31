package de.netherwars.kazimir.timechallenge.objects;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

/**
 * User: kazimir
 * Date: 30.03.12
 * Time: 21:19
 */
@SerializableAs("Timechallenge.Checkpoint")
public class Checkpoint implements ConfigurationSerializable {
    private CheckpointTypes type;
    private int xPos;
    private int yPos;
    private int zPos;

    public Checkpoint() {
    }

    public Checkpoint(CheckpointTypes type, int xPos, int yPos, int zPos) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    public Checkpoint(Map<String, Object> map) {
        if (map.containsKey("type")) {
            this.type = CheckpointTypes.valueOf(String.valueOf(map.get("type")));
        }
        if (map.containsKey("xPos")) {
            this.xPos = Integer.parseInt(String.valueOf(map.get("xPos")));
        }
        if (map.containsKey("yPos")) {
            this.yPos = Integer.parseInt(String.valueOf(map.get("yPos")));
        }
        if (map.containsKey("zPos")) {
            this.zPos = Integer.parseInt(String.valueOf(map.get("zPos")));
        }
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("type", type.toString());
        map.put("xPos", xPos);
        map.put("yPos", yPos);
        map.put("zPos", zPos);
        return map;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getzPos() {
        return zPos;
    }

    public CheckpointTypes getType() {
        return type;
    }

    public void setType(CheckpointTypes type) {
        this.type = type;
    }
}
