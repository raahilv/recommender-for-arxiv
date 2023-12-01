package interface_adapters.switchView;

import interface_adapters.ViewManagerModel;
import use_cases.switchView.SwitchViewOutputBoundary;
import use_cases.switchView.SwitchViewOutputData;

public class SwitchViewPresenter implements SwitchViewOutputBoundary {

    private final SwitchViewViewModel switchViewViewModel;
    private ViewManagerModel viewManagerModel;

    public SwitchViewPresenter(SwitchViewViewModel switchViewViewModel, ViewManagerModel viewManagerModel) {
        this.switchViewViewModel = switchViewViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SwitchViewOutputData switchViewOutputData) {
        String viewName = switchViewOutputData.getViewName();
        this.viewManagerModel.setActiveView(viewName);
    }
}
