package MyWindowApp.forms.Edit;

import DAO.Impl.PlanetDAOImpl;
import entity.Planet;

import javax.swing.*;
import java.awt.*;

public class editPlanetForm extends JFrame {
    public static JTextField textFieldNamePlanet;
    public static JTextField textFieldRadius;
    public static JTextField textFieldCoreTemperature;
    public static JCheckBox checkBoxAtmosphere;
    public static JCheckBox checkBoxLife;
    public static JComboBox<String> comboBoxGalaxies;

    public editPlanetForm(Planet planet) {
        super("Add Planet");
        setLayout(new GridLayout(0, 2, 10, 10));

        PlanetDAOImpl planetDAO = new PlanetDAOImpl();

        JLabel jLabelNamePlanet = new JLabel("Planet Name:");
        jLabelNamePlanet.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelNamePlanet);

        textFieldNamePlanet = new JTextField(planet.getNamePlanet());
        textFieldNamePlanet.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldNamePlanet.setPreferredSize(new Dimension(200, 25));
        add(textFieldNamePlanet);

        JLabel jLabelRadius = new JLabel("Radius:");
        jLabelRadius.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelRadius);

        textFieldRadius = new JTextField(planet.getRadius().toString());
        textFieldRadius.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldRadius.setPreferredSize(new Dimension(200, 25));
        add(textFieldRadius);

        JLabel jLabelCoreTemperature = new JLabel("Core Temperature:");
        jLabelCoreTemperature.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelCoreTemperature);

        textFieldCoreTemperature = new JTextField(planet.getCoreTemperature().toString());
        textFieldCoreTemperature.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldCoreTemperature.setPreferredSize(new Dimension(200, 25));
        add(textFieldCoreTemperature);

        checkBoxAtmosphere = new JCheckBox("Atmosphere:");
        checkBoxAtmosphere.setSelected(planet.getAtmosphere() == 1);
        checkBoxAtmosphere.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(checkBoxAtmosphere);

        checkBoxLife = new JCheckBox("Life:");
        checkBoxLife.setSelected(planet.getLife() == 1);
        checkBoxLife.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(checkBoxLife);

        JLabel jLabelWhichGalaxy = new JLabel("Which galaxy:");
        jLabelWhichGalaxy.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelWhichGalaxy);

        comboBoxGalaxies = planetDAO.getNamesGalaxies();
        comboBoxGalaxies.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(comboBoxGalaxies);

        JButton jButton = new JButton("Edit");
        jButton.setPreferredSize(new Dimension(80, 25));
        jButton.addActionListener(e -> planetDAO.acceptEditable(planet));
        add(jButton);

        add(jButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}