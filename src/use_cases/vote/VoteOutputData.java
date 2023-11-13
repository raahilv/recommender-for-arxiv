package use_cases.vote;

public class VoteOutputData {
    private final String paperId;
    private final boolean isUpvote;
    private final boolean removeVote;

    public VoteOutputData(String paperId, boolean isUpvote, boolean removeVote) {
        this.paperId = paperId;
        this.isUpvote = isUpvote;
        this.removeVote = removeVote;
    }
}
