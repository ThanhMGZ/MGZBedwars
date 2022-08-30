package net.thanhmgz.mgzbedwars.database;

import net.thanhmgz.mgzbedwars.Enums.EnumStats;
import net.thanhmgz.mgzbedwars.MGZBedwars;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
import java.util.logging.Level;

public class Database {

    private MGZBedwars plugin;
    Connection connection;

    public Database(MGZBedwars instance) {
        plugin = instance;
    }

    public String SQLiteCreateTokensTable = "CREATE TABLE IF NOT EXISTS global_stats (" +
            "`player` varchar(200) NOT NULL," +
            "`cosmetics` varchar(200) NOT NULL," +
            "`customgui` varchar(200) NOT NULL,"+
            "`kills` int(200) NOT NULL," +
            "`finalkills` int(200) NOT NULL," +
            "`deaths` int(200) NOT NULL," +
            "`wins` int(200) NOT NULL," +
            "`losses` int(200) NOT NULL," +
            "`finaldeaths` int(200) NOT NULL," +
            "`bedlost` int(200) NOT NULL," +
            "`bedbroken` int(200) NOT NULL," +
            "`gameplayed` int(200) NOT NULL," +
            "`exp` int(200) NOT NULL," +
            "`blockplaced` int(200) NOT NULL," +
            "PRIMARY KEY (`player`)" +
            ");";


    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder(), "database" + ".db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: " + "database" + ".db");
            }
        }
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, ex.getMessage());
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLiteCreateTokensTable);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }

    public void initialize() {
        connection = getSQLConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + "global_stats" + " WHERE player = ?");
            ResultSet rs = ps.executeQuery();
            close(ps, rs);

        } catch (SQLException ignored) {
        }
    }

    public Object getStat(Player player, EnumStats enumStats) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String pn = player.getName().toLowerCase();
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + "global_stats" + " WHERE player = '" + pn + "';");

            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(pn)) {
                    return rs.getObject(enumStats.getName());
                }
            }
        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public void setStats(Player player, EnumStats enumStats, int plus) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO global_stats (player,cosmetics,customgui,kills,finalkills,deaths,wins,losses,finaldeaths,bedlost,bedbroken,gameplayed,exp,blockplaced) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, player.getName().toLowerCase());
            for (String s : EnumStats.getList()) {
                EnumStats enumStats1 = EnumStats.valueOf(s);
                if (enumStats1.equals(enumStats)) {
                    ps.setInt(enumStats1.getId(), Integer.parseInt(String.valueOf(getStat(player, enumStats))) + plus);
                } else {
                    ps.setInt(enumStats1.getId(), Integer.parseInt(String.valueOf(getStat(player, enumStats))));
                }
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void setStats(Player player) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            if (getStat(player, EnumStats.KILLS) != null)
                return;
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO global_stats (player,cosmetics,customgui,kills,finalkills,deaths,wins,losses,finaldeaths,bedlost,bedbroken,gameplayed,exp,blockplaced) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, player.getName().toLowerCase());
            for (String s : EnumStats.getList()) {
                EnumStats enumStats1 = EnumStats.valueOf(s);
                ps.setInt(enumStats1.getId(), 0);
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void saveStats(Player player) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            if (getStat(player, EnumStats.KILLS) == null)
                setStats(player);
            conn = getSQLConnection();
            ps = conn.prepareStatement("REPLACE INTO global_stats (player,cosmetics,customgui,kills,finalkills,deaths,wins,losses,finaldeaths,bedlost,bedbroken,gameplayed,exp,blockplaced) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, player.getName().toLowerCase());
            ps.setString(2,String.valueOf(getStat(player,EnumStats.COSMETICS)));
            ps.setString(3,String.valueOf(getStat(player,EnumStats.CUSTOMGUI)));
            for (String s : EnumStats.getList()) {
                EnumStats enumStats1 = EnumStats.valueOf(s);
                ps.setInt(enumStats1.getId(), Integer.parseInt(String.valueOf(getStat(player,enumStats1))));
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {
            }
        }
    }

    public void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
        }
    }

}
