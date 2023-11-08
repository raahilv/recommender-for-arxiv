package interface_adapters.recommend;

import entities.Category;
import use_cases.recommend.RecommendInputBoundary;
import use_cases.recommend.RecommendInputData;

import java.util.List;
import java.util.Map;

public class RecommendController {

    final RecommendInputBoundary recommendUseCaseInteractor;

    public RecommendController(RecommendInputBoundary recommendUseCaseInteractor) {
        this.recommendUseCaseInteractor = recommendUseCaseInteractor;
    }

    public void execute(String username, List<Category> preferredCategories,
                        boolean prioritizeSubcategorySearch, boolean prioritizeUpvotePercentageSearch,
                        boolean wantAutoRecommend) {
        RecommendInputData recommendInputData = new RecommendInputData(
                username, preferredCategories, prioritizeSubcategorySearch,
                prioritizeUpvotePercentageSearch, wantAutoRecommend
        );
        this.recommendUseCaseInteractor.execute(recommendInputData);
    }

}
