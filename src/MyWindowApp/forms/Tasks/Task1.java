package MyWindowApp.forms.Tasks;

import javax.swing.*;
import java.awt.*;

public class Task1 extends JFrame {
    private static JTextField textFieldName;
    public Task1() {
        super("Task 1");
        setLayout(new GridLayout(0, 2, 10, 10));

        Tasks tasks = new Tasks();

        JLabel jLabelName = new JLabel("Name Galaxy:");
        jLabelName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        add(jLabelName);

        textFieldName = new JTextField();
        textFieldName.setFont(new Font("Times New Roman", Font.BOLD, 14));
        textFieldName.setPreferredSize(new Dimension(200, 25));
        add(textFieldName);

        JButton jButton = new JButton("Go");
        jButton.setPreferredSize(new Dimension(80, 25));
        jButton.addActionListener(e -> {
            tasks.task1(textFieldName.getText());
            this.dispose();
        });
        add(jButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 200));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
