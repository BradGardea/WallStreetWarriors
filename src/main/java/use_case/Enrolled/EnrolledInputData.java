package use_case.Enrolled;

/**
 * Houses the UUID of a certain selected StockContest object from the user's page view.
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledInputData {
    final private String username;
    final private String contestId;

    public EnrolledInputData(String username, String contestId) {
        this.username = username;
        this.contestId = contestId;
    }

    String getUsername() {return username;}
    String getContestId() {return contestId;}
}
