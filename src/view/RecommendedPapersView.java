package view;

import interface_adapters.recommend.RecommendState;
import interface_adapters.recommend.RecommendViewModel;
import interface_adapters.save.SaveController;
import interface_adapters.save.SaveViewModel;
import interface_adapters.vote.VoteController;
import interface_adapters.vote.VoteViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendedPapersView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "recommend";
    private final SaveViewModel saveViewModel;
    private final VoteViewModel voteViewModel;
    private final RecommendViewModel recommendViewModel;
    private final SaveController saveController;
    private final VoteController voteController;
    private final JTextArea paperInfoArea1 = new JTextArea(10, 40);
    private final JTextArea paperInfoArea2 = new JTextArea(10, 40);
    private final JTextArea paperInfoArea3 = new JTextArea(10, 40);
    private final JTextArea paperInfoArea4 = new JTextArea(10, 40);
    private final JTextArea paperInfoArea5 = new JTextArea(10, 40);
    private final JTextArea paperInfoArea6 = new JTextArea(10, 40);
    private final JTextArea paperInfoArea7 = new JTextArea(10, 40);
    private final JTextArea paperInfoArea8 = new JTextArea(10, 40);
    private final JTextArea paperInfoArea9 = new JTextArea(10, 40);
    private final JButton upVote1;
    private final JButton upVote2;
    private final JButton upVote3;
    private final JButton upVote4;
    private final JButton upVote5;
    private final JButton upVote6;
    private final JButton upVote7;
    private final JButton upVote8;
    private final JButton upVote9;
    private final JButton downVote1;
    private final JButton downVote2;
    private final JButton downVote3;
    private final JButton downVote4;
    private final JButton downVote5;
    private final JButton downVote6;
    private final JButton downVote7;
    private final JButton downVote8;
    private final JButton downVote9;
    private final JButton save1;
    private final JButton save2;
    private final JButton save3;
    private final JButton save4;
    private final JButton save5;
    private final JButton save6;
    private final JButton save7;
    private final JButton save8;
    private final JButton save9;

    public RecommendedPapersView(SaveViewModel saveViewModel,
                                 VoteViewModel voteViewModel,
                                 RecommendViewModel recommendViewModel,
                                 SaveController saveController,
                                 VoteController voteController) {
        this.saveViewModel = saveViewModel;
        this.voteViewModel = voteViewModel;
        this.recommendViewModel = recommendViewModel;
        this.saveController = saveController;
        this.voteController = voteController;

        upVote1 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 1");
        upVote2 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 2");
        upVote3 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 3");
        upVote4 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 4");
        upVote5 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 5");
        upVote6 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 6");
        upVote7 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 7");
        upVote8 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 8");
        upVote9 = new JButton(voteViewModel.UPVOTE_BUTTON_LABEL + " paper 9");

        downVote1 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 1");
        downVote2 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 2");
        downVote3 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 3");
        downVote4 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 4");
        downVote5 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 5");
        downVote6 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 6");
        downVote7 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 7");
        downVote8 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 8");
        downVote9 = new JButton(voteViewModel.DOWNVOTE_BUTTON_LABEL + " paper 9");

        save1 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 1");
        save2 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 2");
        save3 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 3");
        save4 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 4");
        save5 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 5");
        save6 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 6");
        save7 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 7");
        save8 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 8");
        save9 = new JButton(saveViewModel.SAVE_BUTTON_LABEL + " paper 9");

        saveViewModel.addPropertyChangeListener(this);
        voteViewModel.addPropertyChangeListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}