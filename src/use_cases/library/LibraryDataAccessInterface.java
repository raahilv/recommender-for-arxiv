package use_cases.library;

import entities.ResearchPaper;

import java.util.List;


public interface LibraryDataAccessInterface {
    public List<ResearchPaper> getSavedPapersForUser(String username);
    public void deleteSavedPaper(String userName,String paperID);
}
