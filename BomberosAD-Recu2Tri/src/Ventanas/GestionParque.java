package Ventanas;

import Clases.*;
import Conexion.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GestionParque {
    public GestionMySQL gestion;
    public Main m;
    public JFrame frame;
    public JComboBox cbBomberos;
    private JPanel panel1;
    private JComboBox cbVehiculos;
    private JComboBox cbEmergencias;
    private JButton bRelacionar;
    private JButton bSalir;
    private JList lGestion;
    private JScrollPane jpGestion;
    private JTextField tfFecha;
    private JTextField tfHora;

    public GestionParque() throws SQLException {
        gestion = new GestionMySQL();
        m = new Main();
        m.frame.setVisible(false);

        frame = new JFrame("gestionParque");
        frame.setContentPane(panel1);

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cargarComboxBomberos();
        cargarComboxVehiculos();
        cargarComboxEmergencias();

        DefaultListModel<gestionParque> modeloparque = new DefaultListModel<>();
        for (gestionParque gestionParque : m.gestionParques) {
            modeloparque.addElement(gestionParque);
        }

        // Creamos un JList a partir del modelo de lista
        lGestion.setModel(modeloparque);

        // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
        jpGestion.setViewportView(lGestion);

        bRelacionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    gestionParque gp = new gestionParque(cbBomberos.getSelectedItem().toString(), cbVehiculos.getSelectedItem().toString(), cbEmergencias.getSelectedItem().toString(), parseDateTextField(tfFecha.getText()), parseTimeTextField(tfHora.getText()));
                    gestion.insertarGestionParque(gp);
                    modeloparque.addElement(gp);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                m.frame.setVisible(true);
            }
        });
    }

    public void cargarComboxBomberos() throws SQLException {

        DefaultComboBoxModel<String> modelBombero = new DefaultComboBoxModel<>();
        for (Bombero bombero : m.bomberos) {
            modelBombero.addElement(bombero.getCodigo());
        }

        cbBomberos.setModel(modelBombero);
    }

    public void cargarComboxVehiculos() throws SQLException {

        DefaultComboBoxModel<String> modelVehiculo = new DefaultComboBoxModel<>();
        for (Vehiculo vehiculo : m.vehiculos) {
            modelVehiculo.addElement(vehiculo.getCodigo());
        }

        cbVehiculos.setModel(modelVehiculo);
    }

    public void cargarComboxEmergencias() throws SQLException {

        DefaultComboBoxModel<String> modelEmergencia = new DefaultComboBoxModel<>();
        for (Emergencia emergencia : m.emergencias) {
            modelEmergencia.addElement(emergencia.getCodigo());
        }

        cbEmergencias.setModel(modelEmergencia);
    }

    public static Date parseDateTextField(String text) {
        try {
            LocalDate localDate = LocalDate.parse(text);
            return Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "La fecha debe ser con el formato YYYY-MM-dd");
            return null;
        }
    }

    public static Time parseTimeTextField(String text) {
        try {
            LocalTime localTime = LocalTime.parse(text);
            return Time.valueOf(localTime);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "La hora debe ser con el formato HH:mm");
            return null;
        }

    }
}
