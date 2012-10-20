package de.bangl.wgdf.listener;

import de.bangl.wgdf.Utils;
import de.bangl.wgdf.WGDamageFlagPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

/**
 *
 * @author BangL
 */
public class PlayerDamageListener implements Listener {
    WGDamageFlagPlugin plugin;

    public PlayerDamageListener(WGDamageFlagPlugin plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByBlockEvent event) {
        
        // Only handle if player and dmg cause is not null.
        if(event.getEntity() instanceof Player
                && event.getCause() != null) {
            // Cancel if dmg cause is denied here.
            if (!Utils.dmgAllowedAtLocation(plugin.getWGP(), event.getCause(), event.getEntity().getLocation())) {
                event.setCancelled(true);
            }
        }
    }
}
