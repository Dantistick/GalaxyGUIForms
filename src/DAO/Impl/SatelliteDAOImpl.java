package DAO.Impl;

import DAO.Interfaces.SatelliteDAO;
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

public class SatelliteDAOImpl implements SatelliteDAO {

    private final String SELECT_SATELLITE_WITH_PLANET_NAME = "SELECT s.nameSatellite, s.radius, s.distanceToPlanet, p.namePlanet " +
            "FROM Satellite s " +
            "JOIN Planet p " +
            "ON s.idPlanet = p.idPlanet";

    @Override
    public Satellite get(int id) throws SQLException {
        return null;
    }

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

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));

        session.close();
    }

    @Override
    public int save(Satellite satellite) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Satellite satellite) throws SQLException {
        return 0;
    }

    @Override
    public int update(Satellite satellite) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Satellite satellite) throws SQLException {
        return 0;
    }
}
