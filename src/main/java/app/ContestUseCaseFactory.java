package app;

import FirebaseDataAccess.FirebaseDataAccess;
import UseCase.AvailableContest.AvailableContestInteractor;
import UseCase.AvailableContest.AvailableContestOuputBoundary;
import interface_adapters.AvailableContests.AvailableContestPresenter;
import UseCase.CompletedContest.CompletedContestInteractor;
import UseCase.CompletedContest.CompletedContestOutputBoundary;
import interface_adapters.AvailableContests.AvailableContestsController;
import interface_adapters.AvailableContests.AvailableContestsViewModel;
import interface_adapters.CompletedContests.CompletedContestController;
import interface_adapters.CompletedContests.CompletedContestPresenter;
import interface_adapters.CompletedContests.CompletedContestViewModel;
import interface_adapters.Contests.ContestViewModel;
import interface_adapters.ViewModelManager;
import view.AvailableContests.AvailableContestDetailView;
import view.CompletedContests.CompletedContestView;
import view.ContestView;

public class ContestUseCaseFactory {

    // Prevents Instantiation
    private ContestUseCaseFactory(){};

    public static ContestView create(ContestViewModel contestViewModel){
        return new ContestView(contestViewModel);
    }

    public static AvailableContestDetailView createAvailableContestDetailView(AvailableContestsViewModel availableContestsViewModel, ViewModelManager viewModelManager, String contestId, String username){
        AvailableContestsController availableContestsController = createAvailableContestUseCase(availableContestsViewModel, viewModelManager, contestId, username);
        return new AvailableContestDetailView(availableContestsController, availableContestsViewModel);
    }

    public static CompletedContestView createCompletedContestView(CompletedContestViewModel completedContestViewModel, FirebaseDataAccess dataAccessInterface, ViewModelManager viewModelManager, String contestId, String username){
        CompletedContestController completedContestController = createCompletedContestUseCase(completedContestViewModel, viewModelManager, contestId, dataAccessInterface);
        return new CompletedContestView(completedContestController, completedContestViewModel);

    }
    private static AvailableContestsController createAvailableContestUseCase(AvailableContestsViewModel availableContestsViewModel, ViewModelManager viewModelManager, String contestId, String username){
        AvailableContestOuputBoundary availableContestOuputBoundary = new AvailableContestPresenter(availableContestsViewModel, viewModelManager);
        AvailableContestInteractor availableContestInteractor = new AvailableContestInteractor(availableContestOuputBoundary, contestId, username);
        return new AvailableContestsController(availableContestInteractor);
    }

    private static CompletedContestController createCompletedContestUseCase(CompletedContestViewModel completedContestViewModel, ViewModelManager viewModelManager, String contestId, FirebaseDataAccess dataAccessInterface){
        CompletedContestOutputBoundary completedContestOutputBoundary = new CompletedContestPresenter(completedContestViewModel, viewModelManager);
        // TODO: remove this hardcoded username later when Goncalo's code is merged in.
        String username = "dhruvpatt";
        CompletedContestInteractor completedContestInteractor = new CompletedContestInteractor(dataAccessInterface, completedContestOutputBoundary, contestId, username);

        // TODO: remove this method call once we have main view for calling the method.
        completedContestInteractor.execute();
        return new CompletedContestController(completedContestInteractor);
    }

}
