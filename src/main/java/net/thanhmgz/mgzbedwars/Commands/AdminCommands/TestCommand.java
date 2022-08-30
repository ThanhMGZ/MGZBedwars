package net.thanhmgz.mgzbedwars.Commands.AdminCommands;

import net.thanhmgz.mgzbedwars.Arena.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements CommandExecutor {

    private WorldManager worldManager;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equals("testcommand")) {
            if (args.length == 0) {
                player.sendMessage("1");
            } else if (args[0].equalsIgnoreCase("create")) {
                if (args.length == 2){
                    if (Bukkit.getWorld(args[1]) != null) {
                        WorldManager worldManager = new WorldManager();
                        worldManager.start(args[1]);
                        this.worldManager = worldManager;
                        player.teleport(worldManager.getWorld().getSpawnLocation());
                    } else {
                        player.sendMessage("2");
                    }
                }
            } else if (args[0].equalsIgnoreCase("--unload")) {
                player.teleport(Bukkit.getWorld("world").getSpawnLocation());
                this.worldManager.unloadWorld();

            }
        }
        return true;
    }
}
