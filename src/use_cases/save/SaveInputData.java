package use_cases.save;

import entities.ResearchPaper;
import entities.User;

public class SaveInputData {
    final private String userName;
    final private String paperId;

    public SaveInputData(String userName, String paperId) {
        this.userName = userName;
        this.paperId = paperId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPaperId() {
        return paperId;
    }
}
