package use_cases.save;

public class SaveOutputData {
    private final String paperId;
    public SaveOutputData(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperId() {
        return paperId;
    }
}
