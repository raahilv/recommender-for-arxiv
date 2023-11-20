package interface_adapters.vote;

public class VoteState {
    private String paperId = "";
    private boolean isUpvote = false;
    private boolean removeVote = false;

    public VoteState(VoteState copy) {
        this.paperId = copy.paperId;
        this.isUpvote = copy.isUpvote;
        this.removeVote = copy.removeVote;
    }

    public VoteState() {

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
