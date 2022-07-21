package state.StateTeams;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class JoinEvents implements Listener {
    private static Plugin plugin;
    private static ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static Scoreboard scoreboard = manager.getMainScoreboard();

    public JoinEvents(Plugin plugin) {
        JoinEvents.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String teamName = plugin.teams.getTeam(String.valueOf(e.getPlayer().getUniqueId()));
        if(teamName == null) return;
        Team team = scoreboard.getTeam(teamName);
        team.addPlayer(e.getPlayer());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), String.format("lp user %s parent add %s", e.getPlayer().getUniqueId().toString(), teamName));
    }

    @EventHandler
    public void onPlayerLogin(AsyncPlayerPreLoginEvent e) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(e.getUniqueId());
        Boolean isWhitelisted = Bukkit.getServer().getWhitelistedPlayers().contains(offlinePlayer);
        String teamName = plugin.teams.getTeam(String.valueOf(e.getUniqueId()));
        if(teamName == null && !isWhitelisted) e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_WHITELIST, ChatColor.RED + "Sorry, you must be registered to play in State Season 2.");
    }
}
