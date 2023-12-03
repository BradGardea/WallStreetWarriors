package EnrolledContestTests;

import FirebaseDataAccess.FirebaseDataAccess;
import UseCase.EnrolledContest.EnrolledInputData;
import UseCase.EnrolledContest.EnrolledInteractor;
import InterfaceAdapters.Enrolled.EnrolledPresenter;
import InterfaceAdapters.Enrolled.EnrolledViewModel;
import InterfaceAdapters.ViewModelManager;
import UseCase.EnrolledContest.EnrolledOutputData;
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
}
