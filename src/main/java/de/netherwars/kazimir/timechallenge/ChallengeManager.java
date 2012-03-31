package de.netherwars.kazimir.timechallenge;

import de.netherwars.kazimir.timechallenge.objects.Challenge;
import de.netherwars.kazimir.timechallenge.objects.ChallengeProgress;
import de.netherwars.kazimir.timechallenge.objects.Checkpoint;
import de.netherwars.kazimir.timechallenge.objects.CheckpointTypes;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;

/**
 * User: corwynt
 * Date: 30.03.12
 * Time: 23:50
 */
public class ChallengeManager {
    private static final String PRE = "[TimeChallenge] ";

    private HashMap<Player, Challenge> challengeCreatorMap;
    private HashMap<Player, ChallengeProgress> playerList;
    private TimeChallenge plugin;

    public ChallengeManager(TimeChallenge plugin) {
        this.plugin = plugin;
        playerList = new HashMap<Player, ChallengeProgress>();
        challengeCreatorMap = new HashMap<Player, Challenge>();
    }


    public void useCheckpoint(Sign checkpointSign, PlayerInteractEvent evt) {
        Player player = evt.getPlayer();
        if (challengeCreatorMap.containsKey(player)) {
            Challenge c = challengeCreatorMap.get(player);
            Checkpoint lastCp = c.getLastCheckpoint();
            if (lastCp.getxPos() == checkpointSign.getX() && lastCp.getyPos() == checkpointSign.getY()
                    && lastCp.getzPos() == checkpointSign.getZ()) {
                lastCp.setType(CheckpointTypes.END);
                checkpointSign.setLine(0, "[CHALLENGE]");
                checkpointSign.setLine(1, ChatColor.GREEN + "END");
                checkpointSign.setLine(2, "");
                checkpointSign.setLine(3, c.getName());
                checkpointSign.update();
                Object obj = challengeCreatorMap.remove(player);
                player.sendMessage("removed " + obj);
                plugin.saveChallenge(c);
            }
        } else {
            play(checkpointSign, player);
            //TODO: Start
        }
    }

    public void createCheckpoint(Sign checkpointSign, SignChangeEvent evt) {
        Player player = evt.getPlayer();
        String line1 = evt.getLine(1);
        Challenge c;
        if (line1.isEmpty()) {
            player.sendMessage(ChatColor.RED + PRE + "Second line must not be empty!");
            evt.setCancelled(true);
            return;
        }

        boolean challengeGetsCreated = false;
        Iterator it = challengeCreatorMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Player, Challenge> pairs = (Map.Entry) it.next();
            if (line1.equals(pairs.getValue().getName())) {
                challengeGetsCreated = true;
            }
            it.remove(); // avoids a ConcurrentModificationException
        }
        if (challengeGetsCreated || plugin.loadChallenge(line1) != null) {
            player.sendMessage(ChatColor.RED + PRE + "Challenge already exists. Please try another name!");
            evt.setCancelled(true);
            return;
        } else {
            c = new Challenge(line1, player.getName());
            challengeCreatorMap.put(player, c);
            Checkpoint chk = new Checkpoint(CheckpointTypes.START, checkpointSign.getX(), checkpointSign.getY(), checkpointSign.getZ());
            c.addCheckpoint(chk);
            evt.setLine(0, "[CHALLENGE]");
            evt.setLine(1, ChatColor.GOLD + line1);
            evt.setLine(2, "by");
            evt.setLine(3, player.getName());
            player.sendMessage(ChatColor.GREEN + PRE + "Now hit some checkpoints and the end!");
        }
    }


    public void destroyCheckpoint(Sign checkpointSign, BlockBreakEvent player) {

    }


    public void addCheckpoint(Sign sign, PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (challengeCreatorMap.containsKey(player)) {
            Challenge c = challengeCreatorMap.get(player);
            Checkpoint chk = new Checkpoint(CheckpointTypes.CHECKPOINT, sign.getX(), sign.getY(), sign.getZ());
            c.addCheckpoint(chk);
            sign.setLine(0, "[CHALLENGE]");
            sign.setLine(1, ChatColor.GREEN + "CHECKPOINT " + (c.getCheckpointCount() - 1));
            sign.setLine(2, "");
            sign.setLine(3, c.getName());
            sign.update();
        }
    }

    private void play(Sign checkpointSign, Player player) {
        if (playerList.containsKey(player)) {
            player.sendMessage(ChatColor.GREEN + PRE + "You are already playing "
                    + playerList.get(player).getChallenge().getName() + "!");
        } else {
            String line1 = checkpointSign.getLine(1);
            TimeChallenge.getLog().log(Level.INFO, line1);
        }
    }
}
