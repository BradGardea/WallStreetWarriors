package useCase.AvailableContest;

public interface AvailableContestOutputBoundary {
    void prepareSuccess(AvailableContestOutputData finalizedOutputData);
    void prepareFail();
}
