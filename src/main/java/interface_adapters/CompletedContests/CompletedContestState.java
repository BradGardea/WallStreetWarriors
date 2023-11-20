package interface_adapters.CompletedContests;

import com.google.type.DateTime;

public class CompletedContestState {

    public String contestName;

    public DateTime startDate;

    public String industry;

    public DateTime endDate;

    public Object[][] portfolio;

    public String[] leaderboard;

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

    public Object[][] getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Object[][] portfolio) {
        this.portfolio = portfolio;
    }

    public String[] getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(String[] leaderboard) {
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
