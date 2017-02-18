package fr.kwizzy.app.database;

import fr.kwizzy.app.Config;
import fr.kwizzy.app.Mc2Web;

import java.sql.*;
import java.util.UUID;

/**
 * Created by Alexis on 12/02/2017.
 * French author.
 */

public class Database
{

    private static DatabaseConfig config = Config.getInstance().getDatabase();

    private static final String url = String.format("jdbc:mysql://%s:%s/%s", config.getHost(), config.getPort(), config.getDatabase());

    private static Connection connection = connect();


    static private Connection connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            return DriverManager.getConnection(url, config.getUser(), config.getPassword());

        } catch (Exception e) {
            Mc2Web.toConsole("*********************************");
            Mc2Web.toConsole("The sql database isn't connected");
            Mc2Web.toConsole("*********************************");
        }
        return null;
    }

    public static void createTable() {
        try (Statement s = connection.createStatement()){
            String sql = "CREATE TABLE " + config.getTable() +" (id INTEGER NOT NULL UNIQUE, uuid VARCHAR(255), code VARCHAR(255) NOT NULL, ok boolean DEFAULT false)";
            s.executeUpdate(sql);
        } catch (Exception e) {
            Mc2Web.toConsole("Table already created !");
        }
    }

    static boolean isUuidUsed(UUID uuid) {
        String sql = "SELECT id FROM " + config.getTable() +" WHERE uuid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setObject(1, uuid.toString());
            ResultSet result = pstmt.executeQuery();
            return result.next();
        } catch (Exception e) {
            return false;
        }
    }

    static public boolean confirmCode(String code, UUID uuid) {
        if(isUuidUsed(uuid))
            return false;
        String sql = "SELECT id FROM " + config.getTable() +" WHERE code = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)){
            pstmt.setObject(1, code);
            ResultSet result = pstmt.executeQuery();
            if(result.next()){
                statementValidate(code, uuid);
                result.close();
                return true;
            } else {
                result.close();
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    static private void statementValidate(String code, UUID uuid) {
        String sqlValidate = "UPDATE " + config.getTable() +" SET ok=?, uuid=? WHERE code=?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlValidate)){
            pstmt.setObject(1, true);
            pstmt.setObject(2, uuid.toString());
            pstmt.setObject(3, code);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isConnected() {
        try {
            return Database.connection != null && !Database.connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public static void close() {
        if(isConnected())
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
