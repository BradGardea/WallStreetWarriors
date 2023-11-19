package entity;

import java.util.ArrayList;

public interface IContest {

    String getTitle();

    String getDescription();

    ArrayList<IUser> getMembers();

    String getContestId();

    String getContestIndustry();

    IUser getWinner();
}
