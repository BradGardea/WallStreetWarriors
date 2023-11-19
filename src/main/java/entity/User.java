package entity;

import FirebaseDataAccess.FirebaseDataAccess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User implements IUser{

    private String id;
    private String username;
    private String password;
    private ArrayList<String> completedContests;

    private ArrayList<String> enrolledContests;
    private ArrayList<Contest> concreteCompletedContests;

    private ArrayList<Contest> concreteEnrolledContests;

    private ArrayList<String> contests;
    // TODO: For each setter, make it so it updates firebase aswell
    public User(String id, String username, String password, ArrayList<String> completedContests, ArrayList<String> enrolledContests){
        this.id = id;
        this.username = username;
        this.password = password;
        this.enrolledContests = enrolledContests;
        this.completedContests = completedContests;
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUUID() {
        return this.id;
    }

    public ArrayList<Contest> getCompletedContests() {
        ArrayList<Contest> completedConts = new ArrayList<>();
        var dataAccess = FirebaseDataAccess.getInstance();

        for (var contestId: completedContests) {
            completedConts.add(dataAccess.getEntity(CompletedContests.class, "Contests", contestId));

        }

        return completedConts;
    }

    public ArrayList<Contest> getEnrolledContests(){
        ArrayList<Contest> enrolledConts = new ArrayList<>();
        var dataAccess = FirebaseDataAccess.getInstance();

        for (var contestId: enrolledContests){
            enrolledConts.add(dataAccess.getEntity(EnrolledContests.class, "Contests", contestId));
        }

        return enrolledConts;
    }

    public void setEnrolledContests(ArrayList<String> enrolledContests){
        this.enrolledContests = enrolledContests;
        this.concreteEnrolledContests = getEnrolledContests();
    }

    public void setCompletedContests(ArrayList<String> completedContests){
        this.completedContests = completedContests;
        this.concreteCompletedContests = getCompletedContests();
    }
}
