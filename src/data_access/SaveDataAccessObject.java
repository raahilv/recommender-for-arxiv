package data_access;

import entities.ResearchPaper;
import entities.User;
import use_cases.save.SaveDataAccessInterface;

public class SaveDataAccessObject implements SaveDataAccessInterface {

    private final LocalUserDataAccessObject uDAO;
    private final LocalResearchPaperDataAccessObject rpDAO;
    private final LocalLibraryDataAccessObject lDAO;

    public SaveDataAccessObject(LocalUserDataAccessObject uDAO, LocalResearchPaperDataAccessObject rpDAO, LocalLibraryDataAccessObject lDAO) {
        this.uDAO = uDAO;
        this.rpDAO = rpDAO;
        this.lDAO = lDAO;
    }

    @Override
    public User getUser(String username) {
        return this.uDAO.getUser(username);
    }

    @Override
    public ResearchPaper getPaper(String paperID) {
        return this.rpDAO.getPaperByID(paperID);
    }

    @Override
    public void save(String username, ResearchPaper paper) {
        this.lDAO.saveToDatabase(username, paper);
    }
}
