package interface_adapters.library;

import interface_adapters.RecommendHome.RecommendHomeState;
import interface_adapters.ViewModel;
import interface_adapters.login.LoginState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class LibraryViewModel extends ViewModel {

    private LibraryState state = new LibraryState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LibraryViewModel() {
        super("library view");
    }
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    public void setState(LibraryState state) {
        this.state = state;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LibraryState getState() {
        return state;
    }


}
