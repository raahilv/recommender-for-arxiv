package use_cases.recommend;

import entities.User;
import entities.ResearchPaper;

import java.util.List;

public interface RecommendDataAccessInterface {

    /**
     * Return whether the user with the given *username* exists in
     * the local user database.
     * @param username the username to be checked
     * */
    boolean existsByUsername(String username);

    /**
     * Return the user with the given username. Return null if this
     * user does not exist.
     * @param username the username to be searched
     * @return null if the user with *username* does not exist, otherwise return
     *         the user with the username *username*
     * */
    User getUser(String username);

    /**
     * Return the research paper with the identifier *id*.
     * @param id the identifier to be searched
     * @return the ResearchPaper object with the identifier *id*
     * */
    ResearchPaper getPaperByID(String id);

    /**
     * Return the research paper with the given title.
     * @param title the title to be searched
     * @return the ResearchPaper object with the given title
     * */
    ResearchPaper getPaperByTitle(String title);

    /**
     * Return a list of paper IDs with the same *root category*.
     * @param rootCategory the root category to be searched
     * @return a list of paper IDs with the given root category
     * */
    List<String> filterPapersByRootCategory(String rootCategory);

}
