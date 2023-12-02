package interface_adapters.save;

import interface_adapters.ViewManagerModel;
import use_cases.save.SaveOutputBoundary;
import use_cases.save.SaveOutputData;
import view.ViewManager;

public class SavePresenter implements SaveOutputBoundary {
    private final SaveViewModel saveViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor for presenter for Save use case.
     * @param saveViewModel the view model of save use case
     * @param viewManagerModel the view manager model
     */
    public SavePresenter(SaveViewModel saveViewModel,
                         ViewManagerModel viewManagerModel) {
        this.saveViewModel = saveViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare the view when the paper is successfully saved.
     * @param saveOutputData the output data for Save use case
     */
    @Override
    public void prepareSuccessView(SaveOutputData saveOutputData) {
        SaveState saveState = saveViewModel.getState();
        saveState.setPaperId(saveOutputData.getPaperId());
        this.saveViewModel.setState(saveState);
        this.saveViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(saveViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepare the view when the paper is not successfully saved.
     * @param error the error message
     */
    @Override
    public void prepareFailView(String error) {
        SaveState saveState = saveViewModel.getState();
        saveState.setPaperAlreadySavedError(error);
        this.saveViewModel.setState(saveState);
        this.saveViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(saveViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
