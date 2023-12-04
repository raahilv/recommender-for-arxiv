package data_access;

import entities.Category;
import entities.ResearchPaper;
import entities.User;
import use_cases.recommend.RecommendDataAccessInterface;

import java.util.List;

public class RecommendDataAccessObject implements RecommendDataAccessInterface {
    private final ArxivDataAccessObject arxivDAO;
    private final LocalUserDataAccessObject userDAO;

    public RecommendDataAccessObject(ArxivDataAccessObject arxixDAO, LocalUserDataAccessObject userDAO) {
        this.arxivDAO = arxixDAO;
        this.userDAO = userDAO;
    }


    @Override
    public boolean existsByUsername(String username) {
        return this.userDAO.existsByUsername(username);
    }

    @Override
    public User getUser(String username) {
        return this.userDAO.get(username);
    }

    @Override
    public ResearchPaper getPaperByID(String id) {
        return this.arxivDAO.getPaperByID(id);
    }

    @Override
    public ResearchPaper getPaperByTitle(String title) {
        return this.arxivDAO.getPaperByTitle(title);
    }


    @Override
    public List<String> filterPapersByRootCategory(Category category) {
        return this.arxivDAO.filterPapersByRootCategory(category);
    }
}
