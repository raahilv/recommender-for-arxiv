package interface_adapters.vote;

import use_cases.vote.VoteInputBoundary;
import use_cases.vote.VoteInputData;

public class VoteController {
    final VoteInputBoundary voteInteractor;

    /**
     * Constructor for controller for Vote use case.
     * @param voteInteractor the interactor used for executing the use case
     */
    public VoteController(VoteInputBoundary voteInteractor) {
        this.voteInteractor = voteInteractor;
    }

    /**
     * Execute the Vote use case, with inputs userName, paperId, and isUpvote.
     * @param userName username of the user who wants to save the paper
     * @param paperId paper id for the paper that is wanted to be saved
     * @param isUpvote boolean value that is true if the user upvotes a paper, is false other if the user downvotes a paper.
     */
    public void execute(String userName, String paperId, boolean isUpvote) {
        VoteInputData voteInputData = new VoteInputData(userName, paperId, isUpvote);
        voteInteractor.execute(voteInputData);
    }
}
