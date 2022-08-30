package net.thanhmgz.mgzbedwars.Arena.Config;

import org.bukkit.Location;

import java.util.List;
import java.util.Map;

public abstract class GeneratorConfig {

    public abstract List<Location> emeraldGeneratorLocations();

    public abstract List<Location> diamondGeneratorLocations();

    public abstract Map<String,List<String>> emeraldTierConfig();

    public abstract Map<String,List<String>> diamondTierConfig();


}
