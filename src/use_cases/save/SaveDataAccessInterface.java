package use_cases.save;

import entities.ResearchPaper;
import entities.User;

public interface SaveDataAccessInterface {

    /**
     * Return the User object by the username.
     * @param username uername of the User
     * @return User object that has same username as input
     */
    User getUser(String username);

    /**
     * Return the Paper object by the paperId.
     * @param paperID paper id of the paper
     * @return Paper object that has same id as input
     */
    ResearchPaper getPaper(String paperID);

    /**
     * Save the given paper to the given user's library.
     * @param username username of the use
     * @param paper the research paper object
     * */
    void save(String username, ResearchPaper paper);

}
