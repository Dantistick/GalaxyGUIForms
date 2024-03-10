package MyWindowApp.forms.Add;

import DAO.Impl.SatelliteDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class addSatelliteForm extends JFrame {
    public static JTextField textFieldNameSatellite;
    public static JTextField textFieldRadius;
    public static JTextField textFieldDistanceToPlanet;
    public static JComboBox<String> comboBoxPlanets;

    public addSatelliteForm() {
        super("Add Satellite");
        setLayout(new GridLayout(0, 2, 10, 10));

        SatelliteDAOImpl satelliteDAO = new SatelliteDAOImpl();

        JLabel jLabelNameSatellite = new JLabel("Satellite Name:");
        jLabelNameSatellite.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelNameSatellite);

        textFieldNameSatellite = new JTextField();
        textFieldNameSatellite.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldNameSatellite.setPreferredSize(new Dimension(200, 25));
        add(textFieldNameSatellite);

        JLabel jLabelRadius = new JLabel("Radius:");
        jLabelRadius.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelRadius);

        textFieldRadius = new JTextField();
        textFieldRadius.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldRadius.setPreferredSize(new Dimension(200, 25));
        add(textFieldRadius);

        JLabel jLabelDistanceToPlanet = new JLabel("Distance to planet:");
        jLabelDistanceToPlanet.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelDistanceToPlanet);

        textFieldDistanceToPlanet = new JTextField();
        textFieldDistanceToPlanet.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldDistanceToPlanet.setPreferredSize(new Dimension(200, 25));
        add(textFieldDistanceToPlanet);

        JLabel jLabelWhichGalaxy = new JLabel("Which planet:");
        jLabelWhichGalaxy.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelWhichGalaxy);

        comboBoxPlanets = satelliteDAO.getNamesPlanets();
        comboBoxPlanets.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(comboBoxPlanets);

        JButton jButton = new JButton("Add");
        jButton.setPreferredSize(new Dimension(80, 25));
        jButton.addActionListener(e -> {
            try {
                satelliteDAO.insert();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        add(jButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}