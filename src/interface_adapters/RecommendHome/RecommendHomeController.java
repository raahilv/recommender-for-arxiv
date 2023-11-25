package interface_adapters.RecommendHome;

import use_cases.recommend.RecommendInputBoundary;
import use_cases.recommend.RecommendInputData;

import java.util.List;

public class RecommendHomeController {
    final RecommendInputBoundary recommendInputBoundaryInteractor;
    public RecommendHomeController(RecommendInputBoundary recommendInputBoundaryInteractor) {
        this.recommendInputBoundaryInteractor = recommendInputBoundaryInteractor;
    }
    public void execute(List<List<String>> categories, boolean prioritizeSubcategorySearch, boolean prioritizeUpvotePercentageSearch, boolean wantAutoRecommend){
        RecommendInputData inputData = new RecommendInputData("User123",categories, prioritizeSubcategorySearch, prioritizeUpvotePercentageSearch, wantAutoRecommend);
        recommendInputBoundaryInteractor.execute(inputData);
    }
}
