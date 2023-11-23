package view;

import interface_adapters.showAuthor.showAuthorController;
import interface_adapters.showAuthor.showAuthorViewModel;

import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ShowAuthorView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewname = "showing author";
    public final showAuthorViewModel showAuthorViewModel;
    public final showAuthorController showAuthorController;

    public ShowAuthorView(showAuthorController controller, showAuthorViewModel showAuthorViewModel) {
        this.showAuthorController = controller;
        this.showAuthorViewModel = showAuthorViewModel;

        showAuthorViewModel.addPropertyChangeListener(this);
        JLabel title =new JLabel(showAuthorViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel AuthorName = new JLabel(showAuthorViewModel.getState().getAuthorName());
        JLabel AuthorDetails = new JLabel("from : " + showAuthorViewModel.getState().getAuthorAffiliation());
        JLabel authorvotes = new JLabel("total upvotes: " + showAuthorViewModel.getState().getTotalUpvotes() + "\n total downvotes: " + showAuthorViewModel.getState().getTotalDownvotes() + "\n Average upvotes: " + showAuthorViewModel.getState().getAverageUpvotes());
        // Not implemented yet JLabel authorPopularity = new JLabel("popularity index: ")

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
