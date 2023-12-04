package view;

import interface_adapters.RecommendHome.RecommendHomeState;
import interface_adapters.RecommendHome.RecommendHomeViewModel;
import interface_adapters.localsave.LocalSaveController;
import interface_adapters.recommend.RecommendState;
import interface_adapters.recommend.RecommendViewModel;
import interface_adapters.save.SaveController;
import interface_adapters.save.SaveViewModel;
import interface_adapters.vote.VoteController;
import interface_adapters.vote.VoteViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendedPapersView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "recommend";
    private final SaveViewModel saveViewModel;
    private final VoteViewModel voteViewModel;
    private final RecommendViewModel recommendViewModel;
    private final RecommendHomeViewModel recommendHomeViewModel;
    private final SaveController saveController;
    private final VoteController voteController;
    private final LocalSaveController localSaveController;
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
    private final JButton download1;
    private final JButton download2;
    private final JButton download3;
    private final JButton download4;
    private final JButton download5;
    private final JButton download6;
    private final JButton download7;
    private final JButton download8;
    private final JButton download9;


    public RecommendedPapersView(SaveViewModel saveViewModel,
                                 VoteViewModel voteViewModel,
                                 RecommendViewModel recommendViewModel,
                                 RecommendHomeViewModel recommendHomeViewModel,
                                 SaveController saveController,
                                 VoteController voteController,
                                 LocalSaveController localSaveController) {
        this.saveViewModel = saveViewModel;
        this.voteViewModel = voteViewModel;
        this.recommendViewModel = recommendViewModel;
        this.recommendHomeViewModel = recommendHomeViewModel;
        this.saveController = saveController;
        this.voteController = voteController;
        this.localSaveController = localSaveController;

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

        download1 = new JButton("Download" + " paper 1");
        download2 = new JButton("Download" + " paper 2");
        download3 = new JButton("Download" + " paper 3");
        download4 = new JButton("Download" + " paper 4");
        download5 = new JButton("Download" + " paper 5");
        download6 = new JButton("Download" + " paper 6");
        download7 = new JButton("Download" + " paper 7");
        download8 = new JButton("Download" + " paper 8");
        download9 = new JButton("Download" + " paper 9");

        saveViewModel.addPropertyChangeListener(this);
        voteViewModel.addPropertyChangeListener(this);
        recommendViewModel.addPropertyChangeListener(this);
    }

    private void setViewLayout() {
        ArrayList<JTextArea> infoAreas = new ArrayList<JTextArea>(
                Arrays.asList(paperInfoArea1, paperInfoArea2, paperInfoArea3, paperInfoArea4,
                        paperInfoArea5, paperInfoArea6, paperInfoArea7, paperInfoArea8, paperInfoArea9));

        ArrayList<JButton> upVoteButtons = new ArrayList<JButton>(
                Arrays.asList(upVote1, upVote2, upVote3, upVote4, upVote5, upVote6,
                        upVote7, upVote8, upVote9));

        ArrayList<JButton> downVoteButtons = new ArrayList<JButton>(
                Arrays.asList(downVote1, downVote2, downVote3, downVote4, downVote5, downVote6,
                        downVote7, downVote8, downVote9));

        ArrayList<JButton> saveButtons = new ArrayList<JButton>(
                Arrays.asList(save1, save2, save3, save4, save5, save6, save7, save8, save9));

        ArrayList<JButton> downloadButtons = new ArrayList<JButton>(
                Arrays.asList(download1, download2, download3, download4, download5, download6,
                        download7, download8, download9));

        RecommendState recommendState = this.recommendViewModel.getState();
        RecommendHomeState recommendHomeState = this.recommendHomeViewModel.getState();
        List<List<String>> papersRecommended = recommendState.getRecommendedPapers();
        List<String> emptyPaperInfo = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            emptyPaperInfo.add("");
        }

        for (int i = 0; i <= 10; i++) {
            papersRecommended.add(emptyPaperInfo);
        }

        List<List<String>> papersDisplayed = recommendState.getRecommendedPapers().subList(0, 9);

