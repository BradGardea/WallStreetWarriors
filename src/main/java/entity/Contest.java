package entity;

import FirebaseDataAccess.FirebaseDataAccess;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Contest implements IContest {

    private String contestId;
    private String title;

    private String description;

    private ArrayList<String> members;

    private String industry;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    public String getTitle(){ return this.title; }

    public String getDescription(){ return this.description; }

    public ArrayList<IUser> getMembers(){
        var users = new ArrayList<IUser>();
        var dataAccess = FirebaseDataAccess.getInstance();
        for(var userId: this.members){
            users.add(dataAccess.getEntity(IUser.class, "users", userId));
        }
        return users;
    }

    public String getContestId(){ return this.contestId; }

    public String getContestIndustry(){ return this.industry; }



    //TODO: Implement Method Later when API call logic is finished
    public IUser getWinner(){ return null; }

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
