package interface_adapters.vote;

public class VoteState {
    private String paperId = "";
    private boolean isUpvote = false;
    private boolean removeVote = false;
    private String paperAlreadyUpvotedError = null;
    private String paperAlreadyDownvotedError = null;

    public VoteState(VoteState copy) {
        this.paperId = copy.paperId;
        this.isUpvote = copy.isUpvote;
        this.removeVote = copy.removeVote;
        this.paperAlreadyUpvotedError = copy.paperAlreadyUpvotedError;
        this.paperAlreadyDownvotedError = copy.paperAlreadyDownvotedError;
    }

    public VoteState() {

    }

    public String getPaperAlreadyUpvotedError() {
        return paperAlreadyUpvotedError;
    }

    public void setPaperAlreadyUpvotedError(String paperAlreadyUpvotedError) {
        this.paperAlreadyUpvotedError = paperAlreadyUpvotedError;
    }

    public String getPaperAlreadyDownvotedError() {
        return paperAlreadyDownvotedError;
    }

    public void setPaperAlreadyDownvotedError(String paperAlreadyDownvotedError) {
        this.paperAlreadyDownvotedError = paperAlreadyDownvotedError;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public void setUpvote(boolean upvote) {
        isUpvote = upvote;
    }

    public void setRemoveVote(boolean removeVote) {
        this.removeVote = removeVote;
    }

    public String getPaperId() {
        return paperId;
    }

    public boolean isUpvote() {
        return isUpvote;
    }

    public boolean isRemoveVote() {
        return removeVote;
    }
}

