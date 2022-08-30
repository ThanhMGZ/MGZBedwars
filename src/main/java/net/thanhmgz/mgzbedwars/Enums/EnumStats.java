package net.thanhmgz.mgzbedwars.Enums;

import java.util.ArrayList;
import java.util.List;

public enum EnumStats
{

    KILLS("kills", "Kills", 2),
    COSMETICS("cosmetics", "Cosmetics", 3),
    CUSTOMGUI("customgui","CustomGUI",4),
    FINALKILLS("finalkills", "FinalKills", 5),
    DEATHS("deaths", "Deaths", 6),
    WINS("wins", "Wins", 7),
    LOSSES("losses", "Losses", 8),
    FINALDEATHS("finaldeaths", "FinalDeaths", 9),
    BEDLOST("bedlost", "BedLost", 10),
    BEDBROKEN("bedbroken", "BedBroken", 11),
    GAMEPLAYED("gameplayed", "GamePlayed", 12),
    EXP("exp", "Exp", 11),
    BLOCKPLACED("blockplaced", "BlockPlaced", 13);

    private String name;
    private String name1;
    private int id;

    EnumStats(String name, String name1, int id) {
        this.name = name;
        this.name1 = name1;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getName1() {
        return name1;
    }

    public int getId() {
        return id;
    }

    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        for (EnumStats enumStats : EnumStats.values()) {
            list.add(enumStats.getName1().toUpperCase());
        }
        return list;
    }

}
