package DAO.Impl;

import DAO.Interfaces.GalaxyDAO;
import MyWindowApp.forms.mainForm;
import entity.Galaxy;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class GalaxyDAOImpl implements GalaxyDAO {

    @Override
    public Galaxy get(int id) throws SQLException {
        return null;
    }

    @Override
    public void getAll() throws SQLException {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Query<Galaxy> query = session.createQuery("FROM Galaxy", Galaxy.class);
        List<Galaxy> galaxies = query.getResultList();

        transaction.commit();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Name");

        for (Galaxy galaxy : galaxies) {
            Object[] row = {galaxy.getNameGalaxy()};
            model.addRow(row);
        }

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));

        session.close();
    }

    @Override
    public int save(Galaxy galaxy) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Galaxy galaxy) throws SQLException {
        return 0;
    }

    @Override
    public int update(Galaxy galaxy) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Galaxy galaxy) throws SQLException {
        return 0;
    }

}
