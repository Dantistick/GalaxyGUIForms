package MyWindowApp.forms.AddEdit;

import entity.Galaxy;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.awt.*;

public class addEditGalaxyForm extends JFrame {

    public addEditGalaxyForm() {
        super("Add Galaxy");
        setLayout(new GridLayout(0, 2, 10, 10));

        JLabel jLabelName = new JLabel("Name:");
        jLabelName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelName);

        JTextField textFieldName = new JTextField();
        textFieldName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldName.setPreferredSize(new Dimension(200, 25));
        add(textFieldName);

        JButton jButton = new JButton("Add");
        jButton.setPreferredSize(new Dimension(80, 25));
        jButton.addActionListener(e -> {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();

            Transaction transaction = session.beginTransaction();

            try {
                Galaxy newGalaxy = new Galaxy();
                newGalaxy.setNameGalaxy(textFieldName.getText());
                session.save(newGalaxy);

                transaction.commit();
            } catch (Exception ex) {
                transaction.rollback();
                ex.printStackTrace();
            } finally {
                session.close();
                dispose();
            }
        });
        add(jButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new addEditGalaxyForm();
    }
}
