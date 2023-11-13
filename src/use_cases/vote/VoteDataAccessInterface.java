package use_cases.vote;

import entities.ResearchPaper;
import entities.User;

public interface VoteDataAccessInterface {
    User getUser(String username);
    ResearchPaper getPaper(String paperName);
}
