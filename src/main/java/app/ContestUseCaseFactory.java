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
import interface_adapters.Enrolled.EnrolledController;
import interface_adapters.Enrolled.EnrolledPresenter;
import interface_adapters.Enrolled.EnrolledViewModel;
import interface_adapters.ViewModelManager;
import use_case.Enrolled.EnrolledInputData;
import use_case.Enrolled.EnrolledInteractor;
import use_case.Enrolled.EnrolledOutputBoundary;
import view.AvailableContests.AvailableContestDetailView;
import view.CompletedContests.CompletedContestView;
import view.ContestView;
import view.EnrolledView;

public class ContestUseCaseFactory {

    // Prevents Instantiation
    private ContestUseCaseFactory(){};

    public static ContestView create(ContestViewModel contestViewModel){
        return new ContestView(contestViewModel);
    }

    public static CompletedContestView createCompletedContestView(CompletedContestViewModel completedContestViewModel, FirebaseDataAccess dataAccessInterface, ViewModelManager viewModelManager, String contestId, String username){
        CompletedContestController completedContestController = createCompletedContestUseCase(completedContestViewModel, viewModelManager, contestId, dataAccessInterface);
        return new CompletedContestView(completedContestController, completedContestViewModel);

    }



    private static CompletedContestController createCompletedContestUseCase(CompletedContestViewModel completedContestViewModel, ViewModelManager viewModelManager, String contestId, FirebaseDataAccess dataAccessInterface){
        CompletedContestOutputBoundary completedContestOutputBoundary = new CompletedContestPresenter(completedContestViewModel, viewModelManager);
        // TODO: remove this hardcoded username later when Goncalo's code is merged in.
        String username = "dhruv";
        CompletedContestInteractor completedContestInteractor = new CompletedContestInteractor(dataAccessInterface, completedContestOutputBoundary, contestId, username);

        // TODO: remove this method call once we have main view for calling the method.
        completedContestInteractor.execute();
        return new CompletedContestController(completedContestInteractor);
    }

    public static EnrolledView createEnrolledView(EnrolledViewModel enrolledViewModel, FirebaseDataAccess firebaseDataAccess, ViewModelManager viewModelManager, String contestId, String username) {
        EnrolledController enrolledController = createEnrolledUseCase(enrolledViewModel, viewModelManager, contestId, firebaseDataAccess);
        return new EnrolledView(enrolledController, enrolledViewModel);
    }

    private static EnrolledController createEnrolledUseCase(EnrolledViewModel enrolledViewModel, ViewModelManager viewModelManager, String contestId, FirebaseDataAccess dataAccessInterface){
        EnrolledOutputBoundary enrolledOutputBoundary = new EnrolledPresenter(enrolledViewModel, viewModelManager);
        String username = "dhruvpatt";
        EnrolledInputData inputData = new EnrolledInputData(username, contestId);
        EnrolledInteractor enrolledInteractor = new EnrolledInteractor(dataAccessInterface, enrolledOutputBoundary);

        enrolledInteractor.execute(inputData);
        return new EnrolledController(enrolledInteractor);
    }
}
