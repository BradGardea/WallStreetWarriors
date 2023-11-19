package entity;

import FirebaseDataAccess.FirebaseDataAccess;
import FirebaseDataAccess.IFirebaseEntity;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@IgnoreExtraProperties
public abstract class Contest implements IContest, IFirebaseEntity{

    private String contestId;
    private String title;

    private String description;

    private ArrayList<String> members;

    private String industry;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Exclude private ArrayList<User> concreteMembers;
    public Contest(String contestId, String title, String description, ArrayList<String> members,
                   String industry, LocalDateTime startTime, LocalDateTime endTime){

        this.contestId = contestId;
        this.title = title;
        this.description = description;
        this.members = members;
        this.industry = industry;
        this.startTime = startTime;
        this.endTime = endTime;
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

    //TODO: Implement Method Later when API call logic is finished
    public User getWinner(){ return null; }

}
