package DAO.Impl;

import DAO.Interfaces.SatelliteDAO;
import MyWindowApp.forms.Add.addSatelliteForm;
import MyWindowApp.forms.Edit.editSatelliteForm;
import MyWindowApp.forms.mainForm;
import entity.Satellite;
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

public class SatelliteDAOImpl implements SatelliteDAO {

    private final String SELECT_SATELLITE_WITH_PLANET_NAME = "SELECT s.nameSatellite, s.radius, s.distanceToPlanet, p.namePlanet " +
            "FROM Satellite s " +
            "JOIN Planet p " +
            "ON s.idPlanet = p.idPlanet";
    private final String SELECT_NAMES_PLANETS = "SELECT namePlanet FROM Planet ";
    @Override
    public void getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query<Object[]> query = session.createQuery(SELECT_SATELLITE_WITH_PLANET_NAME);
        List<Object[]> result = query.getResultList();

        transaction.commit();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Radius");
        model.addColumn("Distance to planet");
        model.addColumn("Planet name");

        for (Object[] row : result) {
            String namePlanet = (String) row[0];
            BigDecimal radius = (BigDecimal) row[1];
            BigDecimal distance = (BigDecimal) row[2];
            String planetName = (String) row[3];

            Object[] rowData = {namePlanet, radius, distance,planetName};
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

            String nameSatellite = addSatelliteForm.textFieldNameSatellite.getText().trim();
            String radiusSatellite = addSatelliteForm.textFieldRadius.getText().trim();
            String distanceToPlanet = addSatelliteForm.textFieldDistanceToPlanet.getText().trim();
            String namePlanet = addSatelliteForm.comboBoxPlanets.getSelectedItem().toString();

            if (nameSatellite.isEmpty() || radiusSatellite.isEmpty() || distanceToPlanet.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter all values", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Satellite newSatellite = new Satellite();
            newSatellite.setNameSatellite(nameSatellite);
            newSatellite.setRadius(new BigDecimal(radiusSatellite));
            newSatellite.setDistanceToPlanet(new BigDecimal(distanceToPlanet));

            Query query = session.createQuery("SELECT idPlanet FROM Planet WHERE namePlanet = :name");
            query.setParameter("name", namePlanet);

            Integer planetId = (Integer) query.uniqueResult();
            if (planetId == null) {
                JOptionPane.showMessageDialog(null, "Satellite with specified name not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            newSatellite.setIdPlanet(planetId);

            session.save(newSatellite);

            transaction.commit();

            JOptionPane.showMessageDialog(null, "Satellite inserted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

            addSatelliteForm.textFieldNameSatellite.setText("");
            addSatelliteForm.textFieldRadius.setText("");
            addSatelliteForm.textFieldDistanceToPlanet.setText("");

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
                        Query<Satellite> query = session.createQuery("from Satellite where nameSatellite = :name", Satellite.class);
                        query.setParameter("name", name);
                        List<Satellite> satellites = query.list();

                        if (!satellites.isEmpty()) {
                            Satellite satellite = satellites.get(0);
                            new editSatelliteForm(satellite);
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

                    int confirmResult = JOptionPane.showConfirmDialog(null, "Вы уверены, что хотите удалить спутник " + name + "?", "Подтверждение удаления", JOptionPane.YES_NO_OPTION);

                    if (confirmResult == JOptionPane.YES_OPTION) {
                        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                            Transaction transaction = session.beginTransaction();

                            Query<Satellite> query = session.createQuery("from Satellite where nameSatellite = :name", Satellite.class);
                            query.setParameter("name", name);
                            List<Satellite> satellites = query.list();

                            if (!satellites.isEmpty()) {
                                Satellite satellite = satellites.get(0);
                                session.delete(satellite);
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

    public JComboBox<String> getNamesPlanets(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<String> query = session.createQuery(SELECT_NAMES_PLANETS, String.class);
        List<String> result = query.getResultList();

        JComboBox<String> comboBox = new JComboBox<>();

        for (String name : result) {
            comboBox.addItem(name);
        }

        transaction.commit();
        session.close();

        return comboBox;
    }

    public void acceptEditable(Satellite satellite){
        Transaction transaction = null;

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            satellite.setNameSatellite(editSatelliteForm.textFieldNameSatellite.getText());
            satellite.setRadius(new BigDecimal(editSatelliteForm.textFieldRadius.getText()));
            satellite.setDistanceToPlanet(new BigDecimal(editSatelliteForm.textFieldDistanceToPlanet.getText()));

            Query query = session.createQuery("SELECT idPlanet FROM Planet WHERE namePlanet = :name");
            query.setParameter("name", editSatelliteForm.comboBoxPlanets.getSelectedItem());

            Integer planetId = (Integer) query.uniqueResult();
            if (planetId == null) {
                JOptionPane.showMessageDialog(null, "Planet with specified name not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            satellite.setIdPlanet(planetId);

            session.update(satellite);

            transaction.commit();
            JOptionPane.showMessageDialog(null, "Satellite updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            getAll();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
