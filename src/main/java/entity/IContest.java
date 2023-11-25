package entity;

import java.util.ArrayList;
import com.google.type.DateTime;

public interface IContest {

    String getTitle();

    String getDescription();

    ArrayList<User> getMembers();

    String getContestId();

    String getContestIndustry();

    IUser getWinner();

    DateTime getStartTime();
    DateTime getEndTime();




}
