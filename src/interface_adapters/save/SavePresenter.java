package interface_adapters.save;

import use_cases.save.SaveOutputBoundary;
import use_cases.save.SaveOutputData;
import view.ViewManager;

public class SavePresenter implements SaveOutputBoundary {
    private final SaveViewModel saveViewModel;
    private ViewManager viewManager;

    public SavePresenter(SaveViewModel saveViewModel,
                         ViewManager viewManager) {
        this.saveViewModel = saveViewModel;
        this.viewManager = viewManager;
    }

    @Override
    public void prepareSuccessView(SaveOutputData saveOutputData) {
        SaveState saveState = saveViewModel.getState();
        saveState.setPaperId(saveOutputData.getPaperId());
        this.saveViewModel.setState(saveState);
        this.saveViewModel.firePropertyChanged();

        this.viewManager.
    }

    @Override
    public void prepareFailView(String error) {

    }
}
