package app;

import interface_adapters.CompletedContests.CompletedContestController;
import interface_adapters.CompletedContests.CompletedContestViewModel;
import interface_adapters.Contests.ContestViewModel;
import view.CompletedContests.CompletedContestView;
import view.ContestView;

public class ContestUseCaseFactory {

    // Prevents Instantiation
    private ContestUseCaseFactory(){};

    public static ContestView create(ContestViewModel contestViewModel){
        return new ContestView(contestViewModel);
    }

    public static CompletedContestView createCompletedContestView(CompletedContestViewModel completedContestViewModel, CompletedContestController completedContestController){
        return new CompletedContestView(completedContestController,completedContestViewModel);
    }

}
