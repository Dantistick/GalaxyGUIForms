package MyWindowApp.forms;

import javax.swing.*;
import java.awt.*;

public class mainForm extends JFrame {

    mainForm(){
        super("BD Galaxy");
        setLayout(new BorderLayout());

        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);

        JMenu tables = new JMenu("Tables");
        JMenu crud = new JMenu("Crud");
        JMenu filters = new JMenu("Filter");

        jMenuBar.add(tables);
        jMenuBar.add(crud);
        jMenuBar.add(filters);

        JMenuItem table1 = new JMenuItem("Table1");
        JMenuItem table2 = new JMenuItem("Table2");
        JMenuItem table3 = new JMenuItem("Table3");

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

        JTable tableInfo = new JTable();
        add(new JScrollPane(tableInfo), BorderLayout.CENTER);

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void setPreferredSizeForMenuItems(JMenuItem item){
        item.setPreferredSize(new Dimension(100,40));
    }

    public void setPreferredSizeForMenu(JMenu menu){
        menu.setPreferredSize(new Dimension(80,30));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(mainForm::new);
    }
}
