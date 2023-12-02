package interface_adapters.vote;

public class VoteState {
    private String paperId = "";
    private boolean isUpvote = false;
    private boolean removeVote = false;
    private String paperAlreadyUpvotedError = null;
    private String paperAlreadyDownvotedError = null;

    /**
     * Constructor for Vote state for copying from another state.
     * @param copy the another state to be copied from
     */
    public VoteState(VoteState copy) {
        this.paperId = copy.paperId;
        this.isUpvote = copy.isUpvote;
        this.removeVote = copy.removeVote;
        this.paperAlreadyUpvotedError = copy.paperAlreadyUpvotedError;
        this.paperAlreadyDownvotedError = copy.paperAlreadyDownvotedError;
    }

    /**
     * Constructor for Vote state that set paperId as empty String, isUpvote and removeVote as false, and errors as null.
     */
    public VoteState() {

    }

    /**
     * Getter for paperAlreadyUpvotedError.
     * @return the error message indicating the paper has already been upvoted
     */
    public String getPaperAlreadyUpvotedError() {
        return paperAlreadyUpvotedError;
    }

    /**
     * Setter for paperAlreadyUpvotedError
     * @param paperAlreadyUpvotedError the error message to be stored
     */
    public void setPaperAlreadyUpvotedError(String paperAlreadyUpvotedError) {
        this.paperAlreadyUpvotedError = paperAlreadyUpvotedError;
    }

    /**
     * Getter for paperAlreadyDownvotedError.
     * @return the error message indicating the paper has already been downvoted
     */
    public String getPaperAlreadyDownvotedError() {
        return paperAlreadyDownvotedError;
    }

    /**
     * Setter for paperAlreadyDownvotedError.
     * @param paperAlreadyDownvotedError the error message to be stored
     */
    public void setPaperAlreadyDownvotedError(String paperAlreadyDownvotedError) {
        this.paperAlreadyDownvotedError = paperAlreadyDownvotedError;
    }

    /**
     * Setter for paperId.
     * @param paperId paper id to be stored
     */
    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    /**
     * Setter for isUpvote.
     * @param upvote boolean value to be stored, true when the user upvotes, false when the user downvotes
     */
    public void setUpvote(boolean upvote) {
        isUpvote = upvote;
    }

    /**
     * Setter for isRemoveVote (not implemented yet).
     * @param removeVote boolean value to be stores, true when the user removes a vote, false otherwise
     */
    public void setRemoveVote(boolean removeVote) {
        this.removeVote = removeVote;
    }

    /**
     * Getter for paperId.
     * @return the paper id stored in the state (of the paper voted)
     */
    public String getPaperId() {
        return paperId;
    }

    /**
     * Getter for isUpvote.
     * @return true when the user upvotes the paper, false when the user downvotes the paper
     */
    public boolean isUpvote() {
        return isUpvote;
    }

    /**
     * Getter for isRemoveVote (not implemented yet).
     * @return truw when the user removes a vote, false otherwise
     */
    public boolean isRemoveVote() {
        return removeVote;
    }
}

