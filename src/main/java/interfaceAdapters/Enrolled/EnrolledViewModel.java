package interfaceAdapters.Enrolled;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for the "Enrolled" view in an application, extending the generic ViewModel class.
 * This class manages the state and property changes for the view displaying enrolled contests.
 *
 * The EnrolledViewModel holds the state of the enrolled view, which includes various details
 * about the contests and user's engagement in them. It provides methods to set the state,
 * fire property changes, and add property change listeners to enable reactive updates in the UI.
 *
 * @author Mikhail Skazhenyuk
 * @version 1.0
 */
public class EnrolledViewModel extends interfaceAdapters.ViewModel {
    private EnrolledState state = new EnrolledState();

    /**
     * Constructs an EnrolledViewModel with a specific view name.
     */
    public EnrolledViewModel() {super("enrolledcontest");}

    public void setState(EnrolledState state) {this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Fires a property change event.
     * This method is used to notify all listeners that the state of this view model has changed,
     * prompting the view to update its UI accordingly.
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to this view model.
     * Listeners are notified whenever the state of this view model changes.
     *
     * @param listener The PropertyChangeListener to be added.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EnrolledState getState() {return state;}
}
