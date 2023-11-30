package UseCase.AvailableContest;

import entity.Contest;

public interface AvailableContestOuputBoundary {
    void prepareSuccess(Contest finalizedContest);
    void prepareFail();
}
