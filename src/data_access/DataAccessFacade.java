package data_access;

import entities.Author;
import entities.ResearchPaper;
import entities.User;
import use_cases.login.LoginUserDataAccessInterface;
import use_cases.recommend.RecommendDataAccessInterface;
import use_cases.save.SaveDataAccessInterface;
import use_cases.showAuthor.showAuthorDataAccessInterface;
import use_cases.signup.SignupUserDataAccessInterface;
import use_cases.vote.VoteDataAccessInterface;

import java.util.List;

public class DataAccessFacade implements LoginUserDataAccessInterface, RecommendDataAccessInterface, SaveDataAccessInterface, showAuthorDataAccessInterface, SignupUserDataAccessInterface, VoteDataAccessInterface {
    LocalDataAccessObject lDAO;
    ArxivDataAccessObject arxivDAO;

    @Override
    public boolean existsByName(String identifier) {
        return lDAO.existsByName();
    }

    @Override
    public void save(User user) {
        lDAO.save(user)
    }

    @Override
    public User get(String username) {
        return lDAO.get(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return lDAO.existsByUsername(username);
    }

    @Override
    public User getUser(String userName) {
        return lDAO.getUser(userName);
    }

    @Override
    public ResearchPaper getPaper(String paperName) {
        return arxivDAO.getPaper(paperName);
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
    public List<ResearchPaper> getPapersbyAuthor(Author author) {
        return arxivDAO.getPapersByAuthor(author);
    }
}
