package app;

import firebaseDataAccess.FirebaseDataAccess;
import useCase.AvailableContest.AvailableContestInputData;
import useCase.AvailableContest.AvailableContestInteractor;
import useCase.AvailableContest.AvailableContestOutputBoundary;
import interfaceAdapters.AvailableContests.AvailableContestPresenter;
import useCase.CompletedContest.CompletedContestInputData;
import useCase.CompletedContest.CompletedContestInteractor;
import useCase.CompletedContest.CompletedContestOutputBoundary;
import interfaceAdapters.AvailableContests.AvailableContestsController;
import interfaceAdapters.AvailableContests.AvailableContestsViewModel;
import interfaceAdapters.CompletedContests.CompletedContestController;
import interfaceAdapters.CompletedContests.CompletedContestPresenter;
import interfaceAdapters.CompletedContests.CompletedContestViewModel;
import interfaceAdapters.Enrolled.EnrolledController;
import interfaceAdapters.Enrolled.EnrolledPresenter;
import interfaceAdapters.Enrolled.EnrolledViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.EnrolledContest.EnrolledInputData;
import useCase.EnrolledContest.EnrolledInteractor;
import useCase.EnrolledContest.EnrolledOutputBoundary;
import view.AvailableContests.AvailableContestDetailView;
import view.CompletedContests.CompletedContestView;
import view.EnrolledContest.EnrolledView;

public class ContestUseCaseFactory {

    // Prevents Instantiation
    private ContestUseCaseFactory(){};


    public static AvailableContestDetailView createAvailableContestDetailView(AvailableContestsViewModel availableContestsViewModel, ViewModelManager viewModelManager, String contestId, String username){
        AvailableContestsController availableContestsController = createAvailableContestUseCase(availableContestsViewModel, viewModelManager, contestId, username);
        return new AvailableContestDetailView(availableContestsController, availableContestsViewModel, true);
    }
    public static CompletedContestView createCompletedContestView(CompletedContestViewModel completedContestViewModel, FirebaseDataAccess dataAccessInterface, ViewModelManager viewModelManager, String contestId, String username){
        CompletedContestController completedContestController = createCompletedContestUseCase(completedContestViewModel, viewModelManager, contestId, dataAccessInterface, username);
        return new CompletedContestView(completedContestController, completedContestViewModel);

    }
    private static AvailableContestsController createAvailableContestUseCase(AvailableContestsViewModel availableContestsViewModel, ViewModelManager viewModelManager, String contestId, String username){
        AvailableContestOutputBoundary availableContestOutputBoundary = new AvailableContestPresenter(availableContestsViewModel, viewModelManager);
        AvailableContestInputData availableContestInputData = new AvailableContestInputData(username, contestId);
        AvailableContestInteractor availableContestInteractor = new AvailableContestInteractor(availableContestOutputBoundary, availableContestInputData);
        return new AvailableContestsController(availableContestInteractor);
    }

    private static CompletedContestController createCompletedContestUseCase(CompletedContestViewModel completedContestViewModel, ViewModelManager viewModelManager, String contestId, FirebaseDataAccess dataAccessInterface, String username){
        CompletedContestOutputBoundary completedContestOutputBoundary = new CompletedContestPresenter(completedContestViewModel, viewModelManager);
        CompletedContestInputData completedContestInputData = new CompletedContestInputData(username, contestId);
        CompletedContestInteractor completedContestInteractor = new CompletedContestInteractor(dataAccessInterface, completedContestOutputBoundary, completedContestInputData);

        // TODO: remove this method call once we have main view for calling the method.
        completedContestInteractor.execute();
        return new CompletedContestController(completedContestInteractor);
    }

    public static EnrolledView createEnrolledView(EnrolledViewModel enrolledViewModel, FirebaseDataAccess firebaseDataAccess, ViewModelManager viewModelManager, String contestId, String username) {
        EnrolledController enrolledController = createEnrolledUseCase(enrolledViewModel, viewModelManager, contestId, firebaseDataAccess, username);
        enrolledController.execute(username, contestId);
        return new EnrolledView(enrolledController, enrolledViewModel);
    }

    private static EnrolledController createEnrolledUseCase(EnrolledViewModel enrolledViewModel, ViewModelManager viewModelManager, String contestId, FirebaseDataAccess dataAccessInterface, String username){
        EnrolledOutputBoundary enrolledOutputBoundary = new EnrolledPresenter(enrolledViewModel, viewModelManager);
        EnrolledInteractor enrolledInteractor = new EnrolledInteractor(dataAccessInterface, enrolledOutputBoundary);

        return new EnrolledController(enrolledInteractor);
    }
}
