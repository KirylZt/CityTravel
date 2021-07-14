package by.teachmeskills.travelplaner.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnections {
    private static final String URL = "jdbc:mysql://localhost/travel_company?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Fotfotgod991";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
