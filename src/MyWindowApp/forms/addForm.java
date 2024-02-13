package MyWindowApp.forms;

import javax.swing.*;
import java.awt.*;

public class addForm extends JFrame {

    addForm() {
        super("Add Form");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(720, 480));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
