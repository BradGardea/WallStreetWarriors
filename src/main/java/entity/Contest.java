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

    private ArrayList<String> members;

    private String industry;

    private Timestamp startTime;

    private Timestamp endTime;

    // Portfolio: {username: {Ticker: {data}}}
    private HashMap<String, HashMap<String, List<Object>>> portfolios;


    @Exclude private ArrayList<User> concreteMembers;
    public Contest(String contestId, String title, String description, ArrayList<String> members,
                   String industry, Timestamp startTime, Timestamp endTime, HashMap<String, HashMap<String, List<Object>>> portfolios){

        this.contestId = contestId;
        this.title = title;
        this.description = description;
        this.members = members;
        this.industry = industry;
        this.startTime = startTime;
        this.endTime = endTime;
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
        for(var userId: this.members){
            users.add(dataAccess.getEntity(User.class, "Users", userId));
        }
        return users;
    }

    public void setMembers(ArrayList<String> members){
        this.members = members;
        this.concreteMembers = getMembers();
    }
    public ArrayList<User> getConcreteMembers(){
        return this.concreteMembers;
    }

    @Override
    public String getContestId() {
        return contestId;
    }

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

    public HashMap<String, HashMap<String, List<Object>>> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(HashMap<String, HashMap<String, List<Object>>> portfolios) {
        this.portfolios = portfolios;
    }


    //TODO: Implement Method Later when API call logic is finished
//    public User getWinner(){ return null; }


}
