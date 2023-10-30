package use_cases.save;

import entities.ResearchPaper;
import entities.User;

public class SaveInputData {
    final private User user;
    final private ResearchPaper paper;

    public SaveInputData(User user, ResearchPaper paper) {
        this.user = user;
        this.paper = paper;
    }

    public User getUser() {
        return user;
    }

    public ResearchPaper getPaper() {
        return paper;
    }
}
