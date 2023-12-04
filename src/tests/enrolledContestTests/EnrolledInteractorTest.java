package enrolledContestTests;

import firebaseDataAccess.FirebaseDataAccess;
import useCase.EnrolledContest.EnrolledInputData;
import useCase.EnrolledContest.EnrolledInteractor;
import interfaceAdapters.Enrolled.EnrolledPresenter;
import interfaceAdapters.Enrolled.EnrolledViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.EnrolledContest.EnrolledOutputData;
import app.Main;

import java.io.IOException;

public class EnrolledInteractorTest {

    public FirebaseDataAccess firebaseDataAccess;
    public EnrolledPresenter enrolledPresenter;
    public EnrolledViewModel enrolledViewModel;

    public ViewModelManager viewModelManager;
    public EnrolledInteractor enrolledInteractor;

    public EnrolledInteractorTest() throws IOException {
        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.enrolledViewModel = new EnrolledViewModel();
        this.viewModelManager = new ViewModelManager();
        this.enrolledPresenter = new EnrolledPresenter(this.enrolledViewModel, this.viewModelManager);
        // hardcoded values for test purposes
        this.enrolledInteractor = new EnrolledInteractor(this.firebaseDataAccess, this.enrolledPresenter);    }

    @org.junit.Test
    public void retrieveDataTest(){
        EnrolledInputData enrolledInputData = new EnrolledInputData("a", "EnrolledTest");
        EnrolledOutputData enrolledOutputData = this.enrolledInteractor.retrieveData(enrolledInputData);
        assert(enrolledOutputData.getTitle().equals("Test"));
        assert(enrolledOutputData.getDescription().equals("EnrolledTest"));
    }

    @org.junit.Test
    public void testExecute() {
        EnrolledInputData enrolledInputData = new EnrolledInputData("a", "EnrolledTest");
        this.enrolledInteractor.execute(enrolledInputData);
        assert(this.enrolledViewModel.getState() != null);

    }

    @org.junit.Test
    public void testMarkContestCompleted() {
        EnrolledInputData enrolledInputData = new EnrolledInputData("a", "EnrolledTest");
        this.enrolledInteractor.execute(enrolledInputData);
        assert(this.enrolledInteractor.markContestCompleted());
    }

    @org.junit.Test
    public void testMarkContestCompletedFail() {
        assert(!this.enrolledInteractor.markContestCompleted());
    }
}
