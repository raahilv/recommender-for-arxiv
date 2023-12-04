package use_cases.switchView;

public class SwitchViewOutputData {
    private final String viewName;

    /**
     * Constructor for the output data for Switch View use case.
     * @param viewName name of the view that want to switch to
     */
    public SwitchViewOutputData(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Get the name of the view that want to switch to.
     * @return the name of the view that want to switch to
     */
    public String getViewName() {
        return viewName;
    }
}
