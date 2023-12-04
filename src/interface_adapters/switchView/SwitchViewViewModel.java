package interface_adapters.switchView;

import interface_adapters.ViewModel;
import interface_adapters.save.SaveState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SwitchViewViewModel extends ViewModel {
    public final String TITLE_LABEL = "Switch View";
    public static final String SAVE_BUTTON_LABEL = "Back";

    private SwitchViewState state = new SwitchViewState();

    /**
     * Constructor for view model for Switch View use case.
     */
    public SwitchViewViewModel() {
        super("switch");
    }

    /**
     * Set Switch View state stored in the view model.
     * @param state the Switch View state to be stored in the view model
     */
    public void setState(SwitchViewState state) {
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
     * Get the Switch View state stored in the view model.
     * @return the Switch View state stored in the view model
     */
    public SwitchViewState getState() {
        return state;
    }
}
