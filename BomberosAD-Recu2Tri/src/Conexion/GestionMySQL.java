package Conexion;

import java.sql.*;

public class GestionMySQL {


    public void listadoPacientesCompleto() throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM bomberos ");
        while (miResultSet.next()) {
            System.out.println("id: " + miResultSet.getString(1) + ", dni: " + miResultSet.getString(2) + ", nombre: "
                    + miResultSet.getString(3) + ", apellidos : " + miResultSet.getInt(4) + ", direccion: "
                    + miResultSet.getString(5) + ", profesion: " + miResultSet.getString(6) + ", edad: "
                    + miResultSet.getString(7));
        }
        miConexion.close();
    }
}
