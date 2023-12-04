package interface_adapters.vote;

import interface_adapters.ViewManagerModel;
import use_cases.vote.VoteOutputBoundary;
import use_cases.vote.VoteOutputData;

public class VotePresenter implements VoteOutputBoundary {
    private final VoteViewModel voteViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor for presenter for Vote use case.
     * @param voteViewModel the view model for Vote use case
     * @param viewManagerModel the view manager model
     */
    public VotePresenter(VoteViewModel voteViewModel,
                         ViewManagerModel viewManagerModel) {
        this.voteViewModel = voteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare the view when the paper is successfully voted.
     * @param voteOutputData the output data for the Vote use case
     */
    @Override
    public void prepareSuccessView(VoteOutputData voteOutputData) {
        VoteState voteState = voteViewModel.getState();
        voteState.setPaperId(voteOutputData.getPaperId());
        this.voteViewModel.setState(voteState);
        this.voteViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(voteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepare the view when the paper is not successfully saved.
     * @param error the error message
     */
    @Override
    public void prepareFailView(String error) {
        VoteState voteState = voteViewModel.getState();
        if (error.contains("up")) {
            voteState.setPaperAlreadyUpvotedError(error);
        } else {
            voteState.setPaperAlreadyDownvotedError(error);
        }
        this.voteViewModel.setState(voteState);
        this.voteViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(voteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();    }

}
