package interface_adapters.RecommendHome;

import use_cases.library.LibraryInputBoundary;
import use_cases.library.LibraryInputData;
import use_cases.recommend.RecommendInputBoundary;
import use_cases.recommend.RecommendInputData;

import java.util.List;

public class RecommendHomeController {
    final RecommendInputBoundary recommendInputBoundaryInteractor;
    final LibraryInputBoundary libraryInputBoundaryInteractor;
    public RecommendHomeController(RecommendInputBoundary recommendInputBoundaryInteractor, LibraryInputBoundary libraryInputBoundary) {
        this.recommendInputBoundaryInteractor = recommendInputBoundaryInteractor;
        this.libraryInputBoundaryInteractor = libraryInputBoundary;
    }
    public void execute(List<List<String>> categories, boolean prioritizeSubcategorySearch, boolean prioritizeUpvotePercentageSearch, boolean wantAutoRecommend, String username){
        /*
        Collects user preferences and sends the collected data to recommend use case, so it can recommend papers using
        these user preferences data
         */
        RecommendInputData inputData = new RecommendInputData(username,categories, prioritizeSubcategorySearch, prioritizeUpvotePercentageSearch, wantAutoRecommend);
        recommendInputBoundaryInteractor.execute(inputData);
    }

    public void SwitchToLibrary(String userName){
        /*
        A method to switch to library view, which displays user's bookmarked research papers
         */
        LibraryInputData libraryInputData = new LibraryInputData(userName);
        libraryInputBoundaryInteractor.execute(libraryInputData);
    }
}
