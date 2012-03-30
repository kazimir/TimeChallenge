package de.netherwars.kazimir.timechallenge;

import de.netherwars.kazimir.timechallenge.objects.Challenge;
import de.netherwars.kazimir.timechallenge.objects.ChallengeProgress;
import org.bukkit.block.Sign;

import java.util.HashMap;

/**
 * User: corwynt
 * Date: 30.03.12
 * Time: 23:50
 */
public class ChallengeManager {
    private HashMap<String,Challenge> challengeMap;
    private HashMap<String,ChallengeProgress> playerList;
    public ChallengeManager(){
        challengeMap=new HashMap<String, Challenge>();
        playerList=new HashMap<String, ChallengeProgress>();
    }
    public Challenge getChallenge(String name){
        if(!challengeMap.containsKey(name)){
            //TODO: Challenge laden
        }
        return challengeMap.get(name);
    }
    public void useCheckpoint(Sign checkpointSign,String playerName){

    }
}
