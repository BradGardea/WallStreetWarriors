package entity;

import FirebaseDataAccess.FirebaseDataAccess;
import FirebaseDataAccess.IFirebaseEntity;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@IgnoreExtraProperties
public abstract class Contest implements IContest {

    private String contestId;
    private String title;

    private String description;

    private ArrayList<String> members;

    private String industry;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Exclude private ArrayList<User> concreteMembers;

    public String getTitle(){ return this.title; }

    public String getDescription(){ return this.description; }

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

    public String getContestId(){ return this.contestId; }

    public String getContestIndustry(){ return this.industry; }

    public ArrayList<User> getConcreteMembers(){
        return this.concreteMembers;
    }

    //TODO: Implement Method Later when API call logic is finished
    public User getWinner(){ return null; }

    public Contest(String contestId, String title, String description, ArrayList<String> members,
                   String industry, LocalDateTime startTime, LocalDateTime endTime){
        this.contestId = contestId;
        this.title = title;
        this.description = description;
        this.members = members;
        this.industry = industry;
        this.startTime = startTime;
        this.endTime = endTime;

    }

}
