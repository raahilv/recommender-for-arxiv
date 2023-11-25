package interface_adapters.vote;

import use_cases.vote.VoteInputBoundary;
import use_cases.vote.VoteInputData;

public class VoteController {
    final VoteInputBoundary voteInteractor;

    public VoteController(VoteInputBoundary voteInteractor) {
        this.voteInteractor = voteInteractor;
    }

    public void execute(String userName, String paperId, boolean isUpvote) {
        VoteInputData voteInputData = new VoteInputData(userName, paperId, isUpvote);
        voteInteractor.execute(voteInputData);
    }
}
