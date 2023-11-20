package interface_adapters.CompletedContests;

import com.google.type.DateTime;
import interface_adapters.ViewModel;
import view.CompletedContests.CompletedContestView;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CompletedContestViewModel extends ViewModel {

    public static String contestName;

    public static DateTime startDate;

    public static String industry;

    public static DateTime endDate;

    public static final String YOUR_PORTFOLIO = "Your Portfolio";

    public static final String LEADERBOARD_LABEL = "Leaderboard";

    public static Object[][] portfolio;

    public static String[] leaderboard;

    public static String profit;

    public static String placement;

    private CompletedContestState state = new CompletedContestState();

    public CompletedContestViewModel(){
        super("completed contest view");
    }

    public void setState(CompletedContestState completedContestState) {
        this.state = completedContestState;
    }

    public CompletedContestState getState(){
        return this.state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
