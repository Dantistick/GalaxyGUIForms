package MyWindowApp.forms;

import MyWindowApp.menu.CreateMenu;
import javax.swing.*;
import java.awt.*;

public class mainForm extends JFrame {

    public mainForm() {
        super("BD Galaxy");
        setLayout(new BorderLayout());

        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar = CreateMenu.createMenu(jMenuBar);
        add(jMenuBar, BorderLayout.NORTH);

        JTable tableInfo = new JTable();
        add(new JScrollPane(tableInfo), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
