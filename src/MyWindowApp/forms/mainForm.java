package MyWindowApp.forms;

import MyWindowApp.menu.CreateMenu;
import hibernate.HibernateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class mainForm extends JFrame {

    public static JScrollPane scrollPane;

    public mainForm() {
        super("DB Galaxy");
        setLayout(new BorderLayout());

        scrollPane = new JScrollPane();

        CreateMenu createMenu = new CreateMenu();
        JMenuBar jMenuBar = createMenu.createMenu();
        add(jMenuBar, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) {
                HibernateUtil.close();
            }
        });
        setPreferredSize(new Dimension(1280, 720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
