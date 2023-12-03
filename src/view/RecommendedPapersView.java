package view;

Upimport interface_adapters.localsave.LocalSaveController;
import interface_adapters.logged_in.LoggedInState;
import interface_adapters.logged_in.LoggedInViewModel;
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
    private final String viewName = "recommend";
    private final SaveViewModel saveViewModel;
    private final VoteViewModel voteViewModel;
    private final RecommendViewModel recommendViewModel;
    private final LoggedInViewModel loggedInViewModel;
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
                                 LoggedInViewModel loggedInViewModel,
                                 SaveController saveController,
                                 VoteController voteController,
                                 LocalSaveController localSaveController) {
        this.saveViewModel = saveViewModel;
        this.voteViewModel = voteViewModel;
        this.recommendViewModel = recommendViewModel;
        this.loggedInViewModel = loggedInViewModel;
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


        RecommendState recommendState = this.recommendViewModel.getState();
        LoggedInState loggedInState = this.loggedInViewModel.getState();
        List<List<String>> papersRecommended = recommendState.getRecommendedPapers();
        List<String> emptyPaperInfo = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            emptyPaperInfo.add("");
        }

        for (int i = 0; i <= 10; i++) {
            papersRecommended.add(emptyPaperInfo);
        }

        List<List<String>> papersDisplayed = recommendState.getRecommendedPapers().subList(0, 9);
        List<String> paperInfo1 = papersDisplayed.get(0);
        List<String> paperInfo2 = papersDisplayed.get(1);
        List<String> paperInfo3 = papersDisplayed.get(2);
        List<String> paperInfo4 = papersDisplayed.get(3);
        List<String> paperInfo5 = papersDisplayed.get(4);
        List<String> paperInfo6 = papersDisplayed.get(5);
        List<String> paperInfo7 = papersDisplayed.get(6);
        List<String> paperInfo8 = papersDisplayed.get(7);
        List<String> paperInfo9 = papersDisplayed.get(7);

