package interface_adapters.save;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SaveViewModel extends ViewModel {
    public final String TITLE_LABEL = "Save View";
    public static final String SAVE_BUTTON_LABEL = "Save";

    private SaveState state = new SaveState();

    /**
     * Constructor for view model of Save use case, set the viewName to "save".
     */
    public SaveViewModel() {
        super("save");
    }

    /**
     * Setter for state stored in the view model.
     * @param state the state to be stored
     */
    public void setState(SaveState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter for the state stored in the view model.
     * @return the stated stored in the view model
     */
    public SaveState getState() {
        return state;
    }
}
