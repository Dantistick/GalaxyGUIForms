package MyWindowApp.menu;

import DAO.Impl.GalaxyDAOImpl;
import DAO.Impl.PlanetDAOImpl;
import DAO.Impl.SatelliteDAOImpl;
import MyWindowApp.forms.Tasks.Task1;
import MyWindowApp.forms.Tasks.Tasks;

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
    private JMenu tasks;

    public JMenuBar createMenu(){

        galaxyDAO = new GalaxyDAOImpl();
        planetDAO = new PlanetDAOImpl();
        satelliteDAO = new SatelliteDAOImpl();
        actionFromButtons = new ActionFromButtons();

        JMenuBar jMenuBar = new JMenuBar();

        tables = new JMenu("Tables");

        crud = new JMenu("Crud");
        crud.setVisible(false);

        filters = new JMenu("Filters");
        filters.setVisible(false);

        tasks = new JMenu("Tasks");
        tasks.setVisible(false);

        jMenuBar.add(tables);
        jMenuBar.add(crud);
        jMenuBar.add(filters);
        jMenuBar.add(tasks);

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

        JMenuItem task1 = new JMenuItem("Task 1");
        JMenuItem task2 = new JMenuItem("Task 2");
        JMenuItem task3 = new JMenuItem("Task 3");
        JMenuItem task4 = new JMenuItem("Task 4");

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        setPreferredSizeForMenu(tables);
        setPreferredSizeForMenu(crud);
        setPreferredSizeForMenu(filters);
        setPreferredSizeForMenu(tasks);

        setPreferredSizeForMenuItems(table1);
        setPreferredSizeForMenuItems(table2);
        setPreferredSizeForMenuItems(table3);

        setPreferredSizeForMenuItems(add);
        setPreferredSizeForMenuItems(edit);
        setPreferredSizeForMenuItems(delete);

        setPreferredSizeForMenuItems(filterByName);
        setPreferredSizeForMenuItems(filterByNums);

        setPreferredSizeForMenuItems(task1);
        setPreferredSizeForMenuItems(task2);
        setPreferredSizeForMenuItems(task3);
        setPreferredSizeForMenuItems(task4);

        add.addActionListener(e -> actionFromButtons.setActionListenerOnAddButton());
        edit.addActionListener(e -> actionFromButtons.setActionListenerOnEditButton());
        delete.addActionListener(e -> actionFromButtons.setActionListenerOnDeleteButton());

        table1.addActionListener(e -> getAllEntities(galaxyDAO));
        table2.addActionListener(e -> getAllEntities(planetDAO));
        table3.addActionListener(e -> getAllEntities(satelliteDAO));

        Tasks tasks = new Tasks();

        task1.addActionListener(e -> {
            new Task1();
            selectVisibleAttributeFalse();
        });
        task2.addActionListener(e -> {
            tasks.task2();
            selectVisibleAttributeFalse();
        });
        task3.addActionListener(e -> {
            tasks.task3();
            selectVisibleAttributeFalse();
        });
        task4.addActionListener(e -> {
            tasks.task4();
            selectVisibleAttributeFalse();
        });

        return jMenuBar;
    }

    private static void setPreferredSizeForMenuItems(JMenuItem item){
        item.setPreferredSize(new Dimension(100,40));
    }

    private static void setPreferredSizeForMenu(JMenu menu){
        menu.setPreferredSize(new Dimension(80,30));
    }

    private void getAllEntities(GalaxyDAOImpl galaxy){
        selectVisibleAttributeTrue();
        try {
            galaxy.getAll();
            currentTable = CurrentTable.GALAXY;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void getAllEntities(PlanetDAOImpl planet){
        selectVisibleAttributeTrue();
        try {
            planet.getAll();
            currentTable = CurrentTable.PLANET;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void getAllEntities(SatelliteDAOImpl satellite){
        selectVisibleAttributeTrue();
        try {
            satellite.getAll();
            currentTable = CurrentTable.SATELLITE;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void selectVisibleAttributeTrue(){
        crud.setVisible(true);
        filters.setVisible(true);
        tasks.setVisible(true);
    }

    private void selectVisibleAttributeFalse(){
        crud.setVisible(false);
        filters.setVisible(false);
    }
}
