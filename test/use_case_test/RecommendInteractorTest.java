package use_case_test;

import data_access.*;
import entities.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.recommend.RecommendPresenter;
import interface_adapters.recommend.RecommendViewModel;
import org.junit.Test;
import use_cases.recommend.RecommendInputData;
import use_cases.recommend.RecommendInteractor;
import use_cases.recommend.RecommendOutputBoundary;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendInteractorTest {
    private static final String PAPER_FILE_PATH = "test/test_files/papers.csv";
    private static final String UPVOTED_FILE_PATH = "test/test_files/upvotedPapers.csv";
    private static final String DOWNVOTED_FILE_PATH = "test/test_files/downvotedPapers.csv";
    private static final String CATEGORIES_FILE_PATH = "test/test_files/categories.csv";
    private final AuthorFactory af = new AuthorFactory();
    private final CategoryFactory cf = new CategoryFactory();
    private final ResearchPaperFactory rpf = new ResearchPaperFactory();
    private final PreferenceDataFactory pdf = new PreferenceDataFactory();
    private final UserFactory uf = new CommonUserFactory();
    private final Author a1 = new Author("Zhipp Canuff");
    private final Author a2 = new Author("Mnji Aito");
    private final List<String> gn = new ArrayList<>(Arrays.asList("econ", "GN", "General Economics"));
    private final List<String> ap = new ArrayList<>(Arrays.asList("stat", "AP", "Applications"));
    private final List<String> sp = new ArrayList<>(Arrays.asList("math", "SP", "Spectral Theory"));
    private final List<List<String>> preferredCategoriesRawData = new ArrayList<>(Arrays.asList(gn, ap, sp));
    private final List<Category> preferredCategories = new ArrayList<>(Arrays.asList(cf.create(gn), cf.create(ap), cf.create(sp)));
    private final List<Author> authors = new ArrayList<>(Arrays.asList(a1, a2));

    @Test
    public void testRecommendInteractor() {
        try {
            String username = "kevin";

            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPER_FILE_PATH, af, cf, rpf);
            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_FILE_PATH, rpDAO);
            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_FILE_PATH, rpDAO);
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);
            ArxivDataAccessObject aDAO = new ArxivDataAccessObject(preferredCategories, authors);

            RecommendInputData input = new RecommendInputData(username, preferredCategoriesRawData, false, false, false);
            RecommendPresenter presenter = new RecommendPresenter(new RecommendViewModel(), new ViewManagerModel());

            RecommendDataAccessObject rDAO = new RecommendDataAccessObject(aDAO, uDAO);
            RecommendInteractor interactor = new RecommendInteractor(rDAO, presenter, cf, pdf);
            interactor.execute(input);


        } catch (IOException ioe) {
            System.out.println("IOException in testAdjustWithInputGreaterThanOne().");
        }
    }

}
