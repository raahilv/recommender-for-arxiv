package use_cases;

import data_access.*;
import entities.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.recommend.RecommendPresenter;
import interface_adapters.recommend.RecommendViewModel;
import org.junit.Test;
import use_cases.recommend.RecommendInputData;
import use_cases.recommend.RecommendInteractor;
import use_cases.recommend.RecommendOutputBoundary;
import use_cases.recommend.RecommendOutputData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    private final List<String> gn = new ArrayList<>(Arrays.asList("cs", "cs.CR", ""));
    private final List<String> ap = new ArrayList<>(Arrays.asList("cs", "cs.AR", ""));
    private final List<String> sp = new ArrayList<>(Arrays.asList("cs", "cs.CV", ""));
    private final List<List<String>> preferredCategoriesRawData = new ArrayList<>(Arrays.asList(gn, ap, sp));
    private final List<Category> preferredCategories = new ArrayList<>(Arrays.asList(cf.create(gn), cf.create(ap), cf.create(sp)));

    @Test
    public void testGetCategoryMatchCountWithoutPrioritizeCategorySearchAndWithoutPrioritizeUpvotePercentage() {
        try {
            String username = "kevin";
            Category category = new CategoryFactory().create(new ArrayList<>(List.of(new String[]{"cs", "cs.CR"})));
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(uf);
            ArxivDataAccessObject aDAO = new ArxivDataAccessObject(new ArrayList<>(List.of(new Category[]{category})), new AuthorFactory());
            RecommendPresenter presenter = new RecommendPresenter(new RecommendViewModel(), new ViewManagerModel());

            RecommendDataAccessObject rDAO = new RecommendDataAccessObject(aDAO, uDAO);
            RecommendInteractor interactor = new RecommendInteractor(rDAO, presenter, cf, pdf);
            List<String> papers = aDAO.filterPapersByRootCategory(category);

            ResearchPaper randomPaper = aDAO.getPaperByID(papers.get(new Random().nextInt(papers.size())));
            List<List<String>> categories = new ArrayList<>();
            categories.add(new ArrayList<>(List.of("cs", "cs.AR", "")));
            categories.add(new ArrayList<>(List.of("cs", "cs.CV", "")));
            categories.add(new ArrayList<>(List.of("cs", "cs.CR", "")));
            PreferenceData preference = new PreferenceDataFactory().createWithRawData(username, categories, false, false, false);

            assert (interactor.getMatchScore(randomPaper, preference) == 2);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

    }

    @Test
    public void testGetCategoryMatchCountWithoutPrioritizeCategorySearchAndWithPrioritizeUpvotePercentage() {
        try {
            String username = "kevin";
            Category category = new CategoryFactory().create(new ArrayList<>(List.of(new String[]{"cs", "cs.CV"})));
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(uf);
            ArxivDataAccessObject aDAO = new ArxivDataAccessObject(new ArrayList<>(List.of(new Category[]{category})), new AuthorFactory());
            RecommendPresenter presenter = new RecommendPresenter(new RecommendViewModel(), new ViewManagerModel());

            RecommendDataAccessObject rDAO = new RecommendDataAccessObject(aDAO, uDAO);
            RecommendInteractor interactor = new RecommendInteractor(rDAO, presenter, cf, pdf);
            List<String> papers = aDAO.filterPapersByRootCategory(category);

            ResearchPaper randomPaper = aDAO.getPaperByID(papers.get(new Random().nextInt(papers.size())));
            List<List<String>> categories = new ArrayList<>();
            categories.add(new ArrayList<>(List.of("cs", "cs.AI", "")));
            categories.add(new ArrayList<>(List.of("cs", "cs.CV", "")));
            categories.add(new ArrayList<>(List.of("cs", "cs.AR", "")));
            PreferenceData preference = new PreferenceDataFactory().createWithRawData(username, categories, false, true, false);

            assert (interactor.getMatchScore(randomPaper, preference) == 11);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Test
    public void testGetCategoryMatchCountWithPrioritizeCategorySearchAndWithPrioritizeUpvotePercentage() {
        try {
            String username = "k";
            Category category = new CategoryFactory().create(new ArrayList<>(List.of(new String[]{"cs", "CR"})));
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(uf);
            ArxivDataAccessObject aDAO = new ArxivDataAccessObject(new ArrayList<>(List.of(new Category[]{category})), new AuthorFactory());
            RecommendPresenter presenter = new RecommendPresenter(new RecommendViewModel(), new ViewManagerModel());

            RecommendDataAccessObject rDAO = new RecommendDataAccessObject(aDAO, uDAO);
            RecommendInteractor interactor = new RecommendInteractor(rDAO, presenter, cf, pdf);
            List<String> papers = aDAO.filterPapersByRootCategory(category);

            ResearchPaper randomPaper = aDAO.getPaperByID(papers.get(new Random().nextInt(papers.size())));
            List<List<String>> categories = new ArrayList<>();
            categories.add(new ArrayList<>(List.of("cs", "CC", "")));
            categories.add(new ArrayList<>(List.of("cs", "AI", "")));
            PreferenceData preference = new PreferenceDataFactory().createWithRawData(username, categories, true, true, false);

            assert (interactor.getMatchScore(randomPaper, preference) == 10);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Test
    public void testGetCategoryMatchCountZeroMatches() {
        try {
            Category category = new CategoryFactory().create(new ArrayList<>(List.of(new String[]{"cs", "CC"})));
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(uf);
            ArxivDataAccessObject aDAO = new ArxivDataAccessObject(new ArrayList<>(List.of(new Category[]{category})), new AuthorFactory());
            RecommendPresenter presenter = new RecommendPresenter(new RecommendViewModel(), new ViewManagerModel());

            RecommendDataAccessObject rDAO = new RecommendDataAccessObject(aDAO, uDAO);
            RecommendInteractor interactor = new RecommendInteractor(rDAO, presenter, cf, pdf);
            List<String> papers = aDAO.filterPapersByRootCategory(category);

            ResearchPaper randomPaper = aDAO.getPaperByID(papers.get(new Random().nextInt(papers.size())));
            List<Category> categories = new ArrayList<>();
            categories.add(new CategoryFactory().create(new ArrayList<>(List.of("cs", "AI", ""))));
            categories.add(new CategoryFactory().create(new ArrayList<>(List.of("cs", "CV", ""))));
            categories.add(new CategoryFactory().create(new ArrayList<>(List.of("cs", "AR", ""))));

            assert (interactor.getCategoryMatchCount(randomPaper, categories) == 0);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Test
    public void testGetCategoryMatchCountAtLeastOneMatch() {
        try {
            String username = "kevin";
            Category category = new CategoryFactory().create(new ArrayList<>(List.of(new String[]{"cs", "CV"})));
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(uf);
            ArxivDataAccessObject aDAO = new ArxivDataAccessObject(new ArrayList<>(List.of(new Category[]{category})), new AuthorFactory());
            RecommendPresenter presenter = new RecommendPresenter(new RecommendViewModel(), new ViewManagerModel());

            RecommendDataAccessObject rDAO = new RecommendDataAccessObject(aDAO, uDAO);
            RecommendInteractor interactor = new RecommendInteractor(rDAO, presenter, cf, pdf);
            List<String> papers = aDAO.filterPapersByRootCategory(category);

            ResearchPaper randomPaper = aDAO.getPaperByID(papers.get(new Random().nextInt(papers.size())));
            List<Category> categories = new ArrayList<>();
            categories.add(new CategoryFactory().create(new ArrayList<>(List.of("cs", "AI", ""))));
            categories.add(new CategoryFactory().create(new ArrayList<>(List.of("cs", "CV", ""))));
            categories.add(new CategoryFactory().create(new ArrayList<>(List.of("cs", "AR", ""))));

            assert (interactor.getCategoryMatchCount(randomPaper, categories) == 1);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    @Test
    public void testRecommendInteractor() {
        try {
            String username = "kevin";
            LocalUserDataAccessObject uDAO = new LocalUserDataAccessObject(uf);
            ArxivDataAccessObject aDAO = new ArxivDataAccessObject(preferredCategories, new AuthorFactory());

            RecommendInputData input = new RecommendInputData(username, preferredCategoriesRawData, false, false, false);
            RecommendPresenter presenter = new RecommendPresenter(new RecommendViewModel(), new ViewManagerModel());

            RecommendDataAccessObject rDAO = new RecommendDataAccessObject(aDAO, uDAO);
            RecommendInteractor interactor = new RecommendInteractor(rDAO, presenter, cf, pdf);
            // interactor.execute(input);
            PreferenceData preferenceData = new PreferenceData(username, preferredCategories, false, false, false);
            List<ResearchPaper> papers = interactor.recommend(preferenceData);
            System.out.println(papers.size());


        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

}
