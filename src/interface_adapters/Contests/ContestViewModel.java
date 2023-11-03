package interface_adapters.Contests;

import interface_adapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ContestViewModel extends ViewModel {

    public final String TITLE_LABEL = "contest view";

    private ContestState state = new ContestState();

    public ContestViewModel(){
        super("contest view");
    };

    public void setState(ContestState newState){
        this.state = newState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
