package interface_adapters.AvailableContests;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AvailableContestsViewModel extends ViewModel {

    private AvailableContestState state;

    public AvailableContestsViewModel() {
        super("availableContestView");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setState(AvailableContestState state) {
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
    public AvailableContestState getState(){
        return state;
    }
}
