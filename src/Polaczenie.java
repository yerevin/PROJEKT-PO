
import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mateusz Skulski
 */
public class Polaczenie {

    static final String DB_URL = "jdbc:mysql://localhost:3306/products?serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "mypass";
    Connection connection;

    public Polaczenie() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        try {
            System.err.println("CONNECTING TO DATABASE...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    Connection getConnection() {
        return connection;
    }

}
