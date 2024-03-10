package MyWindowApp.menu;

import DAO.Impl.GalaxyDAOImpl;
import DAO.Impl.PlanetDAOImpl;
import DAO.Impl.SatelliteDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CreateMenu extends JFrame {

    public static JTable table;
    private GalaxyDAOImpl galaxyDAO;
    private PlanetDAOImpl planetDAO;
    private SatelliteDAOImpl satelliteDAO;
    private ActionFromButtons actionFromButtons;
    public static CurrentTable currentTable = null;

    private JMenu tables;
    private JMenu crud;
    private JMenu filters;

    public JMenuBar createMenu(){

        galaxyDAO = new GalaxyDAOImpl();
        planetDAO = new PlanetDAOImpl();
        satelliteDAO = new SatelliteDAOImpl();
        actionFromButtons = new ActionFromButtons();

        JMenuBar jMenuBar = new JMenuBar();

        tables = new JMenu("Tables");

        crud = new JMenu("Crud");
        crud.setVisible(false);

        filters = new JMenu("Filter");
        filters.setVisible(false);

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

        add.addActionListener(e -> actionFromButtons.setActionListenerOnAddButton());
        edit.addActionListener(e -> actionFromButtons.setActionListenerOnEditButton());
        delete.addActionListener(e -> actionFromButtons.setActionListenerOnDeleteButton());

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
        selectFirstTable();
        try {
            galaxy.getAll();
            currentTable = CurrentTable.GALAXY;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void getAllEntities(PlanetDAOImpl planet){
        selectFirstTable();
        try {
            planet.getAll();
            currentTable = CurrentTable.PLANET;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void getAllEntities(SatelliteDAOImpl satellite){
        selectFirstTable();
        try {
            satellite.getAll();
            currentTable = CurrentTable.SATELLITE;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void selectFirstTable(){
        crud.setVisible(true);
        filters.setVisible(true);
    }
}
