package data_access;

import entities.ResearchPaper;
import use_cases.library.LibraryDataAccessInterface;

import java.util.List;

public class LibraryDataAccessObject implements LibraryDataAccessInterface {

    private final LocalLibraryDataAccessObject lDAO;

    public LibraryDataAccessObject(LocalLibraryDataAccessObject lDAO) {
        this.lDAO = lDAO;
    }

    @Override
    public List<ResearchPaper> getSavedPapersForUser(String username) {
        return this.lDAO.getLibrary(username);
    }

    @Override
    public void deleteSavedPaper(String username, String paperID) {
        this.lDAO.delete(username, paperID);
    }
}
