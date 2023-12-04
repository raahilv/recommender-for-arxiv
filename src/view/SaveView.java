package view;

import interface_adapters.save.SaveState;
import interface_adapters.save.SaveViewModel;
import interface_adapters.switchView.SwitchViewController;
import interface_adapters.switchView.SwitchViewViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SaveView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "save";
    private final SaveViewModel saveViewModel;
    private final SwitchViewViewModel switchViewViewModel;
    private final JButton backButton;
    private final JTextArea messageArea = new JTextArea(40, 2);
    private final SwitchViewController switchViewController;

    public SaveView(SaveViewModel saveViewModel,
                    SwitchViewViewModel switchViewViewModel,
                    SwitchViewController switchViewController) {
        this.saveViewModel = saveViewModel;
        this.switchViewController = switchViewController;
        this.switchViewViewModel = switchViewViewModel;
        switchViewViewModel.addPropertyChangeListener(this);

        SaveState currentState = saveViewModel.getState();
        if (currentState.getPaperAlreadySavedError() != null) {
            messageArea.append(currentState.getPaperAlreadySavedError());
        } else {
            String paperId = currentState.getPaperId();
            messageArea.append("Paper " + paperId + " successfully saved.");
        }
        backButton = new JButton(switchViewViewModel.TITLE_LABEL);
        backButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(backButton)) {
                            switchViewController.execute("recommend");
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
