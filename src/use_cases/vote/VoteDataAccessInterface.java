package use_cases.vote;

import entities.ResearchPaper;
import entities.User;

public interface VoteDataAccessInterface {

    /**
     * Return the User object by the username.
     * @param username uername of the User
     * @return User object that has same username as input
     */
    User getUser(String username);

    /**
     * Return the Paper object by the paperId.
     * @param paperId paper id of the paper
     * @return Paper object that has same id as input
     */
    ResearchPaper getPaper(String paperId);
}