package net.thanhmgz.mgzbedwars.Utils;

import net.thanhmgz.mgzbedwars.Arena.Config.ArenaConfig;
import net.thanhmgz.mgzbedwars.Files.ArenaFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class Utils {

    public static boolean isInvFull(Inventory inventory) {
        ItemStack[] itemStacks = inventory.getContents();
        for (int i = 0; i < itemStacks.length; i++) {
            if (itemStacks[i] == null) {
                return false;
            }
        }
        return true;
    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static Location getLocationByString(String s) {
        Location location = null;
        if (s.split(";")[4] == null) {
            location = new Location(Bukkit.getWorld(s.split(";")[0]), Integer.parseInt(s.split(";")[1]), Integer.parseInt(s.split(";")[2]), Integer.parseInt(s.split(";")[3]));
        } else {
            location = new Location(Bukkit.getWorld(s.split(";")[0]), Integer.parseInt(s.split(";")[1]), Integer.parseInt(s.split(";")[2]), Integer.parseInt(s.split(";")[3]),
                    Float.parseFloat(s.split(";")[4]), Float.parseFloat(s.split(";")[5]));
        }
        return location;
    }

    public static List<Location> getLocationByString(List<String> ss) {
        List<Location> location = new ArrayList<>();
        for (String s : ss) {
            if (s.split(";")[4] == null) {
                location.add(new Location(Bukkit.getWorld(s.split(";")[0]), Integer.parseInt(s.split(";")[1]), Integer.parseInt(s.split(";")[2]), Integer.parseInt(s.split(";")[3])));
            } else {
                location.add(new Location(Bukkit.getWorld(s.split(";")[0]), Integer.parseInt(s.split(";")[1]), Integer.parseInt(s.split(";")[2]), Integer.parseInt(s.split(";")[3]),
                        Float.parseFloat(s.split(";")[4]), Float.parseFloat(s.split(";")[5])));
            }
        }
        return location;
    }

    public static Location getLocationByString2(ArenaConfig arenaConfig,String string) {
        Location location = null;
        if (arenaConfig.getOriginWorldName() != null) {
            if (Bukkit.getWorld(arenaConfig.getOriginWorldName()) != null) {
                int i = (int) string.chars().filter(num -> num == ';').count();
                if (i < 2)
                    return null;
                if (i > 2)
                    return getLocationByString(string);
                int x = Integer.parseInt(string.split(";")[0]);
                int y = Integer.parseInt(string.split(";")[1]);
                int z = Integer.parseInt(string.split(";")[2]);
                location = new Location(Bukkit.getWorld(arenaConfig.getArenaFile().getConfig().getString("OriginWorldName")),x,y,z);
            }
        }
        return location;
    }
    public static List<Location> getLocationByString2(ArenaConfig arenaConfig,List<String> string) {
        List<Location> locations = new ArrayList<>();
        if (arenaConfig.getOriginWorldName() != null) {
            if (Bukkit.getWorld(arenaConfig.getOriginWorldName()) != null) {
                for (String str : string) {
                    int i = (int) str.chars().filter(num -> num == ';').count();
                    if (i < 2)
                        return null;
                    if (i > 2)
                        return getLocationByString(string);
                    int x = Integer.parseInt(str.split(";")[0]);
                    int y = Integer.parseInt(str.split(";")[1]);
                    int z = Integer.parseInt(str.split(";")[2]);
                    Location location = new Location(Bukkit.getWorld(arenaConfig.getArenaFile().getConfig().getString("OriginWorldName")), x, y, z);
                    locations.add(location);
                }
            }
        }
        return locations;
    }

    public static String locationtoString(Location location, boolean world, boolean pitchYaw) {
        if (location == null)
            return null;
        if (pitchYaw) {
            if (world) {
                return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getPitch() + ";" + location.getYaw();
            } else {
                return location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getPitch() + ";" + location.getYaw();
            }
        } else {
            if (world) {
                return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ();
            } else {
                return location.getX() + ";" + location.getY() + ";" + location.getZ() + ";";
            }
        }
    }

    public static List<String> locationtoString(List<Location> location, boolean world, boolean pitchYaw) {
        List<String> list = new ArrayList<>();
        for (Location s : location) {
            list.add(locationtoString(s, world, pitchYaw));
        }
        return list;
    }

    public static ArrayList<Item> getNearbyItems(Block start, int radius) {
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<Entity> entities;

        entities = (ArrayList<Entity>) Bukkit.getWorld("world").getEntities();

        for (Entity e : entities) {
            if (e.getLocation().distance(start.getLocation()) <= radius && e.getLocation().getBlock() != start.getLocation().getBlock()) {
                if (e instanceof Item) items.add((Item) e);
            }
        }
        return items;
    }

    public static int randomInt(int min, int max) {
        max++;
        return (new Random()).nextInt(max - min) + min;
    }

}
