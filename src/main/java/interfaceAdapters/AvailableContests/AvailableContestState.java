package interfaceAdapters.AvailableContests;

import entity.Contest;
import useCase.AvailableContest.AvailableContestOutputData;

public class AvailableContestState {
    private AvailableContestOutputData contestDetails;
    public AvailableContestState(AvailableContestOutputData contestDetails){
        this.contestDetails = contestDetails;
    }
    public AvailableContestOutputData getContestDetails() {
        return contestDetails;
    }
    public void setContestDetails(AvailableContestOutputData contestDetails) {
        this.contestDetails = contestDetails;
    }

}
