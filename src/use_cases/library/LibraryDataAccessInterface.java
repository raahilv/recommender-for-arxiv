package use_cases.library;

import entities.ResearchPaper;

import java.util.ArrayList;

public interface LibraryDataAccessInterface {
    public ArrayList<ResearchPaper> getSavedPapersForUser(String username);
    public void deleteSavedPaper(String userName,String paperID);
}
