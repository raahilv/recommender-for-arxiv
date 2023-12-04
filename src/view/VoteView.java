package view;

import interface_adapters.recommend.RecommendState;
import interface_adapters.switchView.SwitchViewController;
import interface_adapters.switchView.SwitchViewViewModel;
import interface_adapters.vote.VoteState;
import interface_adapters.vote.VoteViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class VoteView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Vote";
    private final VoteViewModel voteViewModel;
    private final SwitchViewViewModel switchViewViewModel;
    private final JButton backButton;
    private final JTextArea messageArea = new JTextArea(40, 2);
    private final SwitchViewController switchViewController;

    public VoteView(VoteViewModel voteViewModel,
                    SwitchViewViewModel switchViewViewModel,
                    SwitchViewController switchViewController) {
        this.voteViewModel = voteViewModel;
        this.switchViewViewModel = switchViewViewModel;
        this.switchViewController = switchViewController;
        switchViewViewModel.addPropertyChangeListener(this);
        voteViewModel.addPropertyChangeListener(this);
        backButton = new JButton(switchViewViewModel.TITLE_LABEL);
    }

    private void setViewLayout() {
        VoteState currentState = voteViewModel.getState();
        if (currentState.getPaperAlreadyUpvotedError() != null) {
            messageArea.append(currentState.getPaperAlreadyUpvotedError());
        } else if (currentState.getPaperAlreadyDownvotedError() != null) {
            messageArea.append(currentState.getPaperAlreadyDownvotedError());
        } else {
            String paperId = currentState.getPaperId();
            if (currentState.isUpvote()) {
                messageArea.append("Paper " + paperId + " successfully upvoted.");
            } else {
                messageArea.append("Paper " + paperId + " successfully downvoted.");
            }
        }

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
        this.voteViewModel.setState((VoteState) evt.getNewValue());
        this.setViewLayout();
    }
}
