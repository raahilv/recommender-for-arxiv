package use_cases.vote;

import entities.ResearchPaper;
import entities.User;

import java.awt.print.Paper;

public class VoteInteractor implements VoteInputBoundary{
    final VoteDataAccessInterface voteDataAccessObject;
    final VoteOutputBoundary votePresenter;

    public VoteInteractor(VoteDataAccessInterface voteDataAccessObject, VoteOutputBoundary votePresenter) {
        this.voteDataAccessObject = voteDataAccessObject;
        this.votePresenter = votePresenter;
    }

    @Override
    public void execute(VoteInputData voteInputData) {
        String userName = voteInputData.getUserName();
        String paperId = voteInputData.getPaperId();
        boolean isUpvote = voteInputData.isUpvote();
        User user = voteDataAccessObject.getUser(userName);
        ResearchPaper paper = voteDataAccessObject.getPaper(paperId);
        if (isUpvote) {
//            User tries to upvote the paper.
            if (user.getPapersUpvoted().containsKey(paperId)) {
//                The paper was already upvoted by the user before.
//                Trying to upvote again will cancel the previous upvote.
                user.getPapersUpvoted().remove(paperId);
                VoteOutputData voteOutputData = new VoteOutputData(paperId, true, true);
                votePresenter.prepareSuccessView(voteOutputData);
            } else if (user.getPapersDownvoted().containsKey(paperId)) {
//                The paper was downvoted before, and the user try to upvote it.
//                In this case, previous downvote will be cancelled and the paper is upvoted.
                user.getPapersDownvoted().remove(paperId);
                user.getPapersUpvoted().put(paperId, paper);
                VoteOutputData voteOutputData = new VoteOutputData(paperId, true, false);
                votePresenter.prepareSuccessView(voteOutputData);
            } else {
//                The paper was not upvoted or downvoted before by the user.
                user.getPapersUpvoted().put(paperId, paper);
                VoteOutputData voteOutputData = new VoteOutputData(paperId, true, false);
                votePresenter.prepareSuccessView(voteOutputData);
            }
        } else {
//            User tries to downvote the paper.
            if (user.getPapersDownvoted().containsKey(paperId)) {
//                The paper was already downvoted by the user before.
//                Trying to downvote again will cancel the previous downvote.
                user.getPapersDownvoted().remove(paperId);
                VoteOutputData voteOutputData = new VoteOutputData(paperId, false, true);
                votePresenter.prepareSuccessView(voteOutputData);
            } else if (user.getPapersUpvoted().containsKey(paperId)) {
//                The paper was upvoted before, and the user try to downvote it.
//                In this case, previous upvote will be cancelled and the paper is downvoted.
                user.getPapersUpvoted().remove(paperId);
                user.getPapersDownvoted().put(paperId, paper);
                VoteOutputData voteOutputData = new VoteOutputData(paperId, false, false);
                votePresenter.prepareSuccessView(voteOutputData);
            }
            else {
//                The paper was not upvoted or downvoted before by the user.
                user.getPapersDownvoted().put(paperId, paper);
                VoteOutputData voteOutputData = new VoteOutputData(paperId, false, false);
                votePresenter.prepareSuccessView(voteOutputData);
            }
        }
    }
}
