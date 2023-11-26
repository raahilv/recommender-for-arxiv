package view;

import javax.swing.*;
import java.awt.*;

public class LibraryItemPanel extends JPanel {
    private JButton deleteButton;
    private JButton viewButton;
    private JLabel paperName;
    private JPanel MainPanel;

    public LibraryItemPanel(){
        setPreferredSize(new Dimension(250,50));
        setVisible(true);
        setLayout(new BorderLayout());
        add(MainPanel);
        MainPanel.setLayout(new GridLayout());
        MainPanel.add(paperName);
        MainPanel.add(viewButton);
        MainPanel.add(deleteButton);
    }
}
