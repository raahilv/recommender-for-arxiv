package use_cases;

import data_access.DataAccessFacade;
import data_access.LocalLibraryDataAccessObject;
import data_access.LocalResearchPaperDataAccessObject;
import entities.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.library.LibraryPresenter;
import interface_adapters.library.LibraryViewModel;
import use_cases.library.LibraryInputData;
import use_cases.library.LibraryInteractor;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class LibraryUseCaseTest {
    @org.junit.Test
    public void TestLibraryUseCase() throws IOException {
        // create the UI; note, we don't make a real InputBoundary,
        // since we don't need it for this test.
        LocalLibraryDataAccessObject DAO = new LocalLibraryDataAccessObject(new LocalResearchPaperDataAccessObject("./test/test_files/papers.csv", new AuthorFactory(),new CategoryFactory(), new ResearchPaperFactory()));
        ArrayList<Category> tempCat = new ArrayList<Category>();
        tempCat.add(new Category("cs", "cs","cs"));
        ArrayList<Author> tempAuthor = new ArrayList<>();
        tempAuthor.add(new Author("e"));
        ResearchPaper tempPaper = new ResearchPaper("1", "e",tempCat , tempAuthor,
                LocalDate.now(), "eee", "eeeee",
                "eee.com", 0, 0);
        DAO.saveToDatabase("ege", tempPaper);
        LibraryViewModel libraryViewModel = new LibraryViewModel();
        DataAccessFacade facade = new DataAccessFacade(null,DAO,null,null);
        LibraryInteractor libint = new LibraryInteractor(facade,new LibraryPresenter(new ViewManagerModel(),libraryViewModel));
        libint.execute(new LibraryInputData("ege"));
        assert(libraryViewModel.getState().getIds().get(0).equals("1"));
    }
}
