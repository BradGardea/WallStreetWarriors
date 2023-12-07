package useCase.EnrolledContest;

/**
 * Houses the ID of a certain selected Contest object from the user's homepage view.
 *
 * @author Mikhail Skazhenyuk
 * @version 1.0
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
