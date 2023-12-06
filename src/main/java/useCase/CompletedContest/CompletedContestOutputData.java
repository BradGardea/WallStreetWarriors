package useCase.CompletedContest;

import com.google.cloud.Timestamp;
import entity.IContest;

import java.util.ArrayList;
import java.util.HashMap;

public class CompletedContestOutputData {

    public String contestName;

    public Timestamp startTime;

    public String industry;

    public Timestamp endTime;

    public HashMap<String, HashMap<String, String>> portfolio;

    public ArrayList<String> leaderboard;

    public String portfolioValue;

    public String placement;

    public HashMap<String, HashMap<String, HashMap<String, String>>> portfolios;

    public CompletedContestOutputData(IContest entity, HashMap<String, HashMap<String, String>> portfolio, ArrayList<String> leaderboard, String portfolioValue, String placement, HashMap<String, HashMap<String, HashMap<String, String>>> portfolios) {
        this.contestName = entity.getTitle();
        this.startTime = entity.getStartTime();
        this.industry = entity.getIndustry();
        this.endTime = entity.getEndTime();
        this.portfolio = portfolio;
        this.leaderboard = leaderboard;
        this.portfolioValue = portfolioValue;
        this.placement = placement;
        this.portfolios = portfolios;

    }

    /**
     * @return stock options supported in contest of the following format:
     * StockTicker: {StockTickerMetadataName: StockTickerMetadata}
     */

    public String getIndustry() {
        return industry;
    }

}
