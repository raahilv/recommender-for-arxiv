package view;

import javax.swing.*;
import java.awt.*;

public class LibraryView extends JPanel {
    private JPanel panel1;
    private JButton button1;
    private JPanel Panel2;
    private JButton button2;

    public LibraryView(){
        addLibraryPanels();
    }
    public static void main(String[] args) {

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
