package Conexion;

import Clases.Bombero;
import Clases.Emergencia;
import Clases.Vehiculo;
import Clases.gestionParque;
import Ventanas.GestionParque;
import Ventanas.Main;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionMySQL {
    public GestionParque gp;

    public Main m;

    public void insertarBombero(Bombero b) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("INSERT INTO bomberos (`codigo`,`nombre`,`apellido`,`edad`,`estado_actual`,`rango`,`baja`)" +
                "VALUES('" + b.getCodigo() + "','" + b.getNombre() + "','" + b.getApellidos() + "'," + b.getEdad() + ",'"
                + b.getEstado_actual() + "','" + b.getRango() + "', 0 )");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void modificarBombero(Bombero b) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("UPDATE bomberos SET `nombre` = '" + b.getNombre() + "', `apellido` = '" + b.getApellidos() + "',`edad` = " + b.getEdad() + "" +
                ",`estado_actual` = '" + b.getEstado_actual() + "',`rango` = '" + b.getRango() + "'  WHERE `codigo` = '" + b.getCodigo() + "';");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void bajaBombero(Bombero b) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("UPDATE bomberos SET baja=1 WHERE `codigo` = '" + b.getCodigo() + "';");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void insertarVehiculo(Vehiculo v) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("INSERT INTO vehiculos (`codigo`,`matricula`,`tipo`,`modelo`,`disponibilidad`,`baja`)" +
                "VALUES('" + v.getCodigo() + "','" + v.getMatricula() + "','" + v.getTipo() + "','" + v.getModelo() + "','"
                + v.getDisponibilidad() + "', 0 )");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void modificarVehiculo(Vehiculo v) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("UPDATE vehiculos SET `matricula` = '" + v.getMatricula() + "', `tipo` = '" + v.getTipo() + "',`modelo` = '" + v.getModelo() +
                "',`disponibilidad` = '" + v.getDisponibilidad() + "'  WHERE `codigo` = '" + v.getCodigo() + "';");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void bajaVehiculo(Vehiculo v) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("UPDATE vehiculos SET baja=1 WHERE `codigo` = '" + v.getCodigo() + "';");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void insertarEmergencia(Emergencia e) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("INSERT INTO emergencias (`codigo`,`ubicacion`,`ciudad`,`tipo`,`estao_actual`,`nivel_gravedad`,`baja`)" +
                "VALUES('" + e.getCodigo() + "','" + e.getUbicacion() + "','" + e.getCiudad() + "','" + e.getTipo() + "','"
                + e.getEstado_actual() + "','" + e.getGravedad() + "', 0 )");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void modificarEmergencia(Emergencia e) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("UPDATE emergencias SET `ubicacion` = '" + e.getUbicacion() + "', `ciudad` = '" + e.getCiudad() + "',`tipo` = '" + e.getTipo() +
                "',`estado_actual` = '" + e.getEstado_actual() + "',`nivel_gravedad` = '" + e.getGravedad() + "'  WHERE `codigo` = '" + e.getCodigo() + "';");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void bajaEmergencia(Emergencia e) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("UPDATE emergencias SET baja=1 WHERE `codigo` = '" + e.getCodigo() + "';");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void insertarGestionParque(gestionParque gp) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        String sql = String.format("INSERT INTO gestionparque (`codbombero`,`codvehiculo`,`codemergencia`,`fecha`,`hora`)" +
                "VALUES('" + gp.getCodBombero() + "','" + gp.getCodVehiculo() + "','" + gp.getCodEmergencia() + "','" + gp.getFecha() + "','"
                + gp.getHora() + "' )");

        miSentencia.execute(sql);

        miConexion.close();
    }

    public void vehiculosConsultaPorCodigo(String codigo, ArrayList<Vehiculo> vehiculos) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM vehiculos where codigo='" + codigo + "'");
        if (miResultSet.next()) {
            Vehiculo v = new Vehiculo(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getString(4), miResultSet.getString(5), miResultSet.getBoolean(6));
            vehiculos.add(v);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado resultados");
        }
        miConexion.close();
    }

    public void vehiculosConsultaPorMatricula(String matricula, ArrayList<Vehiculo> vehiculos) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM vehiculos where matricula='" + matricula + "'");
        if (miResultSet.next()) {
            Vehiculo v = new Vehiculo(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getString(4), miResultSet.getString(5), miResultSet.getBoolean(6));
            vehiculos.add(v);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado resultados");
        }
        miConexion.close();
    }

    public void bomberosConsultaPorCodigo(String codigo, ArrayList<Bombero> bomberos) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM bomberos where codigo='" + codigo + "'");
        if (miResultSet.next()) {
            Bombero b = new Bombero(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getInt(4), miResultSet.getString(5), miResultSet.getString(6), miResultSet.getBoolean(7));
            bomberos.add(b);

        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado resultados");
        }

        miConexion.close();
    }

    public void bomberosConsultaPorNombre(String nombre, ArrayList<Bombero> bomberos) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM bomberos where nombre='" + nombre + "'");
        if (miResultSet.next()) {
            Bombero b = new Bombero(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getInt(4), miResultSet.getString(5), miResultSet.getString(6), miResultSet.getBoolean(7));
            bomberos.add(b);

        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado resultados");
        }
        miConexion.close();
    }

    public void emergenciaPorUbicacion(String ubicacion, ArrayList<Emergencia> emergencias) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM emergencias where ubicacion='" + ubicacion + "'");
        if (miResultSet.next()) {
            Emergencia em = new Emergencia(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getString(4), miResultSet.getString(5), miResultSet.getString(6), miResultSet.getBoolean(7));
            emergencias.add(em);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado resultados");
        }
        miConexion.close();
    }

    public void emergenciaPorEstado(String estado, ArrayList<Emergencia> emergencias) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM emergencias where estado_actual='" + estado + "'");
        if (miResultSet.next()) {
            Emergencia em = new Emergencia(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getString(4), miResultSet.getString(5), miResultSet.getString(6), miResultSet.getBoolean(7));
            emergencias.add(em);
        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado resultados");
        }
        miConexion.close();
    }

    public void cargarBomberos(ArrayList<Bombero> bomberos) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM bomberos where baja=false");
        while (miResultSet.next()) {
            Bombero b = new Bombero(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getInt(4), miResultSet.getString(5), miResultSet.getString(6), miResultSet.getBoolean(7));
            bomberos.add(b);
        }
        miConexion.close();
    }

    public void cargarVehiculos(ArrayList<Vehiculo> vehiculos) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM vehiculos where baja=false");
        while (miResultSet.next()) {
            Vehiculo v = new Vehiculo(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getString(4), miResultSet.getString(5), miResultSet.getBoolean(6));
            vehiculos.add(v);
        }
        miConexion.close();
    }

    public void cargarEmergencias(ArrayList<Emergencia> emergencias) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM emergencias where baja=false");
        while (miResultSet.next()) {
            Emergencia e = new Emergencia(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getString(4), miResultSet.getString(5), miResultSet.getString(6), miResultSet.getBoolean(7));
            emergencias.add(e);
        }
        miConexion.close();
    }

    public void cargarParque(ArrayList<gestionParque> gestionParques) throws SQLException {
        Connection miConexion = new ConexionMySQL().conectorMySQL();
        Statement miSentencia = miConexion.createStatement();

        ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM gestionparque");
        while (miResultSet.next()) {
            gestionParque gp = new gestionParque(miResultSet.getString(1), miResultSet.getString(2), miResultSet.getString(3), miResultSet.getDate(4), miResultSet.getTime(5));
            gestionParques.add(gp);
        }
        miConexion.close();
    }

}