//        load paper information to the InfoArea (text areas)

        paperInfoArea1.append("Paper " + String.valueOf(1) + "\n");
        paperInfoArea1.append("Id: " + paperInfo1.get(0) + "\n");
        paperInfoArea1.append("Title: " + paperInfo1.get(1) + "\n");
        paperInfoArea1.append("Publish Date: "+ paperInfo1.get(3) + "\n");
        paperInfoArea1.append("Authors: " + paperInfo1.get(9) + "\n");
        paperInfoArea1.append("Upvote & Downvote Count: " + paperInfo1.get(7) +
                paperInfo1.get(8) + "\n");
        paperInfoArea1.append(paperInfo1.get(4));
        paperInfoArea1.setEditable(false);

        paperInfoArea2.append("Paper " + String.valueOf(2) + "\n");
        paperInfoArea2.append("Id: " + paperInfo2.get(0) + "\n");
        paperInfoArea2.append("Title: " + paperInfo2.get(1) + "\n");
        paperInfoArea2.append("Publish Date: "+ paperInfo2.get(3) + "\n");
        paperInfoArea2.append("Authors: " + paperInfo2.get(9) + "\n");
        paperInfoArea2.append("Upvote & Downvote Count: " + paperInfo2.get(7) +
                paperInfo2.get(8) + "\n");
        paperInfoArea2.append(paperInfo2.get(4));
        paperInfoArea2.setEditable(false);

        paperInfoArea3.append("Paper " + String.valueOf(3) + "\n");
        paperInfoArea3.append("Id: " + paperInfo3.get(0) + "\n");
        paperInfoArea3.append("Title: " + paperInfo3.get(1) + "\n");
        paperInfoArea3.append("Publish Date: "+ paperInfo3.get(3) + "\n");
        paperInfoArea3.append("Authors: " + paperInfo3.get(9) + "\n");
        paperInfoArea3.append("Upvote & Downvote Count: " + paperInfo3.get(7) +
                paperInfo3.get(8) + "\n");
        paperInfoArea3.append(paperInfo3.get(4));
        paperInfoArea3.setEditable(false);

        paperInfoArea4.append("Paper " + String.valueOf(4) + "\n");
        paperInfoArea4.append("Id: " + paperInfo4.get(0) + "\n");
        paperInfoArea4.append("Title: " + paperInfo4.get(1) + "\n");
        paperInfoArea4.append("Publish Date: "+ paperInfo4.get(3) + "\n");
        paperInfoArea4.append("Authors: " + paperInfo4.get(9) + "\n");
        paperInfoArea4.append("Upvote & Downvote Count: " + paperInfo4.get(7) +
                paperInfo4.get(8) + "\n");
        paperInfoArea4.append(paperInfo4.get(4));
        paperInfoArea4.setEditable(false);

        paperInfoArea5.append("Paper " + String.valueOf(5) + "\n");
        paperInfoArea5.append("Id: " + paperInfo5.get(0) + "\n");
        paperInfoArea5.append("Title: " + paperInfo5.get(1) + "\n");
        paperInfoArea5.append("Publish Date: "+ paperInfo5.get(3) + "\n");
        paperInfoArea5.append("Authors: " + paperInfo5.get(9) + "\n");
        paperInfoArea5.append("Upvote & Downvote Count: " + paperInfo5.get(7) +
                paperInfo5.get(8) + "\n");
        paperInfoArea5.append(paperInfo5.get(4));
        paperInfoArea5.setEditable(false);

        paperInfoArea6.append("Paper " + String.valueOf(6) + "\n");
        paperInfoArea6.append("Id: " + paperInfo6.get(0) + "\n");
        paperInfoArea6.append("Title: " + paperInfo6.get(1) + "\n");
        paperInfoArea6.append("Publish Date: "+ paperInfo6.get(3) + "\n");
        paperInfoArea6.append("Authors: " + paperInfo6.get(9) + "\n");
        paperInfoArea6.append("Upvote & Downvote Count: " + paperInfo6.get(7) +
                paperInfo6.get(8) + "\n");
        paperInfoArea6.append(paperInfo6.get(4));
        paperInfoArea6.setEditable(false);

        paperInfoArea7.append("Paper " + String.valueOf(7) + "\n");
        paperInfoArea7.append("Id: " + paperInfo7.get(0) + "\n");
        paperInfoArea7.append("Title: " + paperInfo7.get(1) + "\n");
        paperInfoArea7.append("Publish Date: "+ paperInfo7.get(3) + "\n");
        paperInfoArea7.append("Authors: " + paperInfo7.get(9) + "\n");
        paperInfoArea7.append("Upvote & Downvote Count: " + paperInfo7.get(7) +
                paperInfo7.get(8) + "\n");
        paperInfoArea7.append(paperInfo7.get(4));
        paperInfoArea7.setEditable(false);

        paperInfoArea8.append("Paper " + String.valueOf(8) + "\n");
        paperInfoArea8.append("Id: " + paperInfo8.get(0) + "\n");
        paperInfoArea8.append("Title: " + paperInfo8.get(1) + "\n");
        paperInfoArea8.append("Publish Date: "+ paperInfo8.get(3) + "\n");
        paperInfoArea8.append("Authors: " + paperInfo8.get(9) + "\n");
        paperInfoArea8.append("Upvote & Downvote Count: " + paperInfo8.get(7) +
                paperInfo8.get(8) + "\n");
        paperInfoArea8.append(paperInfo8.get(4));
        paperInfoArea8.setEditable(false);

        paperInfoArea9.append("Paper " + String.valueOf(9) + "\n");
        paperInfoArea9.append("Id: " + paperInfo9.get(0) + "\n");
        paperInfoArea9.append("Title: " + paperInfo9.get(1) + "\n");
        paperInfoArea9.append("Publish Date: "+ paperInfo9.get(3) + "\n");
        paperInfoArea9.append("Authors: " + paperInfo9.get(9) + "\n");
        paperInfoArea9.append("Upvote & Downvote Count: " + paperInfo9.get(7) +
                paperInfo9.get(8) + "\n");
        paperInfoArea9.append(paperInfo9.get(4));
        paperInfoArea9.setEditable(false);

