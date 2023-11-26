package UseCase.CompletedContest;

import com.google.cloud.Timestamp;
import entity.IContest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompletedContestOutputData {

    public String contestName;

    public Timestamp startTime;

    public String industry;

    public Timestamp endTime;

    public HashMap<String, List<Object>> portfolio;

    public ArrayList<String> leaderboard;

    public String profit;

    public String placement;

    public CompletedContestOutputData(IContest entity, HashMap<String, List<Object>> portfolio, ArrayList<String> leaderboard, String profit, String placement) {
        this.contestName = entity.getTitle();
        this.startTime = entity.getStartTime();
        this.industry = entity.getIndustry();
        this.endTime = entity.getEndTime();
        this.portfolio = portfolio;
        this.leaderboard = leaderboard;
        this.profit = profit;
        this.placement = placement;
    }

}
