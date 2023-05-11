package Ventanas;

import Clases.*;
import Conexion.GestionMySQL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public JFrame frame;
    GestionMySQL gestion;
    public ArrayList<Bombero> bomberos;
    public ArrayList<Vehiculo> vehiculos;
    public ArrayList<Emergencia> emergencias;

    public ArrayList<gestionParque> gestionParques;

    public Main() throws SQLException {
        bomberos = new ArrayList<Bombero>();
        vehiculos = new ArrayList<Vehiculo>();
        emergencias = new ArrayList<Emergencia>();
        gestionParques = new ArrayList<gestionParque>();

        gestion = new GestionMySQL();

        gestion.cargarBomberos(bomberos);
        gestion.cargarVehiculos(vehiculos);
        gestion.cargarEmergencias(emergencias);
        gestion.cargarParque(gestionParques);

        frame = new JFrame("main");

        JMenuBar menuBar = new JMenuBar();
        JMenu menuBomberos = new JMenu("Bomberos");
        JMenu menuVehiculos = new JMenu("Vehiculos");
        JMenu menuEmergencias = new JMenu("Emergencias");
        JMenu menuGestionParques = new JMenu("Gestion de Parques");
        JMenu menuAyuda = new JMenu("Ayuda");
        //pestaña bomberos
        JMenuItem gestionBomberos = new JMenuItem("Gestion de Bomberos");
        JMenuItem consultaBomberos = new JMenu("Consulta de Bomberos");

        JMenuItem porCodigoB = new JMenuItem("Por Codigo");
        JMenuItem porNombre = new JMenuItem("Por Nombre");
        //pestaña vehiculos

        JMenuItem gestionVehiculos = new JMenuItem("Gestion de Vehiculos");
        JMenuItem consultaVehiculos = new JMenu("Consulta de Vehiculos");

        JMenuItem porCodigoV = new JMenuItem("Por codigo");
        JMenuItem porMatricula = new JMenuItem("Por matricula");
        //pestaña emergencias
        JMenuItem gestionEmergencias = new JMenuItem("Gestion de Emergencias");
        JMenuItem consultaEmergencias = new JMenu("Consulta de Emergencias ");

        JMenuItem porUbicacion = new JMenuItem("Por Ubicacion");
        JMenuItem porEstado = new JMenuItem("Por estado");


        //pestaña gestion parque
        JMenuItem gestionParque = new JMenuItem("Gestion de Parque");

        //pestaña ayuda
        JMenuItem acercaDe = new JMenuItem("Acerca de");
        JMenuItem manual = new JMenu("Descargar manual");

        consultaBomberos.add(porCodigoB);
        consultaBomberos.add(porNombre);

        consultaVehiculos.add(porCodigoV);
        consultaVehiculos.add(porMatricula);

        consultaEmergencias.add(porUbicacion);
        consultaEmergencias.add(porEstado);

        menuBomberos.add(gestionBomberos);
        menuBomberos.add(consultaBomberos);

        menuVehiculos.add(gestionVehiculos);
        menuVehiculos.add(consultaVehiculos);

        menuEmergencias.add(gestionEmergencias);
        menuEmergencias.add(consultaEmergencias);

        menuGestionParques.add(gestionParque);

        menuAyuda.add(acercaDe);
        menuAyuda.add(manual);

        menuBar.add(menuBomberos);
        menuBar.add(menuVehiculos);
        menuBar.add(menuEmergencias);
        menuBar.add(menuGestionParques);
        menuBar.add(menuAyuda);

        frame.setJMenuBar(menuBar);
        frame.setJMenuBar(menuBar);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gestionBomberos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CRUDBomberos crudBomberos = null;
                try {
                    crudBomberos = new CRUDBomberos();
                    crudBomberos.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        porCodigoB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BomberosConsulta bConsulta = null;
                try {
                    bConsulta = new BomberosConsulta();
                    bConsulta.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        porNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                BomberosConsulta bConsulta = null;
                try {
                    bConsulta = new BomberosConsulta();
                    bConsulta.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        gestionVehiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CRUDVehiculos crudVehiculos = null;
                try {
                    crudVehiculos = new CRUDVehiculos();
                    crudVehiculos.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        porMatricula.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                VehiculosConsulta vConsulta = null;
                try {
                    vConsulta = new VehiculosConsulta();
                    vConsulta.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        porCodigoV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                VehiculosConsulta vConsulta = null;
                try {
                    vConsulta = new VehiculosConsulta();
                    vConsulta.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        gestionEmergencias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CRUDEmergencias crudEmergencias = null;
                try {
                    crudEmergencias = new CRUDEmergencias();
                    crudEmergencias.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        porUbicacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                EmergenciasConsulta emergeciasConsulta = null;
                try {
                    emergeciasConsulta = new EmergenciasConsulta();
                    emergeciasConsulta.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        porEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                EmergenciasConsulta emergeciasConsulta = null;
                try {
                    emergeciasConsulta = new EmergenciasConsulta();
                    emergeciasConsulta.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        gestionParque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                GestionParque gestionParque = null;
                try {
                    gestionParque = new GestionParque();
                    gestionParque.frame.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException {
        Main programa = new Main();
    }
}
