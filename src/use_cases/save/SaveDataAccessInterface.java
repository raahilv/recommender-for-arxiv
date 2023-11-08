package use_cases.save;

import entities.ResearchPaper;
import entities.User;

public interface SaveDataAccessInterface {
    User getUser(String username);
    ResearchPaper getPaper(String paperName);
}
