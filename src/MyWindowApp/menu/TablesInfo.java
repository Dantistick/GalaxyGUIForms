package MyWindowApp.menu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import MyWindowApp.forms.mainForm;
import entity.Planet;
import entity.Satellite;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import hibernate.HibernateUtil;
import entity.Galaxy;

import java.util.List;

public class TablesInfo extends JFrame {

    public void galaxyInfo(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Query<Galaxy> query = session.createQuery("FROM Galaxy", Galaxy.class);

        List<Galaxy> galaxies = query.getResultList();

        transaction.commit();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");

        for (Galaxy galaxy : galaxies) {
            Object[] row = {galaxy.getIdGalaxy(), galaxy.getNameGalaxy()};
            model.addRow(row);
        }

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));

        session.close();
    }

    public void planetInfo(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Query<Planet> query = session.createQuery("FROM Planet", Planet.class);

        List<Planet> planets = query.getResultList();

        transaction.commit();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Radius");
        model.addColumn("Core temperature");
        model.addColumn("Atmosphere");
        model.addColumn("Life");
        model.addColumn("ID Galaxy");

        for (Planet planet : planets) {
            Object[] row = {planet.getIdPlanet(), planet.getNamePlanet(), planet.getRadius(), planet.getCoreTemperature(),planet.getAtmosphere(),planet.getLife(),planet.getIdGalaxy()};
            model.addRow(row);
        }

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));

        session.close();
    }

    public void satelliteInfo(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Query<Satellite> query = session.createQuery("FROM Satellite ", Satellite.class);

        List<Satellite> satellites = query.getResultList();

        transaction.commit();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Radius");
        model.addColumn("Distance to planet");
        model.addColumn("ID Planet");

        for (Satellite satellite : satellites) {
            Object[] row = {satellite.getIdSatellite(), satellite.getNameSatellite(), satellite.getRadius(), satellite.getDistanceToPlanet(),satellite.getIdPlanet()};
            model.addRow(row);
        }

        mainForm.scrollPane.setViewportView(new JScrollPane(new JTable(model)));

        session.close();
    }
}
