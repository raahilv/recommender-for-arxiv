package use_cases.vote;

public interface VoteOutputBoundary {
    void prepareSuccessView(VoteOutputData voteOutputData);
    void prepareFailView(String error);
}
