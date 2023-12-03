package interface_adapters.switchView;

import use_cases.switchView.SwitchViewInputBoundary;
import use_cases.switchView.SwitchViewInputData;

public class SwitchViewController {
    final SwitchViewInputBoundary switchViewInteractor;

    public SwitchViewController(SwitchViewInputBoundary switchViewInteractor) {
        this.switchViewInteractor = switchViewInteractor;
    }

    public void execute(String viewName) {
        SwitchViewInputData inputData = new SwitchViewInputData(viewName);
        this.switchViewInteractor.execute(inputData);
    }
}
