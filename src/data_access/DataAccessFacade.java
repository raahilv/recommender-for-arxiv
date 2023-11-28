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
    private final LocalUserDataAccessObject localUserDAO;
    private final LocalLibraryDataAccessObject libraryDAO;
    private final LocalResearchPaperDataAccessObject localResearchPaperDAO;
    private final ArxivDataAccessObject arxivDAO;

    public DataAccessFacade(LocalUserDataAccessObject localUserDAO, LocalLibraryDataAccessObject libraryDAO,
                            LocalResearchPaperDataAccessObject localResearchPaperDAO, ArxivDataAccessObject arxivDAO) {
        this.localUserDAO = localUserDAO;
        this.libraryDAO = libraryDAO;
        this.localResearchPaperDAO = localResearchPaperDAO;
        this.arxivDAO = arxivDAO;
    }

    @Override
    public boolean existsByUsername(String username) {
        return localUserDAO.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        localUserDAO.save(user);
    }

    @Override
    public User get(String username) {
        return localUserDAO.get(username);
    }

    @Override
    public User getUser(String userName) {
        return localUserDAO.getUser(userName);
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
