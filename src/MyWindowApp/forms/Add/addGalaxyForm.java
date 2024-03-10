package MyWindowApp.forms.Add;

import DAO.Impl.GalaxyDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class addGalaxyForm extends JFrame {
    public static JTextField textFieldName;
    public addGalaxyForm() {
        super("Add Galaxy");
        setLayout(new GridLayout(0, 2, 10, 10));

        GalaxyDAOImpl galaxyDAO = new GalaxyDAOImpl();

        JLabel jLabelName = new JLabel("Name:");
        jLabelName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelName);

        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldName.setPreferredSize(new Dimension(200, 25));
        add(textFieldName);

        JButton jButton = new JButton("Add");
        jButton.setPreferredSize(new Dimension(80, 25));
        jButton.addActionListener(e -> {
            try {
                galaxyDAO.insert();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        add(jButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
