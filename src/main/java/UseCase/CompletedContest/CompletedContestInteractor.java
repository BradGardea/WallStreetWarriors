package UseCase.CompletedContest;

public class CompletedContestInteractor implements CompletedContestInputBoundary{

    public final CompletedContestDataAccessInterface completedContestDataAccessObject;

    public final CompletedContestOutputBoundary completedContestPresenter;

    public CompletedContestInteractor(CompletedContestDataAccessInterface completedContestDataAccessObject, CompletedContestOutputBoundary completedContestPresenter) {
        this.completedContestDataAccessObject = completedContestDataAccessObject;
        this.completedContestPresenter = completedContestPresenter;
    }


    @Override
    public void execute() {

    }
}
