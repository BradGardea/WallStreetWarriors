package interfaceAdapters.CompletedContests;

import com.google.cloud.Timestamp;
import interfaceAdapters.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

public class CompletedContestViewModel extends ViewModel {

    public static String contestName;

    public static Timestamp startDate;

    public static String industry;

    public static Timestamp endDate;

    public static final String YOUR_PORTFOLIO = "Selected Portfolio";

    public static final String LEADERBOARD_LABEL = "Leaderboard";

    public static HashMap<String,  HashMap<String, String>> portfolio;

    public static String[] leaderboard;

    public static String portfolioValue;

    public static String placement;

    private CompletedContestState state = new CompletedContestState();

    public CompletedContestViewModel(){
        super("completed contest view");
    }


    /**
     * Sets the state of the contest to the specified completed contest state.
     *
     * @param  completedContestState  the completed contest state to set
     * @return                        void
     */
    public void setState(CompletedContestState completedContestState) {
        this.state = completedContestState;
    }

    /**
     * Returns the state of the completed contest.
     *
     * @return the state of the completed contest
     */
    public CompletedContestState getState(){
        return this.state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);

        // sets all the fields in the view model to the new ones in state.
//        contestName = state.contestName;
//        startDate = state.startDate;
//        endDate = state.endDate;
//        industry = state.industry;
//        portfolio = state.portfolio;
//        leaderboard = state.leaderboard.toArray(new String[0]);
//        profit = state.profit;
//        placement = state.placement;
        contestName = state.getContestName();
        startDate = state.getStartDate();
        endDate = state.getEndDate();
        industry = state.getIndustry();
        portfolio = state.getPortfolio();
        leaderboard = state.getLeaderboard().toArray(new String[0]);
        portfolioValue = state.getPortfolioValue();
        placement = state.getPlacement();

    }


    /**
     * Adds a property change listener to the object.
     *
     * @param  listener  the listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

}
