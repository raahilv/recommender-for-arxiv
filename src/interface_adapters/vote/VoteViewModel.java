package interface_adapters.vote;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class VoteViewModel extends ViewModel {
    public final String TITLE_LABEL = "Vote View";
    public static final String UPVOTE_BUTTON_LABEL = "Upvote";
    public static final String DOWNVOTE_BUTTON_LABEL = "Downvote";

    private VoteState state = new VoteState();

    /**
     * Constructor for view model for Vote use case, set viewName to "vote".
     */
    public VoteViewModel() {
        super("vote");
    }

    /**
     * Set the Vote state in the view model.
     * @param state the Vote state to be stored in the view model
     */
    public void setState(VoteState state) {
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
     * Get the Vote state stored in the view model.
     * @return the Vote state stored in the view model
     */
    public VoteState getState() {
        return state;
    }
}
