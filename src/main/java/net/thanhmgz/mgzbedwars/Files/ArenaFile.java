package net.thanhmgz.mgzbedwars.Files;

import net.thanhmgz.mgzbedwars.MGZBedwars;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class ArenaFile {

    private File file;
    private FileConfiguration config;
    private String name;

    public ArenaFile(String name) {
        this.name = name;
    }

    public void init() {
        this.file = new File(MGZBedwars.getInstance().getDataFolder(),"Arenas/" + name + ".yml");
        if (!file.exists())
            save();
        this.config = YamlConfiguration.loadConfiguration(file);
        MGZBedwars.getInstance().getArenaFileMap().put(name,this);
    }

    public File getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public FileConfiguration getConfig() {
        if (config != null) {
            return config;
        } else {
            return YamlConfiguration.loadConfiguration(getFile());
        }
    }

    public void save() {
        if (file != null) {
            try {
                YamlConfiguration.loadConfiguration(file).save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            init();
        }
    }

}
