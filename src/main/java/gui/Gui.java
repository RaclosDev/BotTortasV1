package gui;

import javax.swing.*;

public class Gui extends JFrame{
    private JButton apagarButton;
    private JPanel rootPanel;
    private JLabel Text;

    public Gui() {
        setTitle("TortasBot - By:Raclos");
        add(rootPanel);
        setSize(200,200);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        apagarButton.addActionListener(e -> System.exit(0));
    }
}
