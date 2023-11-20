package UseCase.CompletedContest;

import com.google.type.DateTime;

public class CompletedContestOutputData {

    public String contestName;

    public DateTime startDate;

    public String industry;

    public DateTime endDate;

    public Object[][] portfolio;

    public String[] leaderboard;

    public String profit;

    public String placement;

    public CompletedContestOutputData(String contestName, DateTime startDate, String industry, DateTime endDate, Object[][] portfolio, String[] leaderboard, String profit, String placement) {
        this.contestName = contestName;
        this.startDate = startDate;
        this.industry = industry;
        this.endDate = endDate;
        this.portfolio = portfolio;
        this.leaderboard = leaderboard;
        this.profit = profit;
        this.placement = placement;
    }

}
