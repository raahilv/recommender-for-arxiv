package data_access;

import entities.Author;
import entities.ResearchPaper;
import entities.User;
import use_cases.login.LoginUserDataAccessInterface;
import use_cases.recommend.RecommendDataAccessInterface;
import use_cases.save.SaveDataAccessInterface;
import use_cases.showAuthor.ShowAuthorDataAccessInterface;
import use_cases.signup.SignupUserDataAccessInterface;
import use_cases.vote.VoteDataAccessInterface;

import java.util.List;

public class DataAccessFacade implements LoginUserDataAccessInterface, RecommendDataAccessInterface, SaveDataAccessInterface, ShowAuthorDataAccessInterface, SignupUserDataAccessInterface, VoteDataAccessInterface {
    private final InMemoryDataAccessObject imDAO;
    private final ArxivDataAccessObject arxivDAO;

    public DataAccessFacade(InMemoryDataAccessObject imDAO, ArxivDataAccessObject arxivDAO) {
        this.imDAO = imDAO;
        this.arxivDAO = arxivDAO;
    }

    @Override
    public boolean existsByUsername(String username) {
        return imDAO.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        imDAO.save(user);
    }

    @Override
    public User get(String username) {
        return imDAO.get(username);
    }

    @Override
    public User getUser(String userName) {
        return imDAO.getUser(userName);
    }

    @Override
    public ResearchPaper getPaper(String id) {
        return getPaperById(id);
    }

    @Override
    public ResearchPaper getPaperById(String id) {
        return arxivDAO.getPaperById(id);
    }

    @Override
    public ResearchPaper getPaperByTitle(String title) {
        return arxivDAO.getPaperByTitle(title);
    }

    @Override
    public ResearchPaper getPaperByJournalReference(String journalReference) {
        return arxivDAO.getPaperByJournalReference(journalReference);
    }

    @Override
    public List<String> filterPapersByRootCategory(String parentCategory) {
        return arxivDAO.filterPapersByRootCategory(parentCategory);
    }

    @Override
    public List<ResearchPaper> getPapersByAuthor(Author author) {
        return arxivDAO.getPapersByAuthor(author);
    }
}
