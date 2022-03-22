package fr.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static String DB_URL = "";
    private static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static String USER = "";
    private static String PASS = "";
    private static Connection con;
    private static String URL_STRING;

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            try {
                con = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
        }
        return con;
    }
}
