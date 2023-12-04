package use_cases.switchView;

public class SwitchViewInputData {
    final private String viewName;

    /**
     * Constructor for input data with viewName for Switch View use case.
     * @param viewName the name of the view that want to switch to
     */
    public SwitchViewInputData(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Get the view name stored in the input data.
     * @return the view name stored in the input data
     */
    public String getViewName() {
        return viewName;
    }
}
