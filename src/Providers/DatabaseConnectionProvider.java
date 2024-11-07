/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Providers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Irzi Rhmtllh
 */
public class DatabaseConnectionProvider {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management","root","");
            return connect;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error:" + e);
        }
        return null;
    };
}
