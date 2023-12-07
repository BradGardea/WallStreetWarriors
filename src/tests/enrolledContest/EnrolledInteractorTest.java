package enrolledContest;

import firebaseDataAccess.FirebaseDataAccess;
import useCase.EnrolledContest.EnrolledInputData;
import useCase.EnrolledContest.EnrolledInteractor;
import interfaceAdapters.Enrolled.EnrolledPresenter;
import interfaceAdapters.Enrolled.EnrolledViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.EnrolledContest.EnrolledOutputData;
import app.Main;

import java.io.IOException;

/**
 * Test class for the EnrolledInteractor.
 * This class provides unit tests for the EnrolledInteractor class, ensuring that the business logic
 * related to the enrolled contest feature functions correctly.
 * It tests data retrieval, execution of interactor logic, and the marking of contests as completed.
 */
public class EnrolledInteractorTest {

    public FirebaseDataAccess firebaseDataAccess;
    public EnrolledPresenter enrolledPresenter;
    public EnrolledViewModel enrolledViewModel;

    public ViewModelManager viewModelManager;
    public EnrolledInteractor enrolledInteractor;

    /**
     * Initializes necessary components for testing the EnrolledInteractor.
     * This includes setting up Firebase, initializing the view model, presenter, and the interactor itself.
     *
     * @throws IOException If an error occurs during initialization.
     */
    public EnrolledInteractorTest() throws IOException {
        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.enrolledViewModel = new EnrolledViewModel();
        this.viewModelManager = new ViewModelManager();
        this.enrolledPresenter = new EnrolledPresenter(this.enrolledViewModel, this.viewModelManager);
        this.enrolledInteractor = new EnrolledInteractor(this.firebaseDataAccess, this.enrolledPresenter);    }

    /**
     * Tests the data retrieval functionality of the EnrolledInteractor.
     * This test ensures that the interactor correctly retrieves and processes enrolled contest data.
     */
    @org.junit.Test
    public void retrieveDataTest(){
        EnrolledInputData enrolledInputData = new EnrolledInputData("a", "EnrolledTest");
        EnrolledOutputData enrolledOutputData = this.enrolledInteractor.retrieveData(enrolledInputData);
        assert(enrolledOutputData.getTitle().equals("Test"));
        assert(enrolledOutputData.getDescription().equals("EnrolledTest"));
        assert(enrolledOutputData.getContestId().equals("EnrolledTest"));
        assert(enrolledOutputData.getUsername().equals("a"));
        assert(enrolledOutputData.getStartDate() != null);
        assert(enrolledOutputData.getEndDate() != null);
        assert(enrolledOutputData.getOpponents() != null && !enrolledOutputData.getOpponents().isEmpty());
        assert(enrolledOutputData.getPortfolios() != null && !enrolledOutputData.getPortfolios().isEmpty());
    }

    /**
     * Tests the execute method of the EnrolledInteractor.
     * This test verifies that the execute method correctly processes the input data and updates the view model.
     */
    @org.junit.Test
    public void testExecute() {
        EnrolledInputData enrolledInputData = new EnrolledInputData("a", "EnrolledTest");
        this.enrolledInteractor.execute(enrolledInputData);
        assert(this.enrolledViewModel.getState() != null);

    }

    /**
     * Tests the functionality of marking a contest as completed in the EnrolledInteractor.
     * This test ensures that contests are correctly marked as completed when valid data is provided.
     */
    @org.junit.Test
    public void testMarkContestCompleted() {
        EnrolledInputData enrolledInputData = new EnrolledInputData("a", "EnrolledTest");
        this.enrolledInteractor.execute(enrolledInputData);
        assert(this.enrolledInteractor.markContestCompleted(enrolledInputData));
    }

    /**
     * Tests the failure case for marking a contest as completed in the EnrolledInteractor.
     * This test verifies that the method correctly handles invalid data and does not mark the contest as completed.
     */
    @org.junit.Test
    public void testMarkContestCompletedFail() {
        EnrolledInputData enrolledInputData = new EnrolledInputData("", "EnrolledTest");
        assert(!this.enrolledInteractor.markContestCompleted(enrolledInputData));
    }
}
