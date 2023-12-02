package use_cases.vote;

public class VoteInputData {
    final private String userName;
    final private String paperId;
    final private boolean isUpvote;

    /**
     * Constructor for input data for Vote use case.
     * @param userName username of the user who votes
     * @param paperId id of the paper voted
     * @param isUpvote is true when the user upvotes the paper, false when the user downvotes the paper
     */
    public VoteInputData(String userName, String paperId, boolean isUpvote) {
        this.userName = userName;
        this.paperId = paperId;
        this.isUpvote = isUpvote;
    }

    /**
     * Get userName stored.
     * @return username of the user who votes
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get paperId stored.
     * @return id of the paper voted
     */
    public String getPaperId() {
        return paperId;
    }

    /**
     * Get isUpvote stored.
     * @return true when the user upvotes the paper, false when the user downvotes the paper
     */
    public boolean isUpvote() {
        return isUpvote;
    }
}
