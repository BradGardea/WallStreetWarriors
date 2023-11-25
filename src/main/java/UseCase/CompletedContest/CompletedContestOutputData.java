package UseCase.CompletedContest;

import com.google.type.DateTime;
import entity.IContest;

import java.util.ArrayList;
import java.util.HashMap;

public class CompletedContestOutputData {

    public String contestName;

    public DateTime startTime;

    public String industry;

    public DateTime endTime;

    public HashMap<String, String[]> portfolio;

    public ArrayList<String> leaderboard;

    public String profit;

    public String placement;

    public CompletedContestOutputData(IContest entity, HashMap<String, String[]> portfolio, ArrayList<String> leaderboard, String profit, String placement) {
        this.contestName = entity.getTitle();
        this.startTime = entity.getStartTime();
        this.industry = entity.getContestIndustry();
        this.endTime = entity.getEndTime();
        this.portfolio = portfolio;
        this.leaderboard = leaderboard;
        this.profit = profit;
        this.placement = placement;
    }

}
