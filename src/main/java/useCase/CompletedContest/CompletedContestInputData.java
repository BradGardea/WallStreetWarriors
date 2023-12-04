package useCase.CompletedContest;

public class CompletedContestInputData {
    final private String username;
    final private String contestId;

    public CompletedContestInputData(String username, String contestId) {
        this.username = username;
        this.contestId = contestId;
    }

    String getUsername() {return username;}
    String getContestId() {return contestId;}
}
