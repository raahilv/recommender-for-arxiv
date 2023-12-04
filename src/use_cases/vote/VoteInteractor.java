package use_cases.vote;

import entities.ResearchPaper;
import entities.User;

import java.awt.print.Paper;

public class VoteInteractor implements VoteInputBoundary{
    final VoteDataAccessInterface voteDataAccessObject;
    final VoteOutputBoundary votePresenter;

    /**
     * Constructor for interactor for vote use case.
     * @param voteDataAccessObject data access object for Vote use case
     * @param votePresenter presenter for Vote use case
     */
    public VoteInteractor(VoteDataAccessInterface voteDataAccessObject, VoteOutputBoundary votePresenter) {
        this.voteDataAccessObject = voteDataAccessObject;
        this.votePresenter = votePresenter;
    }

    /**
     * Execute the Vote use case, update the upvoted or downvoted papers for user, update the upvote or downvote count of paper.
     * Call the presenter to prepare successful view or fail view.
     * @param voteInputData input data for Vote use case
     */
    @Override
    public void execute(VoteInputData voteInputData) {
        String userName = voteInputData.getUserName();
        String paperId = voteInputData.getPaperId();
        boolean isUpvote = voteInputData.isUpvote();
        User user = voteDataAccessObject.getUser(userName);
        ResearchPaper paper = voteDataAccessObject.getPaper(paperId);
        if (isUpvote) {
//            User tries to upvote the paper.
            if (user.getUpvotedPapers().containsKey(paperId)) {
//                The paper was already upvoted by the user before.
//                Trying to upvote again will raise an error.
                String message = "Error: paper already upvoted.";
                votePresenter.prepareFailView(message);
            } else if (user.getDownvotedPapers().containsKey(paperId)) {
//                The paper was downvoted before, and the user try to upvote it.
//                In this case, previous downvote will be cancelled and the paper is upvoted.
                user.getDownvotedPapers().remove(paperId);
                user.getUpvotedPapers().put(paperId, paper);
                paper.setDownvoteCount((int) (paper.getDownvoteCount() - 1));
                paper.setUpvoteCount((int) (paper.getUpvoteCount() + 1));
                VoteOutputData voteOutputData = new VoteOutputData(paperId, true, false);
                votePresenter.prepareSuccessView(voteOutputData);
            } else {
//                The paper was not upvoted or downvoted before by the user.
                user.getUpvotedPapers().put(paperId, paper);
                paper.setUpvoteCount((int) (paper.getUpvoteCount() + 1));
                VoteOutputData voteOutputData = new VoteOutputData(paperId, true, false);
                votePresenter.prepareSuccessView(voteOutputData);
            }

        } else {
//            User tries to downvote the paper.
            if (user.getDownvotedPapers().containsKey(paperId)) {
//                The paper was already downvoted by the user before.
//                Trying to downvote again will raise an error.
                String message = "Error: Paper already downvoted.";
                votePresenter.prepareFailView(message);
            } else if (user.getUpvotedPapers().containsKey(paperId)) {
//                The paper was upvoted before, and the user try to downvote it.
//                In this case, previous upvote will be cancelled and the paper is downvoted.
                user.getUpvotedPapers().remove(paperId);
                user.getDownvotedPapers().put(paperId, paper);
                paper.setUpvoteCount((int) (paper.getUpvoteCount() - 1));
                paper.setDownvoteCount((int) (paper.getDownvoteCount() + 1));
                VoteOutputData voteOutputData = new VoteOutputData(paperId, false, false);
                votePresenter.prepareSuccessView(voteOutputData);
            }
            else {
//                The paper was not upvoted or downvoted before by the user.
                user.getDownvotedPapers().put(paperId, paper);
                paper.setDownvoteCount((int) (paper.getDownvoteCount() + 1));
                VoteOutputData voteOutputData = new VoteOutputData(paperId, false, false);
                votePresenter.prepareSuccessView(voteOutputData);
            }
        }
    }
}
