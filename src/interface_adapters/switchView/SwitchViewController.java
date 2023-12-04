package interface_adapters.switchView;

import use_cases.switchView.SwitchViewInputBoundary;
import use_cases.switchView.SwitchViewInputData;

public class SwitchViewController {
    final SwitchViewInputBoundary switchViewInteractor;

    /**
     * Constructor for controller for Switch View use case.
     * @param switchViewInteractor the interactor used for executing the use case
     */
    public SwitchViewController(SwitchViewInputBoundary switchViewInteractor) {
        this.switchViewInteractor = switchViewInteractor;
    }

    /**
     * Execute Switch View use case, with input viewName.
     * @param viewName the name of the view that want to switch to
     */
    public void execute(String viewName) {
        SwitchViewInputData inputData = new SwitchViewInputData(viewName);
        this.switchViewInteractor.execute(inputData);
    }
}
