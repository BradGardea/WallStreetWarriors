package entity;

import firebaseDataAccess.FirebaseDataAccess;
import firebaseDataAccess.IUpdateableEntity;

import com.google.cloud.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
public class Contest implements IContest, IUpdateableEntity {
    private String contestId;
    private String title;
    private String description;
    private ArrayList<User> members;
    private String industry;
    private Timestamp startTime;
    private Timestamp endTime;
    private  ArrayList<String> stockOptions;
    private HashMap<String, HashMap<String, HashMap<String, String>>> portfolios;

//    @Exclude
//    private ArrayList<User> concreteMembers;
    public Contest(String contestId, String title, String description, ArrayList<User> members,
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
        updateInStorage();
    }

    // default constructor
    public Contest(){};
    public void updateInStorage(){
        FirebaseDataAccess.getInstance().setOrUpdateEntity(this, "Contests", this.contestId);
    }
   
    public ArrayList<User> getMembers(){
        return this.members;
    }

    public void addMember(User member){
        this.members.add(member);
    }

    /**
     * @return stock options supported in contest of the following format:
     * StockTicker: {StockTickerMetadataName: StockTickerMetadata}
     */
    public ArrayList<String> getStockOptions() {
        return stockOptions;
    }
    public void setStockOptions(ArrayList<String> stockOptions) {
        this.stockOptions = stockOptions;
    }
    /**
     * @return portfolio of following format:
     * Username/id : {StockTicker: {StockTickerMetadataName: StockTickerMetadata}}
     */
    public HashMap<String, HashMap<String, HashMap<String, String>>> getPortfolios() {
        return portfolios;
    }

    /**
     * Username/id : {StockTicker: {StockTickerMetadataName: StockTickerMetadata}}
     * @param username username of portfolio holder
     * @param portfolio portfolio data
     */
    public void setPortfolios(String username, HashMap<String, HashMap<String, String>> portfolio) {
        if (this.portfolios == null){
           this.portfolios = new HashMap<>();
        }
        this.portfolios.put(username, portfolio);
    }

    public String getContestId(){ return this.contestId; }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Override
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
}