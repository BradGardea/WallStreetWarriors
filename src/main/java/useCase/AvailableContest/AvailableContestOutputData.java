package useCase.AvailableContest;

import com.google.cloud.Timestamp;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class AvailableContestOutputData {

    private String contestId;
    private String title;
    private String description;
    private ArrayList<User> members;
    private String industry;
    private Timestamp startTime;
    private Timestamp endTime;
    private  ArrayList<String> stockOptions;
    private HashMap<String, HashMap<String, HashMap<String, String>>> portfolios;

    public AvailableContestOutputData(String contestId, String title, String description, ArrayList<User> members,
                   String industry, Timestamp startTime, Timestamp endTime, ArrayList<String> stockOptions, HashMap<String, HashMap<String, HashMap<String, String>>> portfolios){

        this.contestId = contestId;
        this.title = title;
        this.description = description;
        this.members = members;
        this.industry = industry;
        this.startTime = startTime;
        this.endTime = endTime;
        this.stockOptions = stockOptions;
        this.portfolios = portfolios;
    }

    public ArrayList<User> getMembers(){
        return this.members;
    }

    /**
     * @return stock options supported in contest of the following format:
     * StockTicker: {StockTickerMetadataName: StockTickerMetadata}
     */
    public ArrayList<String> getStockOptions() {
        return stockOptions;
    }

    /**
     * @return portfolio of following format:
     * Username/id : {StockTicker: {StockTickerMetadataName: StockTickerMetadata}}
     */
    public HashMap<String, HashMap<String, HashMap<String, String>>> getPortfolios() {
        return portfolios;
    }
    public String getContestId(){ return this.contestId; }
    public String getDescription() {
        return description;
    }
    public String getIndustry() {
        return industry;
    }
    public Timestamp getStartTime() {return this.startTime;}
    public Timestamp getEndTime() {
        return endTime;
    }

}
