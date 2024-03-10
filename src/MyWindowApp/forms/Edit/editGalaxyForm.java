package MyWindowApp.forms.Edit;

import DAO.Impl.GalaxyDAOImpl;
import MyWindowApp.forms.Add.addGalaxyForm;
import entity.Galaxy;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class editGalaxyForm extends JFrame {
    public static JTextField textFieldName;

    public editGalaxyForm(Galaxy galaxy) {
        super("Edit Galaxy");
        setLayout(new GridLayout(0, 2, 10, 10));

        GalaxyDAOImpl galaxyDAO = new GalaxyDAOImpl();

        JLabel jLabelName = new JLabel("Name:");
        jLabelName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelName);

        textFieldName = new JTextField(galaxy.getNameGalaxy());
        textFieldName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldName.setPreferredSize(new Dimension(200, 25));
        add(textFieldName);

        JButton jButton = new JButton("Edit");
        jButton.setPreferredSize(new Dimension(80, 25));
        jButton.addActionListener(e -> galaxyDAO.acceptEditable(galaxy));
        add(jButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
