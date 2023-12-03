package use_case_test;

import data_access.*;
import entities.*;
import interface_adapters.recommend.RecommendPresenter;
import org.junit.Test;
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

    @Test
    public void testAdjustWithInputGreaterThanOne1() {
        double eps = 10e-10;
        int factor = 10;
        double result = RecommendInteractor.adjust(factor);
        assert (Math.abs(result - 1.0) <= eps);
//        try {
//            LocalResearchPaperDataAccessObject rpDAO = new LocalResearchPaperDataAccessObject(PAPER_FILE_PATH, af, cf, rpf);
//            LocalLibraryDataAccessObject lDAO = new LocalLibraryDataAccessObject(rpDAO);
//            LocalPreferredCategoriesDataAccessObject pcDAO = new LocalPreferredCategoriesDataAccessObject(cf);
//            LocalUpvotedPapersDataAccessObject upDAO = new LocalUpvotedPapersDataAccessObject(UPVOTED_FILE_PATH, rpDAO);
//            LocalDownvotedPapersDataAccessObject dpDAO = new LocalDownvotedPapersDataAccessObject(DOWNVOTED_FILE_PATH, rpDAO);
//            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(lDAO, upDAO, dpDAO, pcDAO, uf);
//
//            List<Category> categories = new ArrayList<>();
//            String[] c1Info = {"r00", "s00", "d00"};
//            String[] c2Info = {"r01", "s01", "d01"};
//            categories.add(cf.create(Arrays.asList(c1Info)));
//            categories.add(cf.create(Arrays.asList(c2Info)));
//
//            List<Author> authors = new ArrayList<>();
//            authors.add(af.createWithoutAffiliation("Roberto Metere"));
//            authors.add(af.createWithoutAffiliation("Changyu Dong"));
////            ArxivDataAccessObject arxivDAO = new ArxivDataAccessObject();
////            RecommendDataAccessObject rDAO = new RecommendDataAccessObject(, uDAO)
////            RecommendOutputBoundary presenter = new RecommendPresenter();
////            RecommendInteractor ri = new RecommendInteractor(uDAO, presenter, cf, pdf);
//            int factor = 10;
//            // assert ()
//        } catch (IOException ioe) {
//            System.out.println("IOException in testAdjustWithInputGreaterThanOne().");
//        }
    }

    @Test
    public void testAdjustWithInputGreaterThanOne2() {
        double eps = 10e-10;
        int factor = 36;
        double result = RecommendInteractor.adjust(factor);
        assert (Math.abs(result - 3.6) <= eps);
    }
}
