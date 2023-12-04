package interfaceAdapters.HomePage;

import entity.Contest;
import interfaceAdapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class HomePageViewModel extends ViewModel {
    public static String username;
    public static ArrayList<Contest> enrolledContests;
    public static ArrayList<Contest> completedContests;
    public static ArrayList<Contest> availableContests;
    public final String TITLE_LABEL = "Home Page View";
    public final String LOGOUT_BUTTON_LABEL = "Log Out";
    private HomePageState state = new HomePageState();

    public HomePageViewModel() {
        super("home page view");
    }

    public void setState(HomePageState state) {
        this.state = state;
    }

    public HomePageState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
        username = state.username;
        enrolledContests = state.enrolledContests;
        completedContests = state.completedContests;
        availableContests = state.availableContests;
    }
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
