package interface_adapters.switchView;

import interface_adapters.ViewManagerModel;
import use_cases.switchView.SwitchViewOutputBoundary;
import use_cases.switchView.SwitchViewOutputData;

public class SwitchViewPresenter implements SwitchViewOutputBoundary {

    private final SwitchViewViewModel switchViewViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructor for presenter for Switch View use case.
     * @param switchViewViewModel the view model for Switch View use case
     * @param viewManagerModel the view manager model
     */
    public SwitchViewPresenter(SwitchViewViewModel switchViewViewModel, ViewManagerModel viewManagerModel) {
        this.switchViewViewModel = switchViewViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepare showing the view that want to switch to.
     * @param switchViewOutputData the output data for the Switch View use case
     */
    @Override
    public void prepareSuccessView(SwitchViewOutputData switchViewOutputData) {
        String viewName = switchViewOutputData.getViewName();
        this.viewManagerModel.setActiveView(viewName);
        this.viewManagerModel.firePropertyChanged();
    }
}
