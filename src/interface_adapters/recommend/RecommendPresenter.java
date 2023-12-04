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

    @Override
    public void prepareSuccessView(RecommendOutputData recommendOutputData) {
        RecommendState recommendState = this.recommendViewModel.getState();
        recommendState.setRecommendedPapers(recommendOutputData.getRecommendedPapers());
        this.recommendViewModel.setState(recommendState);
        recommendViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(recommendViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        RecommendState recommendState = this.recommendViewModel.getState();
        recommendState.setUserNotExistError(error);
        recommendViewModel.firePropertyChanged();
    }

}