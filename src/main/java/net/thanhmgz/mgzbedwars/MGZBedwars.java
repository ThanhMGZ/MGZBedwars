package net.thanhmgz.mgzbedwars;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.Keybinds;
import net.thanhmgz.mgzbedwars.Arena.Config.ArenaConfig;
import net.thanhmgz.mgzbedwars.Commands.AdminCommands.TestCommand;
import net.thanhmgz.mgzbedwars.Files.ArenaFile;
import net.thanhmgz.mgzbedwars.Player.PlayerInfo;
import net.thanhmgz.mgzbedwars.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.ItemList;

import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.Map;

public final class MGZBedwars extends JavaPlugin {

    private static MGZBedwars plugin;

    private Map<Player, PlayerInfo> playerInfoMap = new HashMap<>();
    private Map<String, ArenaFile> arenaFileMap = new HashMap<>();

    private Map<ArenaFile, ArenaConfig> arenaConfigMap = new HashMap<>();

    private Database database;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        saveConfig();
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerInfo playerInfo =  new PlayerInfo(player);
            playerInfo.init();
        }
        this.database = new Database(this);
        this.database.load();

        getCommand("testcommand").setExecutor(new TestCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static MGZBedwars getInstance() {
        return plugin;
    }

    public Map<Player, PlayerInfo> getPlayerInfoMap() {
        return playerInfoMap;
    }

    public Map<String, ArenaFile> getArenaFileMap() {
        return arenaFileMap;
    }

    public Map<ArenaFile, ArenaConfig> getArenaConfigMap() {
        return arenaConfigMap;
    }
}
