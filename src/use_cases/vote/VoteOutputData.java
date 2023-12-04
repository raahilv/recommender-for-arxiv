package use_cases.vote;

public class VoteOutputData {
    private final String paperId;
    private final boolean isUpvote;
    private final boolean removeVote;

    /**
     * Constructor for output data for Vote use case.
     * @param paperId id of the paper voted
     * @param isUpvote true if the user upvotes the paper, false otherwise
     * @param removeVote true if the user removes the vote, false otherwise
     */
    public VoteOutputData(String paperId, boolean isUpvote, boolean removeVote) {
        this.paperId = paperId;
        this.isUpvote = isUpvote;
        this.removeVote = removeVote;
    }

    /**
     * Get paperId stored.
     * @return the id of the paper voted
     */
    public String getPaperId() {
        return paperId;
    }

    /**
     * Get isUpvote stored.
     * @return whether the user upvotes the paper
     */
    public boolean isUpvote() {
        return isUpvote;
    }

    /**
     * Get removeVote
     * @return whether the user removes the vote
     */
    public boolean isRemoveVote() {
        return removeVote;
    }
}
