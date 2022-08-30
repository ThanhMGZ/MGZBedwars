package net.thanhmgz.mgzbedwars.Arena;

import net.thanhmgz.mgzbedwars.Arena.Config.ArenaConfig;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ArenaManager {

    public abstract void start();

    public abstract void stop();


    private List<Player> players = new ArrayList<>();

    private ArenaConfig arenaConfig;

    private List<Team> teams = new ArrayList<>();

    //TODO ingame stats;
    private Map<Player,Integer> kills = new HashMap<>();
    private Map<Player,Integer> deaths = new HashMap<>();
    private Map<Player,Integer> bed = new HashMap<>();
    private Map<Player,Integer> block = new HashMap<>();
    private List<Player> dead = new ArrayList<>();

    public ArenaConfig getArenaConfig() {
        return arenaConfig;
    }

    public Map<Player, Integer> getKills() {
        return kills;
    }

    public Map<Player, Integer> getDeaths() {
        return deaths;
    }

    public Map<Player, Integer> getBed() {
        return bed;
    }

    public Map<Player, Integer> getBlock() {
        return block;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getDead() {
        return dead;
    }
}
