package de.netherwars.kazimir.timechallenge.listener;

import de.netherwars.kazimir.timechallenge.TimeChallenge;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * User: kazimir
 * Date: 30.03.12
 * Time: 21:22
 */
public class ChallengeSignListener implements Listener {
    public ChallengeSignListener(TimeChallenge plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void SignClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType().equals(Material.SIGN)) {
                Sign sign = (Sign) event.getClickedBlock();
                if (sign.getLine(0).toLowerCase().equals("[challenge]")) {
                    event.getPlayer().sendMessage("VOLL DIE CHALLENGE");
                }
            }
        }
    }

    @EventHandler
    public void SignChange(SignChangeEvent event) {
        if (event.getLine(0).toLowerCase().equals("[challenge]")) {
            event.getPlayer().sendMessage("DAS WIRD MAL VOLL DIE CHALLENGE");
        }
    }
}