//        load paper information to the InfoArea (text areas)

        for (int i = 0; i < 9; i++) {
            JTextArea paperInfoArea = infoAreas.get(i);
            List<String> paperInfo = papersDisplayed.get(i);
            paperInfoArea.append("Paper " + String.valueOf(i + 1) + "\n");
            paperInfoArea.append("Id: " + paperInfo.get(0) + "\n");
            paperInfoArea.append("Title: " + paperInfo.get(1) + "\n");
            paperInfoArea.append("Publish Date: "+ paperInfo.get(3) + "\n");
            paperInfoArea.append("Authors: " + paperInfo.get(9) + "\n");
            paperInfoArea.append("Upvote & Downvote Count: " + paperInfo.get(7) +
                    paperInfo.get(8) + "\n");
            paperInfoArea.append(paperInfo.get(4));
            paperInfoArea.setEditable(false);
        }

//        Add listeners to Upvote buttons.

        for (int i = 0; i < 9; i++) {
            JButton upvoteButton = upVoteButtons.get(i);
            List<String> paperInfo = papersDisplayed.get(i);
            upvoteButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(upvoteButton)) {
                                String userName = recommendHomeState.getUsername();
                                String paperId = paperInfo.get(0);
                                boolean isUpvote = true;
                                voteController.execute(userName, paperId, isUpvote);
                            }

                        }
                    }
            );
        }

//      Add listeners to Downvote buttons.

        for (int i = 0; i < 9; i++) {
            JButton downvoteButton = downVoteButtons.get(i);
            List<String> paperInfo = papersDisplayed.get(i);
            downvoteButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(downvoteButton)) {
                                String userName = recommendHomeState.getUsername();
                                String paperId = paperInfo.get(0);
                                boolean isUpvote = false;
                                voteController.execute(userName, paperId, isUpvote);
                            }

                        }
                    }
            );
        }

//        Add listeners for Save buttons.

        for (int i = 0; i < 9; i++) {
            JButton saveButton = saveButtons.get(i);
            List<String> paperInfo = papersDisplayed.get(i);
            saveButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(saveButton)) {
                                String userName = recommendHomeState.getUsername();
                                String paperId = paperInfo.get(0);
                                saveController.execute(userName, paperId);
                            }

                        }
                    }
            );

        }

//        Add listeners to download buttons.

        for (int i = 0; i < 9; i++) {
            JButton downloadButton = downloadButtons.get(i);
            List<String> paperInfo = papersDisplayed.get(i);
            downloadButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(downloadButton)) {
                                String paperName = paperInfo.get(1);
                                String paperUrl = paperInfo.get(6);
                                localSaveController.execute(paperUrl, paperName);
                            }
                        }
                    }
            );
        }
        this.setLayout(new GridLayout(3, 3, 10, 10));

        for (int i = 0; i < 9; i++) {
            addPaperInfoArea(infoAreas.get(i),
                    upVoteButtons.get(i),
                    downVoteButtons.get(i),
                    saveButtons.get(i),
                    downloadButtons.get(i));
        }
    }

    private void addPaperInfoArea(JTextArea paperInfoArea, JButton upVote, JButton downVote, JButton save, JButton doanload) {
        JPanel paperPanel = new JPanel();
        paperPanel.setLayout(new BorderLayout());

        // Add text area to the center of the panel
        JScrollPane scrollPane = new JScrollPane(paperInfoArea);
        paperPanel.add(scrollPane, BorderLayout.CENTER);

        // Create a sub-panel for buttons (vote, upvote, downvote)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 5, 5)); // Adjust gaps as needed

        // Add buttons to the sub-panel
        buttonPanel.add(upVote);
        buttonPanel.add(downVote);
        buttonPanel.add(save);
        buttonPanel.add(doanload);

        // Add the sub-panel of buttons to the south of the paper panel
        paperPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the paper panel to the main panel
        this.add(paperPanel);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.recommendViewModel.setState((RecommendState) evt.getNewValue());
        this.setViewLayout();
    }
}