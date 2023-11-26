package view;

import javax.swing.*;
import java.awt.*;

public class LibraryView extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JPanel Panel2;
    private JButton button2;

    public LibraryView(){
        setSize(1920,1080);
        setTitle("Test");
        Toolkit toolkit = getToolkit();
        Dimension size = getSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        addLibraryPanels();
        setVisible(true);
        setContentPane(panel1);
        pack();

    }
    public static void main(String[] args) {
        LibraryView libraryView = new LibraryView();

    }
    public void addLibraryPanels(){
        Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.PAGE_AXIS));
        for(int i = 0; i < 20; i++){
            LibraryItemPanel itemPanel = new LibraryItemPanel();
            Panel2.add(itemPanel);
            itemPanel.setVisible(true);
        }
    }
}
