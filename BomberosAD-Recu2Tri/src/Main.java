import Conexion.*;

import java.sql.SQLException;

public class Main {
    static GestionMySQL conexionMySQL;

    public static void main(String[] args) throws SQLException {
        conexionMySQL = new GestionMySQL();

        conexionMySQL.listadoPacientesCompleto();
    }
}