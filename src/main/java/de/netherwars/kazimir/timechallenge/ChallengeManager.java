package de.netherwars.kazimir.timechallenge;

import de.netherwars.kazimir.timechallenge.objects.Challenge;
import de.netherwars.kazimir.timechallenge.objects.ChallengeProgress;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

/**
 * User: corwynt
 * Date: 30.03.12
 * Time: 23:50
 */
public class ChallengeManager {
    private HashMap<String, Challenge> challengeMap;
    private HashMap<Player, ChallengeProgress> playerList;
    private TimeChallenge plugin;

    public ChallengeManager(TimeChallenge plugin) {
        this.plugin = plugin;
        challengeMap = new HashMap<String, Challenge>();
        playerList = new HashMap<Player, ChallengeProgress>();
    }

    public Challenge getChallenge(String name) {
        if (!challengeMap.containsKey(name)) {
            //TODO: load Challenge
        }
        return challengeMap.get(name);
    }

    public void useCheckpoint(Sign checkpointSign, PlayerInteractEvent player) {

    }

    public void createCheckpoint(Sign checkpointSign, SignChangeEvent evt) {
        Player player = evt.getPlayer();
        String line1 = checkpointSign.getLine(1);
        Challenge c;
        if (line1.isEmpty()) {
            return;
        }
        if (!challengeMap.containsKey(line1)) {
            if (plugin.loadChallenge(line1) != null) {
                player.sendMessage(ChatColor.RED + "[TimeChallenge] Challenge already exists. Please try another name!");
                evt.setCancelled(true);
                return;
            } else {
                c = new Challenge(line1, player.getName());
                challengeMap.put(line1, c);
            }
        } else {
            c = challengeMap.get(line1);
            if (!player.getName().equals(c.getCreator())) {
                player.sendMessage(ChatColor.RED + "[TimeChallenge] Your not allowed to edit somebody others challenge!");
                evt.setCancelled(true);
                return;
            }
        }
    }

    public void destroyCheckpoint(Sign checkpointSign, BlockBreakEvent player) {

    }
}
