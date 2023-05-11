package Ventanas;

import Clases.Bombero;
import Clases.Vehiculo;
import Conexion.GestionMySQL;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CRUDVehiculos {
    public GestionMySQL gestion;
    public Main m;
    public JFrame frame;
    private JTextField tfCodigo;
    private JTextField tfMatricula;
    private JTextField tfTipo;
    private JTextField tfModelo;
    private JTextField tfDisponibilidad;
    private JPanel botones;
    private JButton bAlta;
    private JButton bBaja;
    private JButton bModificaciones;
    private JButton bLimpiar;
    private JButton bSalir;
    private JPanel textfield;
    private JPanel jlist;
    private JList lVehiculos;
    private JPanel panel1;
    private JScrollPane jsVehiculo;

    public CRUDVehiculos() throws SQLException {
        gestion = new GestionMySQL();
        m = new Main();
        m.frame.setVisible(false);

        frame = new JFrame("crudvehiculos");
        frame.setContentPane(panel1);

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tfCodigo.setDocument(new JTextFieldLimit(6));

        DefaultListModel<Vehiculo> modelVehiculo = new DefaultListModel<>();
        for (Vehiculo vehiculo : m.vehiculos) {
            modelVehiculo.addElement(vehiculo);
        }

        // Creamos un JList a partir del modelo de lista
        lVehiculos.setModel(modelVehiculo);

        // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
        jsVehiculo.setViewportView(lVehiculos);

        lVehiculos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evita que se ejecuten acciones repetidas por la selección múltiple
                    if (lVehiculos.getSelectedIndex() != -1) {
                        tfCodigo.setEditable(false);
                        int indiceSeleccionado = lVehiculos.getSelectedIndex();
                        // Aquí puedes poner el código que quieres que se ejecute cuando el usuario hace clic en un elemento de la lista
                        tfCodigo.setText(m.vehiculos.get(indiceSeleccionado).getCodigo());
                        tfMatricula.setText(m.vehiculos.get(indiceSeleccionado).getMatricula());
                        tfTipo.setText(m.vehiculos.get(indiceSeleccionado).getTipo());
                        tfModelo.setText(String.valueOf(m.vehiculos.get(indiceSeleccionado).getModelo()));
                        tfDisponibilidad.setText(m.vehiculos.get(indiceSeleccionado).getDisponibilidad());
                    }
                }
            }
        });

        bAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (tfCodigo.getText().isEmpty() || tfMatricula.getText().isEmpty() || tfTipo.getText().isEmpty() || tfModelo.getText().isEmpty() || tfDisponibilidad.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio");
                    } else {
                        Vehiculo v = new Vehiculo(tfCodigo.getText(), tfMatricula.getText(), tfTipo.getText(), tfModelo.getText(), tfDisponibilidad.getText(), false);
                        modelVehiculo.addElement(v);
                        gestion.insertarVehiculo(v);
                        m.vehiculos.clear();
                        gestion.cargarVehiculos(m.vehiculos);
                        limpiarTextfiel();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Vehiculo v = new Vehiculo(tfCodigo.getText());
                    gestion.bajaVehiculo(v);
                    m.vehiculos.clear();
                    gestion.cargarVehiculos(m.vehiculos);
                    modelVehiculo.removeElementAt(lVehiculos.getSelectedIndex());
                    limpiarTextfiel();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar antes un bombero para darlo de baja");
                }
            }
        });
        bModificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (tfCodigo.getText().isEmpty() || tfMatricula.getText().isEmpty() || tfTipo.getText().isEmpty() || tfModelo.getText().isEmpty() || tfDisponibilidad.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Ningun campo puede estar vacio");
                    } else {
                        Vehiculo v = new Vehiculo(tfCodigo.getText(), tfMatricula.getText(), tfTipo.getText(), tfModelo.getText(), tfDisponibilidad.getText(), false);
                        gestion.modificarVehiculo(v);
                        m.vehiculos.clear();
                        gestion.cargarVehiculos(m.vehiculos);
                        limpiarTextfiel();
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarTextfiel();
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

    public void limpiarTextfiel() {
        tfCodigo.setEditable(true);
        tfCodigo.setText("");
        tfMatricula.setText("");
        tfTipo.setText("");
        tfModelo.setText("");
        tfDisponibilidad.setText("");

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