//        Add listeners to Upvote buttons.

        upVote1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote1)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        upVote2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote2)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo2.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        upVote3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote3)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo3.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        upVote4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote4)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo4.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        upVote5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote5)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo5.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        upVote6.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote6)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo6.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        upVote7.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote7)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo7.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        upVote8.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote8)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo8.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        upVote9.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(upVote9)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo9.get(0);
                            boolean isUpvote = true;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

//      Add listeners to Downvote buttons.

        downVote1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote1)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        downVote2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote2)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo2.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        downVote3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote3)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo3.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        downVote4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote4)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo4.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        downVote5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote5)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo5.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        downVote6.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote6)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo6.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        downVote7.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote7)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo7.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        downVote8.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote8)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo8.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

        downVote9.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(downVote9)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo9.get(0);
                            boolean isUpvote = false;
                            voteController.execute(userName, paperId, isUpvote);
                        }

                    }
                }
        );

//        Add listeners for Save buttons.

        save1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save1)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

        save2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save2)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

        save3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save3)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

        save4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save4)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

        save5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save5)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

        save6.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save6)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

        save7.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save7)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

        save8.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save8)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

        save9.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(save9)) {
                            String userName = loggedInState.getUsername();
                            String paperId = paperInfo1.get(0);
                            saveController.execute(userName, paperId);
                        }

                    }
                }
        );

//        Add listeners to download buttons.

        download1.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download1)) {
                            String paperName = paperInfo1.get(1);
                            String paperUrl = paperInfo1.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        download2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download2)) {
                            String paperName = paperInfo2.get(1);
                            String paperUrl = paperInfo2.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        download3.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download3)) {
                            String paperName = paperInfo3.get(1);
                            String paperUrl = paperInfo3.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        download4.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download4)) {
                            String paperName = paperInfo4.get(1);
                            String paperUrl = paperInfo4.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        download5.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download5)) {
                            String paperName = paperInfo5.get(1);
                            String paperUrl = paperInfo5.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        download6.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download6)) {
                            String paperName = paperInfo6.get(1);
                            String paperUrl = paperInfo6.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        download7.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download7)) {
                            String paperName = paperInfo7.get(1);
                            String paperUrl = paperInfo7.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        download8.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download8)) {
                            String paperName = paperInfo8.get(1);
                            String paperUrl = paperInfo8.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        download9.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(download9)) {
                            String paperName = paperInfo9.get(1);
                            String paperUrl = paperInfo9.get(6);
                            localSaveController.execute(paperUrl, paperName);
                        }
                    }
                }
        );

        this.setLayout(new GridLayout(3, 3, 10, 10));

        if (! paperInfo1.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea1, upVote1, downVote1, save1, download1);
        }
        if (! paperInfo2.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea2, upVote2, downVote2, save2, download2);
        }
        if (! paperInfo3.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea3, upVote3, downVote3, save3, download3);
        }
        if (! paperInfo4.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea4, upVote4, downVote4, save4, download4);
        }
        if (! paperInfo5.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea5, upVote5, downVote5, save5, download5);
        }
        if (! paperInfo6.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea6, upVote6, downVote6, save6, download6);
        }
        if (! paperInfo7.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea7, upVote7, downVote7, save7, download7);
        }
        if (! paperInfo8.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea8, upVote8, downVote8, save8, download8);
        }
        if (! paperInfo9.equals(emptyPaperInfo)) {
            addPaperInfoArea(paperInfoArea9, upVote9, downVote9, save9, download9);
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

    }
}