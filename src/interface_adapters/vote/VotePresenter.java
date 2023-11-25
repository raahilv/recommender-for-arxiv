package interface_adapters.vote;

import interface_adapters.ViewManagerModel;
import use_cases.vote.VoteOutputBoundary;
import use_cases.vote.VoteOutputData;

public class VotePresenter implements VoteOutputBoundary {
    private final VoteViewModel voteViewModel;
    private ViewManagerModel viewManagerModel;

    public VotePresenter(VoteViewModel voteViewModel,
                         ViewManagerModel viewManagerModel) {
        this.voteViewModel = voteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(VoteOutputData voteOutputData) {
        VoteState voteState = voteViewModel.getState();
        voteState.setPaperId(voteOutputData.getPaperId());
        this.voteViewModel.setState(voteState);
        this.voteViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(voteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        VoteState voteState = voteViewModel.getState();
        if (error.contains("up")) {
            voteState.setPaperAlreadyUpvotedError(error);
        } else {
            voteState.setPaperAlreadyDownvotedError(error);
        }
        voteViewModel.firePropertyChanged();
    }

}
