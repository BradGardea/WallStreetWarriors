package app;

import firebaseDataAccess.FirebaseDataAccess;
import firebaseDataAccess.IDataAccess;
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
        return new CompletedContestView(completedContestController, completedContestViewModel, true);

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

        completedContestInteractor.execute();
        return new CompletedContestController(completedContestInteractor);
    }

    /**
     * Create a new EnrolledView object, also executes controller with given username and contestId.
     *
     * @param enrolledViewModel The enrolled view model needed for the view.
     * @param firebaseDataAccess Reference to singleton data access object.
     * @param viewModelManager Reference to view model manager object.
     * @param contestId The id of the selected contest user wishes to be displayed.
     * @param username The username of the user.
     * @return A newly initialized EnrolledView object.
     */
    public static EnrolledView createEnrolledView(EnrolledViewModel enrolledViewModel, IDataAccess firebaseDataAccess, ViewModelManager viewModelManager, String contestId, String username) {
        EnrolledController enrolledController = createEnrolledUseCase(enrolledViewModel, viewModelManager, firebaseDataAccess);
        enrolledController.execute(username, contestId);
        return new EnrolledView(enrolledController, enrolledViewModel);
    }

    private static EnrolledController createEnrolledUseCase(EnrolledViewModel enrolledViewModel, ViewModelManager viewModelManager, IDataAccess dataAccessInterface){
        EnrolledOutputBoundary enrolledOutputBoundary = new EnrolledPresenter(enrolledViewModel, viewModelManager);
        EnrolledInteractor enrolledInteractor = new EnrolledInteractor(dataAccessInterface, enrolledOutputBoundary);

        return new EnrolledController(enrolledInteractor);
    }
}
