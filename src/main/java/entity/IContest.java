package entity;

import java.util.ArrayList;

import com.google.cloud.Timestamp;
import com.google.type.DateTime;

public interface IContest {

    String getTitle();

    String getDescription();

    ArrayList<User> getMembers();

    String getContestId();

    String getIndustry();

    // IUser getWinner();

    Timestamp getStartTime();
    Timestamp getEndTime();




}