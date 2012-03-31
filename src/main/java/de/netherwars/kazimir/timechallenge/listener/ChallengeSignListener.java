package de.netherwars.kazimir.timechallenge.listener;

import de.netherwars.kazimir.timechallenge.TimeChallenge;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (block.getType() == (Material.SIGN_POST) || block.getType() == (Material.WALL_SIGN)) {
                Sign sign = (Sign) block.getState();
                if ("[challenge]".equalsIgnoreCase(sign.getLine(0))) {
                    this.plugin.getChallengeManager().useCheckpoint(sign, event);
                }
            }
        }
    }

    @EventHandler
    public void SignChange(SignChangeEvent event) {
        if ("[challenge]".equalsIgnoreCase(event.getLine(0))) {
            this.plugin.getChallengeManager().createCheckpoint((Sign) event.getBlock().getState(), event);
        }
    }

    @EventHandler
    public void SignDestroy(BlockBreakEvent event) {
        Material type = event.getBlock().getType();
        if (type == Material.SIGN_POST || type == Material.WALL_SIGN) {
            Sign sign = (Sign) event.getBlock().getState();
            if ("[challenge]".equalsIgnoreCase(sign.getLine(0))) {
                this.plugin.getChallengeManager().destroyCheckpoint(sign, event);
            }
        }
    }
}
