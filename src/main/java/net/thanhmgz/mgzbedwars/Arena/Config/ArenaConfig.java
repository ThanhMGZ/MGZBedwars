package net.thanhmgz.mgzbedwars.Arena.Config;

import net.thanhmgz.mgzbedwars.Files.ArenaFile;
import net.thanhmgz.mgzbedwars.MGZBedwars;
import net.thanhmgz.mgzbedwars.Utils.Utils;
import org.bukkit.Location;

import java.util.*;

public class ArenaConfig {

    private GeneratorConfig generatorConfig;

    private TeamConfig teamConfig;
    private ArenaFile arenaFile;

    private boolean status;

    private String name;

    private String originWorldName;

    private int respawnTime;

    private Location waitingRoom;

    private Location waitingRoomPos1;

    private Location waitingRoomPos2;

    private List<Location> islandSprays;

    private List<Location> glyphs;

    private int PPT;

    public ArenaConfig(ArenaFile arenaFile) {
        this.arenaFile = arenaFile;
    }

    public void init() {
        MGZBedwars.getInstance().getArenaConfigMap().put(arenaFile,this);
        status = false;
        this.name = arenaFile.getConfig().getString("Name");
        this.originWorldName = arenaFile.getConfig().getString("OriginWorldName");
        this.respawnTime = arenaFile.getConfig().getInt("RespawnTime");
        this.waitingRoom = Utils.getLocationByString2(this,arenaFile.getConfig().getString("Location.WaitingRoom"));
        this.waitingRoomPos1 = Utils.getLocationByString2(this,arenaFile.getConfig().getString("Location.WaitingRoomPos1"));
        this.waitingRoomPos2 = Utils.getLocationByString2(this,arenaFile.getConfig().getString("Location.WaitingRoomPos2"));
        this.islandSprays = new ArrayList<>();
        this.islandSprays.addAll(Objects.requireNonNull(Utils.getLocationByString2(this, arenaFile.getConfig().getStringList("Location.IslandSprays"))));
        this.glyphs = new ArrayList<>();
        this.glyphs.addAll(Objects.requireNonNull(Utils.getLocationByString2(this, arenaFile.getConfig().getStringList("Location.Glyphs"))));
        this.PPT = arenaFile.getConfig().getInt("PPT");


        this.generatorConfig = new GeneratorConfig() {
            @Override
            public List<Location> emeraldGeneratorLocations() {
                List<Location> locations = new ArrayList<>();
                for (String s : getThis().getArenaFile().getConfig().getStringList("Generator.Emerald.Locations")) {
                    locations.add(Utils.getLocationByString2(getThis(),s));
                }
                return locations;
            }

            @Override
            public List<Location> diamondGeneratorLocations() {
                List<Location> locations = new ArrayList<>();
                for (String s : getThis().getArenaFile().getConfig().getStringList("Generator.Emerald.Diamond")) {
                    locations.add(Utils.getLocationByString2(getThis(),s));
                }
                return null;
            }

            @Override
            public Map<String, List<String>> emeraldTierConfig() { //tOdo; {tierid} {cooldown} {limit} {start}
                Map<String,List<String>> map = new HashMap<>();
                for (String str1 : getArenaFile().getConfig().getConfigurationSection("Generator.Emerald.Tier").getKeys(false)) {
                    List<String> list = new ArrayList<>();
                    for (String str2 : getArenaFile().getConfig().getConfigurationSection("Generator.Emerald.Tier." + str1).getKeys(false)) {
                        list.add(arenaFile.getConfig().getString("Generator.Emerald.Tier." + str1 + "." + str2));
                    }
                    map.put(str1,list);
                }
                return map;
            }

            @Override
            public Map<String, List<String>> diamondTierConfig() {
                Map<String,List<String>> map = new HashMap<>();
                for (String str1 : getArenaFile().getConfig().getConfigurationSection("Generator.Diamond.Tier").getKeys(false)) {
                    List<String> list = new ArrayList<>();
                    for (String str2 : getArenaFile().getConfig().getConfigurationSection("Generator.Diamond.Tier." + str1).getKeys(false)) {
                        list.add(arenaFile.getConfig().getString("Generator.Diamond.Tier." + str1 + "." + str2));
                    }
                    map.put(str1,list);
                }
                return map;
            }
        };
        this.teamConfig = new TeamConfig() {
            @Override
            public Map<String, Integer> teamId() {
                int id = 0;
                Map<String,Integer> map = new HashMap<>();
                for (String s : getArenaFile().getConfig().getConfigurationSection("TeamConfig").getKeys(false)) {
                    map.put(s,id);
                    id++;
                }
                return map;
            }

            @Override
            public Map<Integer, Location> upgradesShopkeepoer() {
                Map<Integer,Location> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),Utils.getLocationByString2(getThis(),arenaFile.getConfig().getString("TeamConfig." + s + ".UpgradesShopkeepoer")));
                }
                return map;
            }

            @Override
            public Map<Integer, Location> itemShopKeeper() {
                Map<Integer,Location> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),Utils.getLocationByString2(getThis(),arenaFile.getConfig().getString("TeamConfig." + s + ".ItemShopKeeper")));
                }
                return map;
            }

            @Override
            public Map<Integer, Location> spawnLocation() {
                Map<Integer,Location> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),Utils.getLocationByString2(getThis(),arenaFile.getConfig().getString("TeamConfig." + s + ".SpawnLocation")));
                }
                return map;
            }

            @Override
            public Map<Integer, Integer> spawnSize() {
                Map<Integer,Integer> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),arenaFile.getConfig().getInt("TeamConfig." + s + ".SpawnSize"));
                }
                return map;
            }

            @Override
            public Map<Integer, Location> islandTopper() {
                Map<Integer,Location> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),Utils.getLocationByString2(getThis(),arenaFile.getConfig().getString("TeamConfig." + s + ".IslandTopper")));
                }
                return map;
            }

            @Override
            public Map<Integer, Location> bedLocationHead() {
                Map<Integer,Location> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),Utils.getLocationByString2(getThis(),arenaFile.getConfig().getString("TeamConfig." + s + ".BedLocationHead")));
                }
                return map;
            }

            @Override
            public Map<Integer, Location> bedLocationFoot() {
                Map<Integer,Location> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),Utils.getLocationByString2(getThis(),arenaFile.getConfig().getString("TeamConfig." + s + ".BedLocationFoot")));
                }
                return map;
            }

            @Override
            public Map<Integer, Location> islandCenter() {
                Map<Integer,Location> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),Utils.getLocationByString2(getThis(),arenaFile.getConfig().getString("TeamConfig." + s + ".IslandCenter")));
                }
                return map;
            }

            @Override
            public Map<Integer, Integer> islandSize() {
                Map<Integer,Integer> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),arenaFile.getConfig().getInt("TeamConfig." + s + ".IslandSize"));
                }
                return map;
            }

            @Override
            public Map<Integer, Location> chest() {
                Map<Integer,Location> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),Utils.getLocationByString2(getThis(),arenaFile.getConfig().getString("TeamConfig." + s + ".Chest")));
                }
                return map;
            }

            @Override
            public Map<Integer, String> color() {
                Map<Integer,String> map = new HashMap<>();
                for (String s : teamId().keySet()) {
                    map.put(teamId().get(s),arenaFile.getConfig().getString("TeamConfig." + s + ".Color"));
                }
                return map;
            }
        };
    }

    public String getName() {
        return name;
    }

    public ArenaConfig getThis() {
        return this;
    }

    public ArenaFile getArenaFile() {
        return arenaFile;
    }

    public GeneratorConfig getGeneratorConfig() {
        return generatorConfig;
    }

    public boolean isStatus() {
        return status;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public List<Location> getGlyphs() {
        return glyphs;
    }

    public TeamConfig getTeamConfig() {
        return teamConfig;
    }

    public List<Location> getIslandSprays() {
        return islandSprays;
    }

    public Location getWaitingRoom() {
        return waitingRoom;
    }

    public Location getWaitingRoomPos1() {
        return waitingRoomPos1;
    }

    public Location getWaitingRoomPos2() {
        return waitingRoomPos2;
    }

    public String getOriginWorldName() {
        return originWorldName;
    }

    public int getPPT() {
        return PPT;
    }
}
