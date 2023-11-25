package interface_adapters.CompletedContests;

import com.google.type.DateTime;

import java.util.ArrayList;
import java.util.HashMap;

public class CompletedContestState {

    public String contestName;

    public DateTime startDate;

    public String industry;

    public DateTime endDate;

    public HashMap<String, String[]> portfolio;

    public ArrayList<String> leaderboard;

    public String profit;

    public String placement;

    public CompletedContestState(CompletedContestState copy){
        contestName = copy.contestName;
        startDate = copy.startDate;
        industry = copy.industry;
        endDate = copy.endDate;
        portfolio = copy.portfolio;
        leaderboard = copy.leaderboard;
        profit = copy.profit;
        placement = copy.placement;
    }

    // default constructor
    public CompletedContestState(){}

    // getters and setters

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public HashMap<String, String[]> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(HashMap<String, String[]> portfolio) {
        this.portfolio = portfolio;
    }

    public ArrayList<String> getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(ArrayList<String> leaderboard) {
        this.leaderboard = leaderboard;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String placement) {
        this.placement = placement;
    }
}
