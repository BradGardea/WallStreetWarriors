package interface_adapters.CompletedContests;

import com.google.cloud.Timestamp;
import com.google.type.DateTime;
import interface_adapters.ViewModel;
import view.CompletedContests.CompletedContestView;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;

public class CompletedContestViewModel extends ViewModel {

    public static String contestName;

    public static Timestamp startDate;

    public static String industry;

    public static Timestamp endDate;

    public static final String YOUR_PORTFOLIO = "Your Portfolio";

    public static final String LEADERBOARD_LABEL = "Leaderboard";

    public static HashMap<String, List<Object>> portfolio;

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


    // trying to set the fields of the view model
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
        contestName = state.contestName;
        startDate = state.startDate;
        endDate = state.endDate;
        industry = state.industry;
        portfolio = state.portfolio;
        leaderboard = state.leaderboard.toArray(new String[0]);
        profit = state.profit;
        placement = state.placement;

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
