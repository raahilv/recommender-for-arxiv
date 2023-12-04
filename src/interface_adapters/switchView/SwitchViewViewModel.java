package interface_adapters.switchView;

import interface_adapters.ViewModel;
import interface_adapters.save.SaveState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SwitchViewViewModel extends ViewModel {
    public final String TITLE_LABEL = "Switch View";
    public static final String SAVE_BUTTON_LABEL = "Back";

    private SwitchViewState state = new SwitchViewState();

    public SwitchViewViewModel() {
        super("switch");
    }

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

    public SwitchViewState getState() {
        return state;
    }
}
