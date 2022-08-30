package net.thanhmgz.mgzbedwars.Arena.Config;

import org.bukkit.Location;

import java.util.List;
import java.util.Map;

public abstract class TeamConfig {

    public abstract Map<String,Integer> teamId();

    public abstract Map<Integer, Location> upgradesShopkeepoer();

    public abstract Map<Integer, Location> itemShopKeeper();

    public abstract Map<Integer, Location> spawnLocation();

    public abstract Map<Integer,Integer> spawnSize();

    public abstract Map<Integer,Location> islandTopper();

    public abstract Map<Integer,Location> bedLocationHead();

    public abstract Map<Integer,Location> bedLocationFoot();

    public abstract Map<Integer,Location> islandCenter();

    public abstract Map<Integer,Integer> islandSize();

    public abstract Map<Integer,Location> chest();

    public abstract Map<Integer,String> color();



}
