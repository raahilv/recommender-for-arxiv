package interface_adapters.save;

import interface_adapters.ViewManagerModel;
import use_cases.save.SaveOutputBoundary;
import use_cases.save.SaveOutputData;
import view.ViewManager;

public class SavePresenter implements SaveOutputBoundary {
    private final SaveViewModel saveViewModel;
    private ViewManagerModel viewManagerModel;

    public SavePresenter(SaveViewModel saveViewModel,
                         ViewManagerModel viewManagerModel) {
        this.saveViewModel = saveViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SaveOutputData saveOutputData) {
        SaveState saveState = saveViewModel.getState();
        saveState.setPaperId(saveOutputData.getPaperId());
        this.saveViewModel.setState(saveState);
        this.saveViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(saveViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

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
