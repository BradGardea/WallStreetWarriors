package InterfaceAdapters.HomePage;

import entity.Contest;

import java.util.ArrayList;

public class HomePageState {

    public String username;

    public ArrayList<Contest> enrolledContests;

    public ArrayList<Contest> completedContests;

    public ArrayList<Contest> availableContests;

    public HomePageState(HomePageState copy){
        username = copy.username;
        enrolledContests = copy.enrolledContests;
        completedContests = copy.completedContests;
        availableContests = copy.availableContests;
    }

    // default constructor
    public HomePageState(){}

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Contest> getEnrolledContests() {
        return enrolledContests;
    }

    public void setEnrolledContests(ArrayList<Contest> enrolledContests) {
        this.enrolledContests = enrolledContests;
    }

    public ArrayList<Contest> getCompletedContests() {
        return completedContests;
    }


    public void setCompletedContests(ArrayList<Contest> completedContests) {
        this.completedContests = completedContests;
    }

    public ArrayList<Contest> getAvailableContests() {
        return availableContests;
    }
    public void setAvailableContests(ArrayList<Contest> availableContests) {
        this.availableContests = availableContests;
    }
}
