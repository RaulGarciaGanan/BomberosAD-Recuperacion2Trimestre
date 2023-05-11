package Ventanas;

import Clases.Emergencia;
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

public class CRUDEmergencias {

    public GestionMySQL gestion;
    public Main m;
    public JFrame frame;
    private JPanel list;
    private JPanel textField;
    private JTextField tfCodigo;
    private JTextField tfUbicacion;
    private JTextField tfCiudad;
    private JTextField tfTipo;
    private JTextField tfEstado;
    private JTextField tfNivel;
    private JPanel botones;
    private JButton bAlta;
    private JButton bBaja;
    private JButton bModificaciones;
    private JButton bLimpiar;
    private JButton bSalir;
    private JList lEmergencias;
    private JScrollPane jsEmergencias;
    private JPanel panel1;

    public CRUDEmergencias() throws SQLException {
        gestion = new GestionMySQL();
        m = new Main();
        m.frame.setVisible(false);

        frame = new JFrame("crudEmergencias");
        frame.setContentPane(panel1);

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tfCodigo.setDocument(new JTextFieldLimit(6));
        DefaultListModel<Emergencia> modeloemergencia = new DefaultListModel<>();
        for (Emergencia emergencia : m.emergencias) {
            modeloemergencia.addElement(emergencia);
        }

        // Creamos un JList a partir del modelo de lista
        lEmergencias.setModel(modeloemergencia);

        // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
        jsEmergencias.setViewportView(lEmergencias);

        lEmergencias.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evita que se ejecuten acciones repetidas por la selección múltiple
                    if (lEmergencias.getSelectedIndex() != -1) {
                        tfCodigo.setEditable(false);
                        int indiceSeleccionado = lEmergencias.getSelectedIndex();
                        // Aquí puedes poner el código que quieres que se ejecute cuando el usuario hace clic en un elemento de la lista
                        tfCodigo.setText(m.emergencias.get(indiceSeleccionado).getCodigo());
                        tfUbicacion.setText(m.emergencias.get(indiceSeleccionado).getUbicacion());
                        tfCiudad.setText(m.emergencias.get(indiceSeleccionado).getCiudad());
                        tfTipo.setText(m.emergencias.get(indiceSeleccionado).getTipo());
                        tfEstado.setText(m.emergencias.get(indiceSeleccionado).getEstado_actual());
                        tfNivel.setText(m.emergencias.get(indiceSeleccionado).getGravedad());
                    }

                }
            }
        });

        bAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Emergencia em = new Emergencia(tfCodigo.getText(), tfUbicacion.getText(), tfCiudad.getText(), tfTipo.getText(), tfEstado.getText(), tfNivel.getText(), false);
                    modeloemergencia.addElement(em);
                    gestion.insertarEmergencia(em);
                    m.emergencias.clear();
                    gestion.cargarEmergencias(m.emergencias);
                    limpiarTextfiel();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Emergencia em = new Emergencia(tfCodigo.getText());
                    modeloemergencia.addElement(em);
                    gestion.bajaEmergencia(em);
                    m.emergencias.clear();
                    gestion.cargarEmergencias(m.emergencias);
                    limpiarTextfiel();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        bModificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Emergencia em = new Emergencia(tfCodigo.getText(), tfUbicacion.getText(), tfCiudad.getText(), tfTipo.getText(), tfEstado.getText(), tfNivel.getText());
                    gestion.modificarEmergencia(em);
                    m.emergencias.clear();
                    gestion.cargarEmergencias(m.emergencias);
                    modeloemergencia.removeElementAt(lEmergencias.getSelectedIndex());
                    limpiarTextfiel();
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
        tfUbicacion.setText("");
        tfCiudad.setText("");
        tfTipo.setText("");
        tfEstado.setText("");
        tfNivel.setText("");
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
