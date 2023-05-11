package Ventanas;

import Clases.Bombero;
import Conexion.GestionMySQL;

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

public class BomberosConsulta {
    public GestionMySQL gestion;
    public Main m;
    public ArrayList<Bombero> bomberosConsulta;
    public JFrame frame;
    private JPanel panel1;
    private JTextField tfCodigo;
    private JTextField tfNombre;
    private JButton bConsultar;
    private JButton bBorarConsulta;
    private JButton bSalir;
    private JPanel botones;
    private JPanel consulta;
    private JPanel listado;
    private JList lBomberos;
    private JScrollPane jspBomberos;

    public BomberosConsulta() throws SQLException {
        bomberosConsulta = new ArrayList<Bombero>();
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
                if ((tfCodigo.getText().isEmpty() && !tfNombre.isEditable()) || (tfNombre.getText().isEmpty() && !tfCodigo.isEditable()) || (tfNombre.getText().isEmpty() && tfCodigo.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Ninguno de los 2 campos debe estar vacio para hacer la consulta");
                } else if (!tfCodigo.getText().isEmpty()) {
                    try {
                        gestion.bomberosConsultaPorCodigo(tfCodigo.getText(), bomberosConsulta);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    DefaultListModel<Bombero> modelobombero = new DefaultListModel<>();
                    for (Bombero bombero : bomberosConsulta) {
                        modelobombero.addElement(bombero);
                    }

                    // Creamos un JList a partir del modelo de lista
                    lBomberos.setModel(modelobombero);

                    // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
                    jspBomberos.setViewportView(lBomberos);
                } else if (!tfNombre.getText().isEmpty()) {
                    try {
                        gestion.bomberosConsultaPorNombre(tfNombre.getText(), bomberosConsulta);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    DefaultListModel<Bombero> modelobombero = new DefaultListModel<>();
                    for (Bombero bombero : bomberosConsulta) {
                        modelobombero.addElement(bombero);
                    }

                    // Creamos un JList a partir del modelo de lista
                    lBomberos.setModel(modelobombero);

                    // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
                    jspBomberos.setViewportView(lBomberos);
                }
            }
        });
        bBorarConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bomberosConsulta.clear();
                tfCodigo.setText("");
                tfNombre.setText("");
                tfCodigo.setEditable(true);
                tfNombre.setEditable(true);
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
                tfNombre.setText("");
                tfNombre.setEditable(false);
                tfCodigo.setEditable(true);
            }
        });
        tfNombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tfCodigo.setText("");
                tfCodigo.setEditable(false);
                tfNombre.setEditable(true);
            }
        });
    }

    /*public void cargarJlist() {
        DefaultListModel<String> modelobombero = new DefaultListModel<>();
        for (Bombero bombero : m.bomberos) {
            modelobombero.addElement(bombero.getNombre());
        }

        // Creamos un JList a partir del modelo de lista
        lBomberos.setModel(modelobombero);

        // Añadimos la lista a un JScrollPane para poder hacer scroll si hay muchos elementos
        jspBomberos.setViewportView(lBomberos);
    }*/

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
