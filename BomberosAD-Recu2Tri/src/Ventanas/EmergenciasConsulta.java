package Ventanas;

import Clases.Emergencia;
import Clases.Vehiculo;
import Conexion.GestionMySQL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmergenciasConsulta {
    public GestionMySQL gestion;
    public Main m;
    public ArrayList<Emergencia> emergenciaConsulta;
    public JFrame frame;
    private JPanel listado;
    private JPanel consulta;
    private JTextField tfUbicacion;
    private JTextField tfEstado;
    private JPanel botones;
    private JButton bConsultar;
    private JButton bBorarConsulta;
    private JButton bSalir;
    private JList lEmergencias;
    private JScrollPane jpEmergencias;
    private JPanel panel1;

    public EmergenciasConsulta() throws SQLException {
        emergenciaConsulta = new ArrayList<Emergencia>();
        gestion = new GestionMySQL();
        m = new Main();
        m.frame.setVisible(false);

        frame = new JFrame("bomberosConsulta");
        frame.setContentPane(panel1);

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        bConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((tfUbicacion.getText().isEmpty() && !tfEstado.isEditable()) || (tfEstado.getText().isEmpty() && !tfUbicacion.isEditable()) || (tfEstado.getText().isEmpty() && tfUbicacion.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Ninguno de los 2 campos debe estar vacio para hacer la consulta");
                } else if (!tfUbicacion.getText().isEmpty()) {
                    try {
                        gestion.emergenciaPorUbicacion(tfUbicacion.getText(), emergenciaConsulta);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    DefaultListModel<Emergencia> modeloemergencia = new DefaultListModel<>();
                    for (Emergencia emergencia : emergenciaConsulta) {
                        modeloemergencia.addElement(emergencia);
                    }

                    // Creamos un JList a partir del modelo de lista
                    lEmergencias.setModel(modeloemergencia);

                    // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
                    jpEmergencias.setViewportView(lEmergencias);
                } else if (!tfEstado.getText().isEmpty()) {
                    try {
                        gestion.emergenciaPorEstado(tfEstado.getText(), emergenciaConsulta);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    DefaultListModel<Emergencia> modeloemergencia = new DefaultListModel<>();
                    for (Emergencia emergencia : emergenciaConsulta) {
                        modeloemergencia.addElement(emergencia);
                    }

                    // Creamos un JList a partir del modelo de lista
                    lEmergencias.setModel(modeloemergencia);

                    // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
                    jpEmergencias.setViewportView(lEmergencias);
                }
            }
        });
        bBorarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emergenciaConsulta.clear();
                tfEstado.setText("");
                tfUbicacion.setText("");
                tfEstado.setEditable(true);
                tfUbicacion.setEditable(true);
            }
        });
        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                m.frame.setVisible(true);
            }
        });
        tfUbicacion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfEstado.setText("");
                tfEstado.setEditable(false);
                tfUbicacion.setEditable(true);
            }
        });
        tfEstado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfUbicacion.setText("");
                tfUbicacion.setEditable(false);
                tfEstado.setEditable(true);
            }
        });
    }
}
