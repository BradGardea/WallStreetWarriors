package entity;

import java.util.ArrayList;

public interface Contest {

    String getTitle();

    String getDescription();

    ArrayList<User> getMembers();

    String getContestId();

    String getContestIndustry();

    User getWinner();
}
