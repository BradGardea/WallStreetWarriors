package interface_adapters.AvailableContests;

import entity.AvailableContest;

public class AvailableContestState {
    private AvailableContest contestDetails;
    public AvailableContestState(AvailableContest contestDetails){
        this.contestDetails = contestDetails;
    }
    public AvailableContest getContestDetails() {
        return contestDetails;
    }
    public void setContestDetails(AvailableContest contestDetails) {
        this.contestDetails = contestDetails;
    }

}
