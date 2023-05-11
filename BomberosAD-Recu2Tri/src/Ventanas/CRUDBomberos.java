package Ventanas;

import Clases.Bombero;
import Conexion.GestionMySQL;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.event.*;
import java.sql.SQLException;

public class CRUDBomberos {
    public GestionMySQL gestion;
    public Main m;
    public JFrame frame;
    public JPanel panel1;
    private JTextField tfCodigo;
    private JTextField tfNombre;
    private JTextField tfApellidos;
    private JTextField tfEdad;
    private JButton bAlta;
    private JButton bBaja;
    private JButton bModificaciones;
    private JButton bLimpiar;
    private JTextField tfEstado;
    private JTextField tfRango;
    private JButton bSalir;
    private JPanel panelLista;
    private JPanel botones;
    private JPanel textField;
    private JScrollPane jpListadoBomberos;
    private JList lBomberos;

    public CRUDBomberos() throws SQLException {
        gestion = new GestionMySQL();
        m = new Main();
        m.frame.setVisible(false);

        frame = new JFrame("crudBomberos");
        frame.setContentPane(panel1);

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        tfCodigo.setDocument(new JTextFieldLimit(6));
        DefaultListModel<Bombero> modelobombero = new DefaultListModel<>();
        for (Bombero bombero : m.bomberos) {
            modelobombero.addElement(bombero);
        }

        // Creamos un JList a partir del modelo de lista
        lBomberos.setModel(modelobombero);

        // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
        jpListadoBomberos.setViewportView(lBomberos);

        // Añadimos un ListSelectionListener para detectar cuando el usuario hace clic en un elemento de la lista
        lBomberos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Evita que se ejecuten acciones repetidas por la selección múltiple
                    if (lBomberos.getSelectedIndex() != -1) {
                        tfCodigo.setEditable(false);
                        int indiceSeleccionado = lBomberos.getSelectedIndex();
                        // Aquí puedes poner el código que quieres que se ejecute cuando el usuario hace clic en un elemento de la lista
                        tfCodigo.setText(m.bomberos.get(indiceSeleccionado).getCodigo());
                        tfNombre.setText(m.bomberos.get(indiceSeleccionado).getNombre());
                        tfApellidos.setText(m.bomberos.get(indiceSeleccionado).getApellidos());
                        tfEdad.setText(String.valueOf(m.bomberos.get(indiceSeleccionado).getEdad()));
                        tfEstado.setText(m.bomberos.get(indiceSeleccionado).getEstado_actual());
                        tfRango.setText(m.bomberos.get(indiceSeleccionado).getRango());
                    }
                }
            }
        });


        bAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bombero b = new Bombero(tfCodigo.getText(), tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()), tfEstado.getText(), tfRango.getText(), false);
                    modelobombero.addElement(b);
                    gestion.insertarBombero(b);
                    m.bomberos.clear();
                    gestion.cargarBomberos(m.bomberos);
                    limpiarTextfiel();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "La edad debe ser numerica");
                }

            }
        });
        bBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bombero b = new Bombero(tfCodigo.getText());
                    gestion.bajaBombero(b);
                    m.bomberos.clear();
                    gestion.cargarBomberos(m.bomberos);
                    modelobombero.removeElementAt(lBomberos.getSelectedIndex());
                    limpiarTextfiel();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception ex){
                    JOptionPane.showMessageDialog(null,"Debe seleccionar antes un bombero para darlo de baja");
                }
            }
        });
        bModificaciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Bombero b = new Bombero(tfCodigo.getText(), tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()), tfEstado.getText(), tfRango.getText());
                    gestion.modificarBombero(b);
                    m.bomberos.clear();
                    gestion.cargarBomberos(m.bomberos);
                    limpiarTextfiel();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, "La edad debe ser numerica");
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
        tfNombre.setText("");
        tfApellidos.setText("");
        tfEdad.setText("");
        tfEstado.setText("");
        tfRango.setText("");
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
