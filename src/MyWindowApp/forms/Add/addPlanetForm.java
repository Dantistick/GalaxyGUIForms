package MyWindowApp.forms.Add;

import DAO.Impl.PlanetDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class addPlanetForm extends JFrame {
    public static JTextField textFieldNamePlanet;
    public static JTextField textFieldRadius;
    public static JTextField textFieldCoreTemperature;
    public static JCheckBox checkBoxAtmosphere;
    public static JCheckBox checkBoxLife;
    public static JComboBox<String> comboBoxGalaxies;

    public addPlanetForm() {
        super("Add Planet");
        setLayout(new GridLayout(0, 2, 10, 10));

        PlanetDAOImpl planetDAO = new PlanetDAOImpl();

        JLabel jLabelNamePlanet = new JLabel("Planet Name:");
        jLabelNamePlanet.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelNamePlanet);

        textFieldNamePlanet = new JTextField();
        textFieldNamePlanet.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldNamePlanet.setPreferredSize(new Dimension(200, 25));
        add(textFieldNamePlanet);

        JLabel jLabelRadius = new JLabel("Radius:");
        jLabelRadius.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelRadius);

        textFieldRadius = new JTextField();
        textFieldRadius.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldRadius.setPreferredSize(new Dimension(200, 25));
        add(textFieldRadius);

        JLabel jLabelCoreTemperature = new JLabel("Core Temperature:");
        jLabelCoreTemperature.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelCoreTemperature);

        textFieldCoreTemperature = new JTextField();
        textFieldCoreTemperature.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldCoreTemperature.setPreferredSize(new Dimension(200, 25));
        add(textFieldCoreTemperature);

        checkBoxAtmosphere = new JCheckBox("Atmosphere:");
        checkBoxAtmosphere.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(checkBoxAtmosphere);

        checkBoxLife = new JCheckBox("Life:");
        checkBoxLife.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(checkBoxLife);

        JLabel jLabelWhichGalaxy = new JLabel("Which galaxy:");
        jLabelWhichGalaxy.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelWhichGalaxy);

        comboBoxGalaxies = planetDAO.getNamesGalaxies();
        comboBoxGalaxies.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(comboBoxGalaxies);

        JButton jButton = new JButton("Add");
        jButton.setPreferredSize(new Dimension(80, 25));
        jButton.addActionListener(e -> {
            try {
                planetDAO.insert();
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