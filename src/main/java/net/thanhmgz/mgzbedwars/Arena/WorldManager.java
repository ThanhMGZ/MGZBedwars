package net.thanhmgz.mgzbedwars.Arena;

import net.thanhmgz.mgzbedwars.MGZBedwars;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.material.Bed;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;

public class WorldManager {

    public String id = String.valueOf(System.currentTimeMillis());

    private World world;

    public void start(String wname) {
        copy(wname);
        this.world = Bukkit.createWorld(new WorldCreator(MGZBedwars.getInstance().getDataFolder() + "/InGames/" + id));

    }

    public World getWorld() {
        return world;
    }

    public void unloadWorld() {
        Bukkit.unloadWorld(world,false);
        deleteWorld();
    }

    public void copy(String backup) {
        File srcDir = new File(Bukkit.getServer().getWorldContainer(), backup);
        if (!srcDir.exists()) {
            Bukkit.getLogger().warning("Backup does not exist!");
            return;
        }
        File destDir = new File(MGZBedwars.getInstance().getDataFolder(), "InGames/" + id);
        try {
            FileUtils.copyDirectory(srcDir, destDir);
            for (File file : destDir.listFiles())
                if (file.isFile())
                    if (file.getName().equalsIgnoreCase("uid.dat"))
                        file.delete();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteWorld() {
        File dir = new File(MGZBedwars.getInstance().getDataFolder(), "InGames/" + id);
        try {
            FileUtils.deleteDirectory(dir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public String getId() {
        return id;
    }

}
