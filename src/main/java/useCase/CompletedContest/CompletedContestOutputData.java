package useCase.CompletedContest;

import com.google.cloud.Timestamp;
import entity.IContest;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class CompletedContestOutputData {

    public String contestName;

    public Timestamp startTime;

    public String industry;

    public Timestamp endTime;

    public HashMap<String, HashMap<String, String>> portfolio;

    public ArrayList<String> leaderboard;

    public String profit;

    public String placement;

    public CompletedContestOutputData(IContest entity, HashMap<String, HashMap<String, String>> portfolio, ArrayList<String> leaderboard, String profit, String placement) {
        this.contestName = entity.getTitle();
        this.startTime = entity.getStartTime();
        this.industry = entity.getIndustry();
        this.endTime = entity.getEndTime();
        this.portfolio = portfolio;
        this.leaderboard = leaderboard;
        this.profit = profit;
        this.placement = placement;
    }
<<<<<<< HEAD:src/main/java/UseCase/CompletedContest/CompletedContestOutputData.java
=======

    /**
     * @return stock options supported in contest of the following format:
     * StockTicker: {StockTickerMetadataName: StockTickerMetadata}
     */

    public String getIndustry() {
        return industry;
    }
    public Timestamp getStartTime() {return this.startTime;}
    public Timestamp getEndTime() {
        return endTime;
    }
    public String getContestName() {
        return contestName;
    }

    public HashMap<String, HashMap<String, String>> getPortfolio() {
        return portfolio;
    }

    public ArrayList<String> getLeaderboard() {
        return leaderboard;
    }

    public String getProfit() {
        return profit;
    }

    public String getPlacement() {
        return placement;
    }

>>>>>>> Integration:src/main/java/useCase/CompletedContest/CompletedContestOutputData.java
}
