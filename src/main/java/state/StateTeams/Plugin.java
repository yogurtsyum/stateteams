package state.StateTeams;

import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
    public static Plugin plugin;
    public static Teams teams;

    @Override
    public void onEnable() {
        plugin = this;
        this.getLogger().info("This plugin has been enabled.");
        this.getServer().getPluginManager().registerEvents(new JoinEvents(this), this);
        teams = new Teams(this);
    }
}
