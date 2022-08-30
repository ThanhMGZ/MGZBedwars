package net.thanhmgz.mgzbedwars.Player;

import net.thanhmgz.mgzbedwars.Arena.ArenaManager;
import net.thanhmgz.mgzbedwars.MGZBedwars;
import org.bukkit.entity.Player;

public class PlayerInfo {

    private Player player;

    private PlayerInventory playerInventory;

    private PlayerStats playerStats;

    private ArenaManager arenaManager;

    public PlayerInfo(Player player) {
        this.player = player;
        this.playerStats = new PlayerStats(player);
    }

    public void init() {
        MGZBedwars.getInstance().getPlayerInfoMap().put(player,this);
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerInventory getPlayerInventory() {
        return playerInventory;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public void setArenaManager(ArenaManager arenaManager) {
        this.arenaManager = arenaManager;
    }

    public void setPlayerInventory(PlayerInventory playerInventory) {
        this.playerInventory = playerInventory;
    }
}
