package MyWindowApp.forms.Tasks;

import MyWindowApp.forms.mainForm;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Tasks {
    public void task1(String galaxyName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<Object[]> query = session.createQuery(
                "SELECT s.nameSatellite, s.radius, s.distanceToPlanet, p.namePlanet, p.radius, p.coreTemperature, p.atmosphere, p.life " +
                        "FROM Satellite s " +
                        "JOIN Planet p ON s.idPlanet = p.idPlanet " +
                        "JOIN Galaxy g ON p.idGalaxy = g.idGalaxy " +
                        "WHERE p.life = 1 AND g.nameGalaxy = :galaxyName", Object[].class);

        query.setParameter("galaxyName", galaxyName);
        List<Object[]> results = query.getResultList();

        transaction.commit();
        session.close();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Satellite");
        model.addColumn("Satellite radius");
        model.addColumn("Distance to planet");
        model.addColumn("Planet");
        model.addColumn("Planet radius");
        model.addColumn("Core Temperature");
        model.addColumn("Atmosphere");
        model.addColumn("Life");

        for (Object[] result : results) {
            model.addRow(result);
        }

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));
    }

    public void task2(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<Object[]> query = session.createQuery(
                "SELECT s.nameSatellite, s.radius, s.distanceToPlanet, p.namePlanet, p.radius, p.coreTemperature, p.atmosphere, p.life " +
                        "FROM Planet p " +
                        "JOIN Satellite s ON p.idPlanet = s.idPlanet " +
                        "WHERE p.radius = (SELECT MIN(p2.radius) FROM Planet p2) AND " +
                        "      s.radius = (SELECT MAX(s2.radius) FROM Satellite s2)", Object[].class);

        List<Object[]> results = query.getResultList();

        transaction.commit();
        session.close();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Satellite");
        model.addColumn("Satellite radius");
        model.addColumn("Distance to planet");
        model.addColumn("Planet");
        model.addColumn("Planet radius");
        model.addColumn("Core Temperature");
        model.addColumn("Atmosphere");
        model.addColumn("Life");

        for (Object[] result : results) {
            model.addRow(result);
        }

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));
    }

    public void task3(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<Object[]> query = session.createQuery(
                "SELECT p.namePlanet AS PlanetName, g.nameGalaxy AS GalaxyName, " +
                        "       COUNT(s.idSatellite) AS NumSatellites, SUM(s.radius) AS TotalVolume " +
                        "FROM Planet p " +
                        "JOIN Galaxy g ON p.idGalaxy = g.idGalaxy " +
                        "JOIN Satellite s ON p.idPlanet = s.idPlanet " +
                        "GROUP BY p.idPlanet, g.idGalaxy " +
                        "ORDER BY count(s.idSatellite) DESC, sum(s.radius) ASC", Object[].class);

        query.setMaxResults(1);

        List<Object[]> results = query.getResultList();

        transaction.commit();
        session.close();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Planet Name");
        model.addColumn("Galaxy Name");
        model.addColumn("Num Satellite");
        model.addColumn("Total volume");

        for (Object[] result : results) {
            model.addRow(result);
        }

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));
    }

    public void task4(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query<Object[]> query = session.createQuery(
                "SELECT g.nameGalaxy AS GalaxyName, SUM(p.coreTemperature) AS TotalCoreTemperature " +
                        "FROM Galaxy g " +
                        "JOIN Planet p ON g.idGalaxy = p.idGalaxy " +
                        "GROUP BY g.idGalaxy " +
                        "ORDER BY SUM(p.coreTemperature) DESC " +
                        "LIMIT 1", Object[].class);

        query.setMaxResults(1);

        List<Object[]> results = query.getResultList();

        transaction.commit();
        session.close();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Galaxy Name");
        model.addColumn("Total Core Temperature");

        for (Object[] result : results) {
            model.addRow(result);
        }

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));
    }
}