package de.bangl.wgdf;

import com.mewin.WGCustomFlags.WGCustomFlagsPlugin;
import com.mewin.WGCustomFlags.flags.CustomSetFlag;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.EnumFlag;
import de.bangl.wgdf.listener.PlayerDamageListener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author BangL
 */
public class WGDamageFlagPlugin extends JavaPlugin {
    public static final EnumFlag DAMAGE_CAUSE_FLAG = new EnumFlag("damage-cause", EntityDamageEvent.DamageCause.class);
    public static final CustomSetFlag ALLOW_DAMAGE_FLAG = new CustomSetFlag("allow-damage", DAMAGE_CAUSE_FLAG);
    public static final CustomSetFlag DENY_DAMAGE_FLAG = new CustomSetFlag("deny-damage", DAMAGE_CAUSE_FLAG);

    private WGCustomFlagsPlugin pluginWGCustomFlags;
    private WorldGuardPlugin pluginWorldGuard;
    private PlayerDamageListener listenerplayerDamage;

    public WorldGuardPlugin getWGP() {
        return pluginWorldGuard;
    }

    public WGCustomFlagsPlugin getWGCFP() {
        return pluginWGCustomFlags;
    }

    @Override
    public void onEnable() {
        pluginWGCustomFlags = Utils.getWGCustomFlags(this);
        pluginWorldGuard = Utils.getWorldGuard(this);
        listenerplayerDamage = new PlayerDamageListener(this);

        pluginWGCustomFlags.addCustomFlag(ALLOW_DAMAGE_FLAG);
        pluginWGCustomFlags.addCustomFlag(DENY_DAMAGE_FLAG);
    }

    @Override
    public void onDisable() {
        pluginWGCustomFlags = null;
        pluginWorldGuard = null;
        listenerplayerDamage = null;
    }
}
