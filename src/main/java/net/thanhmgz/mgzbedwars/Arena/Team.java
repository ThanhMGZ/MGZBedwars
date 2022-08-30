package net.thanhmgz.mgzbedwars.Arena;

import net.thanhmgz.mgzbedwars.Arena.Config.ArenaConfig;
import net.thanhmgz.mgzbedwars.Arena.Config.TeamConfig;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {

    private List<Player> players = new ArrayList<>();

    private ArenaManager arenaManager;

    private TeamConfig teamConfig;
    //                                                             bool  ; int ; int ;   bool   ;    bool   ;   int     ;  int
    private Map<String,Integer> upgrades = new HashMap<>(); //sharp ; giáp ; đào ; dragBuff ;  HealPool ; Ironforce ;  trap
    // 1       2       3      4          5          6           7(1,2,3,4)
    private boolean bed = true;

    public Team(List<Player> players,ArenaManager arenaManager,TeamConfig teamConfig) {
        this.players.addAll(players);
        this.arenaManager = arenaManager;
        this.teamConfig = teamConfig;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public TeamConfig getTeamConfig() {
        return teamConfig;
    }

    public Map<String, Integer> getUpgrades() {
        return upgrades;
    }

    public void setBed(boolean bed) {
        this.bed = bed;
    }

    public boolean isBed() {
        return bed;
    }

}
