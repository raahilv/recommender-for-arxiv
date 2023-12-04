package use_cases.switchView;

import use_cases.save.SaveOutputData;

public interface SwitchViewOutputBoundary {

    /**
     * Prepare showing the view that want to switch to.
     * @param switchViewOutputData the output data for the Switch View use case
     */
    void prepareSuccessView(SwitchViewOutputData switchViewOutputData);
}
