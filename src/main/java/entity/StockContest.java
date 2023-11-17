package entity;

import FirebaseDataAccess.FirebaseDataAccess;
import FirebaseDataAccess.IFirebaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class StockContest implements Contest, IFirebaseEntity {

    private String contestId;
    private String title;

    private String description;

    private ArrayList<String> members;

    private String industry;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

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

    public String getContestId(){ return this.contestId; }

    public String getContestIndustry(){ return this.industry; }

    //TODO: Implement Method Later when API call logic is finished
    public User getWinner(){ return null; }

    public StockContest(String contestId, String title, String description, ArrayList<String> members,
                        String industry, LocalDateTime startTime, LocalDateTime endTime){
        this.contestId = contestId;
        this.title = title;
        this.description = description;
        this.members = members;
        this.industry = industry;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    @Override
    public void getDocument(String collection, String iD) {

    }

    @Override
    public void setOrUpdateDocument(String collection, String iD, IFirebaseEntity currObject) {

    }

    @Override
    public void deleteDocument(String collection, String iD) {

    }
}
