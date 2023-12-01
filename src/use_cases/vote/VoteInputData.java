package use_cases.vote;

public class VoteInputData {
    final private String userName;
    final private String paperId;
    final private boolean isUpvote;

    public VoteInputData(String userName, String paperId, boolean isUpvote) {
        this.userName = userName;
        this.paperId = paperId;
        this.isUpvote = isUpvote;
    }

    public String getUserName() {
        return userName;
    }

    public String getPaperId() {
        return paperId;
    }

    public boolean isUpvote() {
        return isUpvote;
    }
}
