package interface_adapters.CompletedContests;

import com.google.type.DateTime;
import view.CompletedContests.CompletedContestView;

import javax.swing.*;

public class CompletedContestViewModel {

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




    public void addPropertyChangeListener(CompletedContestView completedContestsView) {
    }
}
