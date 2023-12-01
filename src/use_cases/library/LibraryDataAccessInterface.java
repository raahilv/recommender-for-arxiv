package use_cases.library;

import entities.ResearchPaper;

import java.util.List;


public interface LibraryDataAccessInterface {
  
    List<ResearchPaper> getSavedPapersForUser(String username);
  
    void deleteSavedPaper(String username,String paperID);
  
}
