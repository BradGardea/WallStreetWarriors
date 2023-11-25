package entity;

import FirebaseDataAccess.FirebaseDataAccess;
import FirebaseDataAccess.IFirebaseEntity;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.type.DateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@IgnoreExtraProperties
public abstract class Contest implements IContest, IFirebaseEntity{

    private String contestId;
    private String title;

    private String description;

    private ArrayList<String> members;

    private String industry;

    private DateTime startTime;

    private DateTime endTime;

    // Portfolio: {username: {Ticker: {data}}}
    private HashMap<String, HashMap<String, String[]>> portfolios;


    @Exclude private ArrayList<User> concreteMembers;
    public Contest(String contestId, String title, String description, ArrayList<String> members,
                   String industry, DateTime startTime, DateTime endTime, HashMap<String, HashMap<String, String[]>> portfolios){

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
    public void updateInFirebase(){
        FirebaseDataAccess.getInstance().setOrUpdateEntity(this, "Contests", this.contestId);
    }

    public String getTitle(){ return this.title; }
    public void setTitle(String title){
        this.title = title;
        updateInFirebase();
    }

    public String getDescription(){ return this.description; }
    public void setDescription(String desc){
        this.description = desc;
        updateInFirebase();
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
        updateInFirebase();
    }
    public ArrayList<User> getConcreteMembers(){
        return this.concreteMembers;
    }

    public String getContestId(){ return this.contestId; }
    public void setContestId(String id){
        this.contestId = id;
        updateInFirebase();
    }

    public String getContestIndustry(){ return this.industry; }
    public void setIndustry(String ind){
        this.industry = ind;
        updateInFirebase();
    }

    public HashMap<String, HashMap<String, String[]>> getPortfolios(){
        return this.portfolios;
    }

    public DateTime getStartTime(){
        return this.startTime;
    }

    public DateTime getEndTime(){
        return this.endTime;
    }

    //TODO: Implement Method Later when API call logic is finished
    public User getWinner(){ return null; }


}
