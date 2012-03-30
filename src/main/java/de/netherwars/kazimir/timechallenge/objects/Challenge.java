package de.netherwars.kazimir.timechallenge.objects;

import de.netherwars.kazimir.timechallenge.TimeChallenge;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Logger;

/**
 * User: kazimir
 * Date: 30.03.12
 * Time: 21:16
 */
public class Challenge implements ConfigurationSerializable{
    private static final Logger log = Logger.getLogger("Minecraft.TimeChallenge");
    private String name;
    private Vector<Checkpoint> checkpoints = new Vector<Checkpoint>();

    public Challenge(Map<String, Object> map){
       if(map.containsKey("name")){
           this.name = String.valueOf(map.get("name"));
       }
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        return null;
    }
}
