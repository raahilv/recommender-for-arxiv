package interface_adapters.showAuthor;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;;

import java.beans.PropertyChangeSupport;

public class showAuthorViewModel extends ViewModel {

    private showAuthorState state = new showAuthorState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public showAuthorViewModel() {super("Show Author Details");}
    public void setState(showAuthorState state) {this.state = state;}

    //TODO: set a better propertyName
    @Override
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public showAuthorState getState() {
        return state;
    }
}
