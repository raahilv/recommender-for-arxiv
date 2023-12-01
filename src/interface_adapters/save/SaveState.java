package interface_adapters.save;

public class SaveState {
    private String paperId = "";
    private String paperAlreadySavedError = null;

    public SaveState(SaveState copy) {
        this.paperId = copy.paperId;
        this.paperAlreadySavedError = copy.paperAlreadySavedError;
    }

    public SaveState() {
    }

    public String getPaperId() {
        return paperId;
    }

    public String getPaperAlreadySavedError() {
        return paperAlreadySavedError;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public void setPaperAlreadySavedError(String paperAlreadySavedError) {
        this.paperAlreadySavedError = paperAlreadySavedError;
    }
}
