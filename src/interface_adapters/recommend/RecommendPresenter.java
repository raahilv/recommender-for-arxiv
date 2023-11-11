package interface_adapters.recommend;

import use_cases.recommend.RecommendOutputBoundary;
import use_cases.recommend.RecommendOutputData;
import interface_adapters.ViewManagerModel;

public class RecommendPresenter implements RecommendOutputBoundary {

    private final RecommendViewModel recommendViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecommendPresenter(RecommendViewModel recommendViewModel, ViewManagerModel viewManagerModel) {
        this.recommendViewModel = recommendViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(RecommendOutputData recommendOutputData) {

    }
    public void prepareFailView(String error) {

    }

}
