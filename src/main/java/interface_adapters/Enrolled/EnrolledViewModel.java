package main.java.interface_adapters.Enrolled;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 *
 *
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledViewModel extends interface_adapters.ViewModel {
    private EnrolledState state = new EnrolledState();

    public EnrolledViewModel() {super("enrolledcontest");}

    public void setState(EnrolledState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EnrolledState getState() {return state;}
}
