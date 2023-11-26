package interface_adapters.RecommendHome;

import interface_adapters.ViewModel;
import interface_adapters.signup.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecommendHomeViewModel extends ViewModel {
    public String username;
    private RecommendHomeState state = new RecommendHomeState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public RecommendHomeViewModel() {
        super("recommend home");
    }
    public void setState(RecommendHomeState state) {
        this.state = state;
    }
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public RecommendHomeState getState() {
        return state;
    }
}
