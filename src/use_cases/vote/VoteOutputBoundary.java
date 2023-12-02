package use_cases.vote;

public interface VoteOutputBoundary {

    /**
     * Prepare the view when the paper is successfully voted.
     * @param voteOutputData the output data for the Vote use case
     */
    void prepareSuccessView(VoteOutputData voteOutputData);

    /**
     * Prepare the view when the paper is not successfully saved.
     * @param error the error message
     */
    void prepareFailView(String error);
}
