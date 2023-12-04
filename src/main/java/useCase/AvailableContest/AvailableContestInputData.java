package useCase.AvailableContest;

public class AvailableContestInputData {
    final private String username;
    final private String contestId;

    public AvailableContestInputData(String username, String contestId) {
        this.username = username;
        this.contestId = contestId;
    }

    String getUsername() {return username;}
    String getContestId() {return contestId;}
}
