package view;

import interface_adapters.RecommendHome.RecommendHomeState;
import interface_adapters.library.LibraryController;
import interface_adapters.library.LibraryState;
import interface_adapters.library.LibraryViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

public class LibraryView extends JPanel implements PropertyChangeListener {
    private JPanel panel1;
    private JButton button1;
    private JPanel Panel2;
    private JButton button2;
    private Map<String, LibraryItemPanel> libraryItemPanels;
    LibraryViewModel libraryViewModel;
    LibraryController libraryController;
    public LibraryView(LibraryViewModel libraryViewModel, LibraryController libraryController) {
        this.libraryViewModel = libraryViewModel;
        this.libraryController = libraryController;
        this.libraryViewModel.addPropertyChangeListener(this);

    }
    public static void main(String[] args) {

    }
    public void addLibraryPanels(LibraryState state){
        /*
        Creates small library item panels for each research paper that is currently bookmarked by the user.
         */
        Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.PAGE_AXIS));
        for(int i = 0; i < state.getTitles().size(); i++){
            if(!libraryItemPanels.containsKey(state.getTitles().get(i))){
                LibraryItemPanel itemPanel = new LibraryItemPanel(state.getTitles().get(i),state.getIds().get(i),state.getUrls().get(i));
                libraryItemPanels.put(state.getTitles().get(i),itemPanel);
                Panel2.add(itemPanel);
                itemPanel.setVisible(true);
                itemPanel.getViewButton().addActionListener(
                        // This creates an anonymous subclass of ActionListener and instantiates it.
                        new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                if (evt.getSource().equals(itemPanel.getViewButton())) {
                                    libraryController.execute(itemPanel.getUrl());
                                }
                            }
                        }
                );
            }
        }
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LibraryState state = (LibraryState) evt.getNewValue();
        addLibraryPanels(state);
    }
}
