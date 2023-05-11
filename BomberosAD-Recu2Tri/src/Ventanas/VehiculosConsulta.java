package Ventanas;

import Clases.*;
import Conexion.*;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehiculosConsulta {
    public GestionMySQL gestion;
    public Main m;
    public ArrayList<Vehiculo> vehiculoConsulta;
    public JFrame frame;
    private JPanel listado;
    private JPanel consulta;
    private JTextField tfCodigo;
    private JTextField tfMatricula;
    private JPanel botones;
    private JButton bConsultar;
    private JButton bBorarConsulta;
    private JButton bSalir;
    private JList lVehiculos;
    private JScrollPane jpVehiculos;
    private JPanel panel1;

    public VehiculosConsulta() throws SQLException {
        vehiculoConsulta = new ArrayList<Vehiculo>();
        gestion = new GestionMySQL();
        m = new Main();
        m.frame.setVisible(false);

        frame = new JFrame("bomberosConsulta");
        frame.setContentPane(panel1);

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tfCodigo.setDocument(new JTextFieldLimit(6));

        bConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((tfCodigo.getText().isEmpty() && !tfMatricula.isEditable()) || (tfMatricula.getText().isEmpty() && !tfCodigo.isEditable()) || (tfMatricula.getText().isEmpty() && tfCodigo.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Ninguno de los 2 campos debe estar vacio para hacer la consulta");
                } else if (!tfCodigo.getText().isEmpty()) {
                    try {
                        gestion.vehiculosConsultaPorCodigo(tfCodigo.getText(), vehiculoConsulta);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    DefaultListModel<Vehiculo> modelobombero = new DefaultListModel<>();
                    for (Vehiculo vehiculo : vehiculoConsulta) {
                        modelobombero.addElement(vehiculo);
                    }

                    // Creamos un JList a partir del modelo de lista
                    lVehiculos.setModel(modelobombero);

                    // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
                    jpVehiculos.setViewportView(lVehiculos);
                } else if (!tfMatricula.getText().isEmpty()) {
                    try {
                        gestion.vehiculosConsultaPorMatricula(tfMatricula.getText(), vehiculoConsulta);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    DefaultListModel<Vehiculo> modelobombero = new DefaultListModel<>();
                    for (Vehiculo vehiculo : vehiculoConsulta) {
                        modelobombero.addElement(vehiculo);
                    }

                    // Creamos un JList a partir del modelo de lista
                    lVehiculos.setModel(modelobombero);

                    // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
                    jpVehiculos.setViewportView(lVehiculos);
                }
            }
        });
        bBorarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehiculoConsulta.clear();
                tfCodigo.setText("");
                tfMatricula.setText("");
                tfCodigo.setEditable(true);
                tfMatricula.setEditable(true);
            }
        });
        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                m.frame.setVisible(true);
            }
        });
        tfCodigo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfMatricula.setText("");
                tfMatricula.setEditable(false);
                tfCodigo.setEditable(true);
            }
        });
        tfMatricula.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfCodigo.setText("");
                tfCodigo.setEditable(false);
                tfMatricula.setEditable(true);
            }
        });
    }

    class JTextFieldLimit extends PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        JTextFieldLimit(int limit, boolean upper) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
