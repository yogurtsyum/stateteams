package state.StateTeams;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.io.*;
import java.util.*;

public class Teams {
    private static JavaPlugin plugin;
    private static final HashMap<String, String> teamPlayers = new HashMap<>();
    private static ScoreboardManager manager = Bukkit.getScoreboardManager();
    private static Scoreboard scoreboard = manager.getMainScoreboard();

    public Teams(JavaPlugin plugin) {
        Teams.plugin = plugin;

        File folder = new File(plugin.getDataFolder().getAbsolutePath() + File.separator + "teams");
        if(!folder.exists()) {
            folder.mkdirs();
        }

        for(File file : folder.listFiles()) {
            String teamName = file.getName().replaceFirst("[.][^.]+$", "");
            Team team = scoreboard.getTeam(teamName);
            if(team == null) {
                team = scoreboard.registerNewTeam(teamName);
            }

            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                for(String line; (line = br.readLine()) != null;) {
                    teamPlayers.put(line.toString(), file.getName().replaceFirst("[.][^.]+$", ""));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getTeam(String uuid) {
        return teamPlayers.get(uuid);
    }
}
