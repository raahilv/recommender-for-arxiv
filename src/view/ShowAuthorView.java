package view;

import interface_adapters.showAuthor.showAuthorController;
import interface_adapters.showAuthor.showAuthorViewModel;
import interface_adapters.switchView.SwitchViewController;
import interface_adapters.switchView.SwitchViewViewModel;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShowAuthorView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewname = "showing author";
    private final showAuthorViewModel showAuthorViewModel;
    private final showAuthorController showAuthorController;
    private final SwitchViewViewModel switchViewViewModel;
    private final SwitchViewController switchViewController;
    private final JButton backButton;

    public ShowAuthorView(showAuthorController controller, showAuthorViewModel showAuthorViewModel, SwitchViewViewModel switchViewViewModel, SwitchViewController switchViewController) {
        this.showAuthorController = controller;
        this.showAuthorViewModel = showAuthorViewModel;
        this.switchViewViewModel = switchViewViewModel;
        this.switchViewController = switchViewController;


        switchViewViewModel.addPropertyChangeListener(this);
        JLabel title =new JLabel(showAuthorViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel AuthorName = new JLabel(showAuthorViewModel.getState().getAuthorName());
        JLabel AuthorDetails = new JLabel("from : " + showAuthorViewModel.getState().getAuthorAffiliation());
        JLabel authorvotes = new JLabel("total upvotes: " + showAuthorViewModel.getState().getTotalUpvotes() + "\n total downvotes: " + showAuthorViewModel.getState().getTotalDownvotes() + "\n Average upvotes: " + showAuthorViewModel.getState().getAverageUpvotes());
        // Not implemented yet JLabel authorPopularity = new JLabel("popularity index: ")
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(AuthorName);
        this.add(AuthorDetails);
        this.add(authorvotes);
    }

    public void actionPerformed(ActionEvent evt) {JOptionPane.showConfirmDialog(this, "Not implemented yet.");}
    public void propertyChange(PropertyChangeEvent evt) {
        // there are no properties to change yet
    }

}
