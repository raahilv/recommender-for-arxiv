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
        RecommendInputData inputData = new RecommendInputData(username,categories, prioritizeSubcategorySearch, prioritizeUpvotePercentageSearch, wantAutoRecommend);
        recommendInputBoundaryInteractor.execute(inputData);
    }

    public void SwitchToLibrary(String userName){
        LibraryInputData libraryInputData = new LibraryInputData(userName);
        libraryInputBoundaryInteractor.execute(libraryInputData);
    }
}
