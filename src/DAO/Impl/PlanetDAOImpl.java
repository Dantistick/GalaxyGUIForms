package DAO.Impl;

import DAO.Interfaces.PlanetDAO;
import MyWindowApp.forms.Add.addPlanetForm;
import MyWindowApp.forms.Edit.editPlanetForm;
import MyWindowApp.forms.mainForm;
import entity.Planet;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static MyWindowApp.menu.CreateMenu.table;

public class PlanetDAOImpl implements PlanetDAO {

    private final String SELECT_PLANET_WITH_GALAXY_NAME = "SELECT p.namePlanet, p.radius, p.coreTemperature, p.atmosphere, p.life, g.nameGalaxy " +
            "FROM Planet p " +
            "JOIN Galaxy g " +
            "ON p.idGalaxy = g.idGalaxy";

    private final String SELECT_NAMES_GALAXIES = "SELECT nameGalaxy FROM Galaxy ";

    @Override
    public void getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query<Object[]> query = session.createQuery(SELECT_PLANET_WITH_GALAXY_NAME);
        List<Object[]> result = query.getResultList();

        transaction.commit();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Radius");
        model.addColumn("Core temperature");
        model.addColumn("Atmosphere");
        model.addColumn("Life");
        model.addColumn("Galaxy name");

        for (Object[] row : result) {
            String namePlanet = (String) row[0];
            BigDecimal radius = (BigDecimal) row[1];
            BigDecimal coreTemperature = (BigDecimal) row[2];
            byte atmosphereByte = (byte) row[3];
            byte lifeByte = (byte) row[4];
            boolean atmosphere = atmosphereByte != 0;
            boolean life = lifeByte != 0;

            String galaxyName = (String) row[5];

            Object[] rowData = {namePlanet, radius, coreTemperature, atmosphere, life, galaxyName};
            model.addRow(rowData);
        }

        table = new JTable(model);

        mainForm.scrollPane.setViewportView(new JScrollPane(table));

        session.close();
    }

    @Override
    public void insert() throws SQLException {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String planetName = addPlanetForm.textFieldNamePlanet.getText().trim();
            String radiusPlanet = addPlanetForm.textFieldRadius.getText().trim();
            String core_temperaturePlanet = addPlanetForm.textFieldCoreTemperature.getText().trim();
            boolean atmosphere = addPlanetForm.checkBoxAtmosphere.isSelected();
            boolean life = addPlanetForm.checkBoxLife.isSelected();
            String nameGalaxy = addPlanetForm.comboBoxGalaxies.getSelectedItem().toString();

            if (planetName.isEmpty() || radiusPlanet.isEmpty() || core_temperaturePlanet.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter all values", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Planet newPlanet = new Planet();
            newPlanet.setNamePlanet(planetName);
            newPlanet.setRadius(new BigDecimal(radiusPlanet));
            newPlanet.setCoreTemperature(new BigDecimal(core_temperaturePlanet));
            newPlanet.setAtmosphere((byte) (atmosphere ? 1 : 0));
            newPlanet.setLife((byte) (life ? 1 : 0));

            Query query = session.createQuery("SELECT idGalaxy FROM Galaxy WHERE nameGalaxy = :name");
            query.setParameter("name", nameGalaxy);

            Integer galaxyId = (Integer) query.uniqueResult();
            if (galaxyId == null) {
                JOptionPane.showMessageDialog(null, "Galaxy with specified name not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            newPlanet.setIdGalaxy(galaxyId);

            session.save(newPlanet);

            transaction.commit();

            JOptionPane.showMessageDialog(null, "Planet inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            addPlanetForm.textFieldNamePlanet.setText("");
            addPlanetForm.textFieldRadius.setText("");
            addPlanetForm.textFieldCoreTemperature.setText("");
            addPlanetForm.checkBoxAtmosphere.setSelected(false);
            addPlanetForm.checkBoxLife.setSelected(false);

            getAll();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            throw new SQLException("Failed to insert planet", ex);
        }
    }

    @Override
    public void update() throws SQLException {
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) table.getValueAt(selectedRow, 0);

                    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                        Query<Planet> query = session.createQuery("from Planet where namePlanet = :name", Planet.class);
                        query.setParameter("name", name);
                        List<Planet> planets = query.list();

                        if (!planets.isEmpty()) {
                            Planet planet = planets.get(0);
                            new editPlanetForm(planet);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void delete() throws SQLException {
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String name = (String) table.getValueAt(selectedRow, 0);

                    int confirmResult = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить планету " + name + "?", "Подтверждение удаления", JOptionPane.YES_NO_OPTION);

                    if (confirmResult == JOptionPane.YES_OPTION) {
                        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                            Transaction transaction = session.beginTransaction();

                            Query<Planet> query = session.createQuery("from Planet where namePlanet = :name", Planet.class);
                            query.setParameter("name", name);
                            List<Planet> planets = query.list();

                            if (!planets.isEmpty()) {
                                Planet planet = planets.get(0);
                                session.delete(planet);
                                transaction.commit();
                                getAll();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public JComboBox<String> getNamesGalaxies(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery(SELECT_NAMES_GALAXIES, String.class);
        List<String> result = query.getResultList();

        JComboBox<String> comboBox = new JComboBox<>();

        for (String name : result) {
            comboBox.addItem(name);
        }

        transaction.commit();
        session.close();

        return comboBox;
    }

    public void acceptEditable(Planet planet){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            planet.setNamePlanet(editPlanetForm.textFieldNamePlanet.getText());
            planet.setRadius(new BigDecimal(editPlanetForm.textFieldRadius.getText()));
            planet.setCoreTemperature(new BigDecimal(editPlanetForm.textFieldCoreTemperature.getText()));
            planet.setAtmosphere((byte) (editPlanetForm.checkBoxAtmosphere.isSelected() ? 1 : 0));
            planet.setLife((byte) (editPlanetForm.checkBoxLife.isSelected() ? 1 : 0));

            Query query = session.createQuery("SELECT idGalaxy FROM Galaxy WHERE nameGalaxy = :name");
            query.setParameter("name", editPlanetForm.comboBoxGalaxies.getSelectedItem());

            Integer galaxyId = (Integer) query.uniqueResult();
            if (galaxyId == null) {
                JOptionPane.showMessageDialog(null, "Galaxy with specified name not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            planet.setIdGalaxy(galaxyId);

            session.update(planet);

            transaction.commit();
            JOptionPane.showMessageDialog(null, "Planet updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            getAll();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
