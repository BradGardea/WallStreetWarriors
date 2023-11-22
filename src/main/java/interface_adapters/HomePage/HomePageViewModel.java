package interface_adapters.HomePage;

import interface_adapters.ViewModel;
import view.HomePage.HomePageView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomePageViewModel extends ViewModel {
    public final String TITLE_LABEL = "Home Page View";
    public final String SIGNOUT_BUTTON_LABEL = "Sign Out";
    private HomePageState state = new HomePageState();

    public HomePageViewModel() {
        super("home page view");
    }

    public void setState(HomePageState state) {
        this.state = state;
    }
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public HomePageState getState() {
        return state;
    }
}
