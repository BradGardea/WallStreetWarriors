package entity;

import FirebaseDataAccess.FirebaseDataAccess;
import FirebaseDataAccess.IFirebaseEntity;

import com.google.cloud.Timestamp;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@IgnoreExtraProperties
public class Contest implements IContest, IFirebaseEntity{
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
        updateInFirebase();
    }

    // default constructor
    public Contest(){};
    public void updateInFirebase(){
        FirebaseDataAccess.getInstance().setOrUpdateEntity(this, "Contests", this.contestId);
    }

    public ArrayList<User> getMembers(){
        var users = new ArrayList<User>();
        var dataAccess = FirebaseDataAccess.getInstance();
        for(var user: this.members){
            users.add(dataAccess.getEntity(User.class, "Users", user.getUsername()));
        }
        return users;
    }

    public void setMembersString(ArrayList<String> members){
        if (this.members != null){
            this.members = getMembers();
        }
    }
    public void addMember(User member){
        this.members.add(member);
    }
//    public ArrayList<User> getConcreteMembers(){
//        return this.concreteMembers;
//    }

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
     * @param portfolios formatted as: Username/id : {StockTicker: {StockTickerMetadataName: StockTickerMetadata}}
     */
    public void setPortfolios(HashMap<String, HashMap<String, HashMap<String, String>>> portfolios) {
        this.portfolios = portfolios;
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
    //TODO: Implement Method Later when API call logic is finished
//    public User getWinner(){ return null; }


}
