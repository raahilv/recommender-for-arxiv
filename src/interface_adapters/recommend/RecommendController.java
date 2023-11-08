package interface_adapters.recommend;

import use_cases.recommend.RecommendInputBoundary;
import use_cases.recommend.RecommendInputData;

import java.util.List;

public class RecommendController {

    final RecommendInputBoundary recommendUseCaseInteractor;

    public RecommendController(RecommendInputBoundary recommendUseCaseInteractor) {
        this.recommendUseCaseInteractor = recommendUseCaseInteractor;
    }

    public void execute(String username, String id, String title, String journalReference,
                        String parentCategory, List<String> childCategories, boolean wantAutoRecommendation) {
        RecommendInputData recommendInputData = new RecommendInputData(
                username, id, title, journalReference, parentCategory, childCategories, wantAutoRecommendation
        );
        this.recommendUseCaseInteractor.execute(recommendInputData);
    }

}
