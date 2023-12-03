package InterfaceAdapters.AvailableContests;

import entity.Contest;

public class AvailableContestState {
    private Contest contestDetails;
    public AvailableContestState(Contest contestDetails){
        this.contestDetails = contestDetails;
    }
    public Contest getContestDetails() {
        return contestDetails;
    }
    public void setContestDetails(Contest contestDetails) {
        this.contestDetails = contestDetails;
    }

}
