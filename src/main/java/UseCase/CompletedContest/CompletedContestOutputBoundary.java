package UseCase.CompletedContest;

public interface CompletedContestOutputBoundary {

    void prepareSuccessView(CompletedContestOutputData data);

    void prepareFailView();
}
