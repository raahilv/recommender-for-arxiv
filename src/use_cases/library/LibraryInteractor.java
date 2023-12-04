package use_cases.library;

import entities.ResearchPaper;

import java.util.List;

public class LibraryInteractor implements LibraryInputBoundary {

    private LibraryDataAccessInterface libraryDAO;
  
    private LibraryOutputBoundary libraryPresenter;
  
    public LibraryInteractor(LibraryDataAccessInterface DAO, LibraryOutputBoundary libraryPresenter){
        this.libraryDAO = DAO;
        this.libraryPresenter = libraryPresenter;
    }
  
    @Override
    public void execute(LibraryInputData inputData) {
        /*
        Uses user's bookmarked research papers to create lists of research paper information, which is used to construct
        user library.
         */
        List<ResearchPaper> researchPaperList = libraryDAO.getSavedPapersForUser(inputData.GetUserName());
        LibraryOutputData outputData = new LibraryOutputData();
        for (ResearchPaper rp : researchPaperList){
            outputData.addID(rp.getID());
            outputData.addPaperName(rp.getTitle());
            outputData.addPaperPDF(rp.getUrl());
        }
        libraryPresenter.prepareLibrary(outputData);
    }

}
