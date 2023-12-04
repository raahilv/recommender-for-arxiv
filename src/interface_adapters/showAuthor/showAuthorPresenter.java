package interface_adapters.showAuthor;

import interface_adapters.ViewManagerModel;
import use_cases.showAuthor.showAuthorOutputBoundary;
import use_cases.showAuthor.showAuthorOutputData;

public class showAuthorPresenter implements showAuthorOutputBoundary {

    private final showAuthorViewModel showAuthorViewModel;
    private  final ViewManagerModel viewMangerModel;

    public showAuthorPresenter(interface_adapters.showAuthor.showAuthorViewModel showAuthorViewModel, ViewManagerModel viewMangerModel) {
        this.showAuthorViewModel = showAuthorViewModel;
        this.viewMangerModel = viewMangerModel;
    }

    @Override
    public void prepareSuccessView(showAuthorOutputData response) {
        showAuthorState showAuthorState = showAuthorViewModel.getState();
        showAuthorState.setAuthorName(response.getAuthorName());
        showAuthorState.setAuthorPapers(response.getPapers());
        showAuthorState.setAverageUpvotes(response.getTotalUpvotes());
        showAuthorState.setTotalDownvotes(response.getTotalDownvotes());
        showAuthorState.setAverageUpvotes(response.getAverageUpvotes());

        this.showAuthorViewModel.setState(showAuthorState);
        this.showAuthorViewModel.firePropertyChanged();

        this.viewMangerModel.setActiveView(this.showAuthorViewModel.getViewName());
        this.viewMangerModel.firePropertyChanged();
    }

//    @Override
//    public void prepareFailurView(String error) {
//
//    }

}
