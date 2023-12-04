package use_cases.switchView;

public class SwitchViewInteractor implements SwitchViewInputBoundary{
    final SwitchViewDataAccessInterface switchViewDataAccessObject;
    final SwitchViewOutputBoundary switchViewPresenter;

    /**
     * Constructor for interactor of Switch View use case.
     * @param switchViewDataAccessObject the DAO for Switch View use case
     * @param switchViewPresenter the presenter for Switch View use case
     */
    public SwitchViewInteractor(SwitchViewDataAccessInterface switchViewDataAccessObject, SwitchViewOutputBoundary switchViewPresenter) {
        this.switchViewDataAccessObject = switchViewDataAccessObject;
        this.switchViewPresenter = switchViewPresenter;
    }

    /**
     * Execute Switch View use case, with input data.
     * Switch the active view to the one that has the same name as specified in the input data.
     * @param inputData the input data for Switch View use case
     */
    @Override
    public void execute(SwitchViewInputData inputData) {
        String viewName = inputData.getViewName();
        SwitchViewOutputData switchViewOutputData = new SwitchViewOutputData(viewName);
        switchViewPresenter.prepareSuccessView(switchViewOutputData);
    }
}
