package de.netherwars.kazimir.timechallenge.listener;

import de.netherwars.kazimir.timechallenge.TimeChallenge;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * User: kazimir
 * Date: 30.03.12
 * Time: 21:22
 */
public class ChallengeSignListener implements Listener {
    private TimeChallenge plugin;

    public ChallengeSignListener(TimeChallenge plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    @EventHandler
    public void SignClick(PlayerInteractEvent event) {
        Player pl = event.getPlayer();
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getClickedBlock().getType() == (Material.SIGN_POST) || event.getClickedBlock().getType() == (Material.WALL_SIGN)) {
                Sign sign = (Sign) event.getClickedBlock().getState();
                if (sign.getLine(0).equalsIgnoreCase("[challenge]")) {
                    this.plugin.getChallengeManager().useCheckpoint(sign, event.getPlayer().getName());
                }
            }
        }
    }

    @EventHandler
    public void SignChange(SignChangeEvent event) {
        if (event.getLine(0).equalsIgnoreCase("[challenge]")) {
            this.plugin.getChallengeManager().createCheckpoint((Sign) event.getBlock().getState(), event.getPlayer().getName());
        }
    }

    @EventHandler
    public void SignDestroy(BlockBreakEvent event) {
        Material type = event.getBlock().getType();
        if (type == Material.SIGN_POST || type == Material.WALL_SIGN) {
            Sign sign = (Sign) event.getBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase("[challenge]")) {
                this.plugin.getChallengeManager().useCheckpoint(sign, event.getPlayer().getName());
            }
        }
    }
}
