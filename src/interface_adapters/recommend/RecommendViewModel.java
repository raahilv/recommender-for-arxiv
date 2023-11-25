package interface_adapters.recommend;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecommendViewModel extends ViewModel {
    public static final String RECOMMEND_BUTTON_LABEL = "Recommend";
    public static final String TITLE_LABEL = "Recommend View";

    private RecommendState state = new RecommendState();

    public RecommendViewModel() {
        super("recommend");
    }

    public void setState(RecommendState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RecommendState getState() {
        return state;
    }

}
