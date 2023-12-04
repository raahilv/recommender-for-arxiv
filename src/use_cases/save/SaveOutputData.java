package use_cases.save;

public class SaveOutputData {
    private final String paperId;

    /**
     * Constructor for output data for save use case.
     * @param paperId id of the paper that the user saves
     */
    public SaveOutputData(String paperId) {
        this.paperId = paperId;
    }

    /**
     * Get paperId stored.
     * @return the id of the paper that the user saves
     */
    public String getPaperId() {
        return paperId;
    }
}