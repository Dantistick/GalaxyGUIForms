package MyWindowApp.menu;

import DAO.Impl.GalaxyDAOImpl;
import DAO.Impl.PlanetDAOImpl;
import DAO.Impl.SatelliteDAOImpl;
import MyWindowApp.forms.AddEdit.addEditGalaxyForm;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CreateMenu extends JFrame {

    private GalaxyDAOImpl galaxyDAO;
    private PlanetDAOImpl planetDAO;
    private SatelliteDAOImpl satelliteDAO;

    public JMenuBar createMenu(){

        galaxyDAO = new GalaxyDAOImpl();
        planetDAO = new PlanetDAOImpl();
        satelliteDAO = new SatelliteDAOImpl();

        JMenuBar jMenuBar = new JMenuBar();

        JMenu tables = new JMenu("Tables");
        JMenu crud = new JMenu("Crud");
        JMenu filters = new JMenu("Filter");

        jMenuBar.add(tables);
        jMenuBar.add(crud);
        jMenuBar.add(filters);

        JMenuItem table1 = new JMenuItem("Galaxies");
        JMenuItem table2 = new JMenuItem("Planets");
        JMenuItem table3 = new JMenuItem("Satellites");

        tables.add(table1);
        tables.add(table2);
        tables.add(table3);

        JMenuItem add = new JMenuItem("Add");
        JMenuItem edit = new JMenuItem("Edit");
        JMenuItem delete = new JMenuItem("Delete");

        crud.add(add);
        crud.add(edit);
        crud.add(delete);

        JMenuItem filterByName = new JMenuItem("By name");
        JMenuItem filterByNums = new JMenuItem("By nums");

        filters.add(filterByName);
        filters.add(filterByNums);

        setPreferredSizeForMenu(tables);
        setPreferredSizeForMenu(crud);
        setPreferredSizeForMenu(filters);

        setPreferredSizeForMenuItems(table1);
        setPreferredSizeForMenuItems(table2);
        setPreferredSizeForMenuItems(table3);

        setPreferredSizeForMenuItems(add);
        setPreferredSizeForMenuItems(edit);
        setPreferredSizeForMenuItems(delete);

        setPreferredSizeForMenuItems(filterByName);
        setPreferredSizeForMenuItems(filterByNums);

        add.addActionListener(e -> new addEditGalaxyForm());

        table1.addActionListener(e -> getAllEntities(galaxyDAO));
        table2.addActionListener(e -> getAllEntities(planetDAO));
        table3.addActionListener(e -> getAllEntities(satelliteDAO));

        return jMenuBar;
    }

    private static void setPreferredSizeForMenuItems(JMenuItem item){
        item.setPreferredSize(new Dimension(100,40));
    }

    private static void setPreferredSizeForMenu(JMenu menu){
        menu.setPreferredSize(new Dimension(80,30));
    }

    private void getAllEntities(GalaxyDAOImpl galaxy){
        try {
            galaxy.getAll();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void getAllEntities(PlanetDAOImpl planet){
        try {
            planet.getAll();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void getAllEntities(SatelliteDAOImpl satellite){
        try {
            satellite.getAll();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
