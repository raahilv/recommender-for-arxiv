package interface_adapters.save;

public class SaveState {
    private String paperId = "";
    private String paperAlreadySavedError = null;

    /**
     * Constructor for Save state for copying from another Save state.
     * @param copy the another state copied
     */
    public SaveState(SaveState copy) {
        this.paperId = copy.paperId;
        this.paperAlreadySavedError = copy.paperAlreadySavedError;
    }

    /**
     * Constructor for Save state that set paperId as empty String and the error as null.
     */
    public SaveState() {
    }

    /**
     * Getter for paperId.
     * @return the paperId that saved in the state (of the paper saved)
     */
    public String getPaperId() {
        return paperId;
    }

    /**
     * Getter for paperAlreadySavedError.
     * @return the error message indicating that paper has already been saved.
     */
    public String getPaperAlreadySavedError() {
        return paperAlreadySavedError;
    }

    /**
     * Setter for paperId.
     * @param paperId the paper id to store in the state
     */
    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    /**
     * Setter for paperAlreadySavedError。
     * @param paperAlreadySavedError the error message to store in the state
     */
    public void setPaperAlreadySavedError(String paperAlreadySavedError) {
        this.paperAlreadySavedError = paperAlreadySavedError;
    }
}
