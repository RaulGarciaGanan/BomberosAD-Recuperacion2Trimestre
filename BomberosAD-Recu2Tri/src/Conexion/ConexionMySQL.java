package Conexion;

import java.sql.*;

public class ConexionMySQL {
    Connection conexion = null;

    public Connection conectorMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/bomberosadrecu","root","root");
            System.out.println("Connection to MySQL has been established.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}
