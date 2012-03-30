package de.netherwars.kazimir.timechallenge.objects;


import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * User: kazimir
 * Date: 30.03.12
 * Time: 21:16
 */
@SerializableAs("Timechallenge.Challenge")
public class Challenge implements ConfigurationSerializable{
    private static final Logger log = Logger.getLogger("Minecraft.TimeChallenge");
    private String name;
    private ArrayList<Checkpoint> checkpoints = new ArrayList<Checkpoint>();

    public Challenge(){

    }

    public Challenge(Map<String, Object> map){
       if(map.containsKey("name")){
           this.name = String.valueOf(map.get("name"));
       }
        if(map.containsKey("checkpoints")){
            this.checkpoints = (ArrayList<Checkpoint>)map.get("checkpoints");
        }
    }

    @Override
    public Map<String, Object> serialize() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("checkpoints", checkpoints);
        return map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Checkpoint> getCheckpoints() {
        return checkpoints;
    }
}
