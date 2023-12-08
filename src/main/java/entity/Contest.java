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

    /**
     * Constructs a new Contest instance with specified details.
     *
     * This constructor initializes a Contest object with a unique identifier, title, description,
     * a list of members (users), industry category, start and end times, a list of stock options,
     * and a nested HashMap representing portfolios for each member.
     *
     * After initializing the Contest object, it immediately updates the data in Firebase.
     *
     * Parameters:
     * @param contestId Contest's Id.
     * @param title The title of the contest.
     * @param description Description of contest.
     * @param members An ArrayList of User objects participating in the contest.
     * @param industry The industry category to which the contest is related.
     * @param startTime The start time of the contest, represented as a Timestamp.
     * @param endTime The end time of the contest, also as a Timestamp.
     * @param stockOptions An ArrayList of stock tickers that are options in the contest.
     * @param portfolios A nested HashMap structure representing the portfolios of each user.
     *                   Each portfolio includes details such as ticker and its respective purchase price,
     *                   quantity, end price, and value.
     */
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