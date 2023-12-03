package entity;

import FirebaseDataAccess.*;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class User implements IUser, IUpdateableEntity {
    private String username;
    private String password;
    private ArrayList<String> completedContests = new ArrayList<String>();
    private ArrayList<String> enrolledContests = new ArrayList<String>();

    public User(){};

    public User(String username, String password, ArrayList<String> completedContests, ArrayList<String> enrolledContests){
        this.username = username;
        this.password = password;
        this.enrolledContests = enrolledContests;
        this.completedContests = completedContests;
        updateInStorage();
    }
    public void updateInStorage(){
        FirebaseDataAccess.getInstance().setOrUpdateEntity(this, "Users", this.username);
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public ArrayList<String> getCompletedContests() {
        return completedContests;
    }

    public void setCompletedContests(ArrayList<String> completedContests) {
        this.completedContests = completedContests;
    }
    public ArrayList<String> getEnrolledContests() {
        return enrolledContests;
    }
    public void addCompletedContest(String completedContest){
        this.completedContests.add(completedContest);
    }

    public void setEnrolledContests(ArrayList<String> enrolledContests) {
        this.enrolledContests = enrolledContests;
    }
    public void addEnrolledContest(String enrolledContest){
        this.enrolledContests.add(enrolledContest);
    }

//    @Override
//    public String getUUID() {
//        return this.id;
//    }
//    public void setUUID(String id){
//        this.id = id;
//        updateInStorage();
//    }

//    public ArrayList<Contest> getCompletedContests() {
//        ArrayList<Contest> completedConts = new ArrayList<>();
//        var dataAccess = FirebaseDataAccess.getInstance();
//        for (var contestId: completedContests) {
//            completedConts.add(dataAccess.getEntity(Contest.class, "Contests", contestId));
//        }
//        return completedConts;
//    }
//
//    public ArrayList<Contest> getEnrolledContests(){
//        ArrayList<Contest> enrolledConts = new ArrayList<>();
//        var dataAccess = FirebaseDataAccess.getInstance();
//        for (var contestId: enrolledContests){
//            enrolledConts.add(dataAccess.getEntity(Contest.class, "Contests", contestId));
//        }
//        return enrolledConts;
//    }
}
