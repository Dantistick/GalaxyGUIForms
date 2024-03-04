package DAO.Impl;

import DAO.Interfaces.PlanetDAO;
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

public class PlanetDAOImpl implements PlanetDAO {

    private final String SELECT_PLANET_WITH_GALAXY_NAME = "SELECT p.namePlanet, p.radius, p.coreTemperature, p.atmosphere, p.life, g.nameGalaxy " +
            "FROM Planet p " +
            "JOIN Galaxy g " +
            "ON p.idGalaxy = g.idGalaxy";

    @Override
    public Planet get(int id) throws SQLException {
        return null;
    }

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

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));

        session.close();
    }


    @Override
    public int save(Planet planet) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Planet planet) throws SQLException {
        return 0;
    }

    @Override
    public int update(Planet planet) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Planet planet) throws SQLException {
        return 0;
    }
}
