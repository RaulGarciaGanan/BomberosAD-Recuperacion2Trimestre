package Ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Autor {
    public Main m;
    public JFrame frame;
    private JPanel panel1;
    private JButton bSalir;

    public  Autor() throws SQLException {
        m = new Main();
        m.frame.setVisible(false);

        frame = new JFrame("Autor");
        frame.setContentPane(panel1);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        bSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                m.frame.setVisible(true);
            }
        });
    }
}
