package MyWindowApp.menu;

import MyWindowApp.forms.addForm;

import javax.swing.*;
import java.awt.*;

public class CreateMenu extends JFrame{
    public JMenuBar createMenu(){
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

        add.addActionListener(e -> new addForm());

        TablesInfo tablesInfo = new TablesInfo();
        table1.addActionListener(e -> tablesInfo.galaxyInfo());
        table2.addActionListener(e -> tablesInfo.planetInfo());
        table3.addActionListener(e -> tablesInfo.satelliteInfo());

        return jMenuBar;
    }

    public static void setPreferredSizeForMenuItems(JMenuItem item){
        item.setPreferredSize(new Dimension(100,40));
    }

    public static void setPreferredSizeForMenu(JMenu menu){
        menu.setPreferredSize(new Dimension(80,30));
    }

}
