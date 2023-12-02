package use_cases.save;

import entities.ResearchPaper;
import entities.User;

public class SaveInputData {
    final private String userName;
    final private String paperId;

    /**
     * Constructor for the input data for Save use case.
     * @param userName userName of the user who saves the paper.
     * @param paperId id of the paper saved by the user.
     */
    public SaveInputData(String userName, String paperId) {
        this.userName = userName;
        this.paperId = paperId;
    }

    /**
     * Get userName stored.
     * @return the username of the user who saves the paper.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get paperId stored.
     * @return the id of the paper that is saved by the user.
     */
    public String getPaperId() {
        return paperId;
    }
}
