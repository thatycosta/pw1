/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 0489166
 */
public class ConnectionFactory {
     public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/produto", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("driver não localizado");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("não conseguiu conectar");
        }
        return null;
    }
}
