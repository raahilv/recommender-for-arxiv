package use_cases.switchView;

public class SwitchViewInteractor implements SwitchViewInputBoundary{
    final SwitchViewDataAccessInterface switchViewDataAccessObject;
    final SwitchViewOutputBoundary switchViewPresenter;

    public SwitchViewInteractor(SwitchViewDataAccessInterface switchViewDataAccessObject, SwitchViewOutputBoundary switchViewPresenter) {
        this.switchViewDataAccessObject = switchViewDataAccessObject;
        this.switchViewPresenter = switchViewPresenter;
    }

    @Override
    public void execute(SwitchViewInputData inputData) {
        String viewName = inputData.getViewName();
        SwitchViewOutputData switchViewOutputData = new SwitchViewOutputData(viewName);
        switchViewPresenter.prepareSuccessView(switchViewOutputData);
    }
}
