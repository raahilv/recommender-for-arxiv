package data_access;

import entities.*;
import use_cases.library.LibraryDataAccessInterface;
import use_cases.login.LoginUserDataAccessInterface;
import use_cases.recommend.RecommendDataAccessInterface;
import use_cases.save.SaveDataAccessInterface;
import use_cases.showAuthor.ShowAuthorDataAccessInterface;
import use_cases.signup.SignupUserDataAccessInterface;
import use_cases.vote.VoteDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataAccessFacade implements LoginUserDataAccessInterface, RecommendDataAccessInterface,
        SaveDataAccessInterface, ShowAuthorDataAccessInterface, SignupUserDataAccessInterface,
        VoteDataAccessInterface, LibraryDataAccessInterface {
    private final LocalUserDataAccessObject localUserDAO;
    private final LocalLibraryDataAccessObject localLibraryDAO;
    private final LocalResearchPaperDataAccessObject localResearchPaperDAO;
    private final ArxivDataAccessObject arxivDAO;

    public DataAccessFacade(List<Category> categories) throws IOException {
        AuthorFactory af = new AuthorFactory();
        CategoryFactory cf = new CategoryFactory();
        UserFactory uf = new CommonUserFactory();
        ResearchPaperFactory rpf = new ResearchPaperFactory();

        this.localResearchPaperDAO = new LocalResearchPaperDataAccessObject("test/test_files/papers.csv", af, cf, rpf);
        this.localLibraryDAO = new LocalLibraryDataAccessObject(this.localResearchPaperDAO);
        LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
        LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject("test/test_files/upvotedPapers.csv", this.localResearchPaperDAO);
        LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject("test/test_files/downvotedPapers.csv", this.localResearchPaperDAO);
        this.localUserDAO = new LocalUserDataAccessObject(this.localLibraryDAO, upDAO, dpDAO, pcDAO, uf);
        this.arxivDAO = new ArxivDataAccessObject(categories, af);
    }

    public DataAccessFacade(LocalUserDataAccessObject localUserDAO, LocalLibraryDataAccessObject localLibraryDAO,
                            LocalResearchPaperDataAccessObject localResearchPaperDAO, ArxivDataAccessObject arxivDAO) {
        this.localUserDAO = localUserDAO;
        this.localLibraryDAO = localLibraryDAO;
        this.localResearchPaperDAO = localResearchPaperDAO;
        this.arxivDAO = arxivDAO;
    }

    @Override
    public boolean existsByUsername(String username) {
        return localUserDAO.existsByUsername(username);
    }

    @Override
    public void save(User user) {
        localUserDAO.saveToDatabase(user);
    }

    @Override
    public void save(String username, ResearchPaper paper) {
        this.localLibraryDAO.saveToDatabase(username, paper);
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
    public ResearchPaper getPaper(String paperID) {
        return getPaperById(paperID);
    }

    @Override
    public ResearchPaper getPaperById(String id) {
        return arxivDAO.getPaperByID(id);
    }

    @Override
    public ResearchPaper getPaperByTitle(String title) {
        return arxivDAO.getPaperByTitle(title);
    }

    @Override
    public List<String> filterPapersByRootCategory(String parentCategory) {
        return arxivDAO.filterPapersByRootCategory(parentCategory);
    }

    @Override
    public List<ResearchPaper> getPapersByAuthor(Author author) {
        return arxivDAO.getPapersByAuthor(author);
    }

    @Override
    public List<ResearchPaper> getSavedPapersForUser(String username) {
        return this.localLibraryDAO.getLibrary(username);
    }

    @Override
    public void deleteSavedPaper(String username,String paperID) {
        this.localLibraryDAO.delete(username, paperID);
    }

}
