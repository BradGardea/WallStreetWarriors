package interfaceAdapters.CompletedContests;

import useCase.CompletedContest.CompletedContestInputBoundary;

public class CompletedContestController {

    public final CompletedContestInputBoundary completedContestUseCaseInteractor;

    public CompletedContestController(CompletedContestInputBoundary completedContestUseCaseInteractor){
        this.completedContestUseCaseInteractor = completedContestUseCaseInteractor;
    }


    public void execute(){
        completedContestUseCaseInteractor.execute();
    }

}
