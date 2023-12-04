package use_cases.vote;

public interface VoteInputBoundary {

    /**
     * Execute Vote use case.
     * @param voteInputData input data for Vote use case
     */
    void execute(VoteInputData voteInputData);
}