package useCase.HomePage;

import entity.Contest;

import java.util.ArrayList;

public class HomePageOutputData {

    public String username;

    public ArrayList<Contest> enrolledContests;

    public ArrayList<Contest> completedContests;

    public ArrayList<Contest> availableContests;


    public HomePageOutputData(String username, ArrayList<Contest> enrolledContests, ArrayList<Contest> completedContests, ArrayList<Contest> availableContests) {
        this.username = username;
        this.enrolledContests = enrolledContests;
        this.completedContests = completedContests;
        this.availableContests = availableContests;

    }

    public String getUsername() {
        return username;
    }

}
