package main.tests.EnrolledTests;

import FirebaseDataAccess.FirebaseDataAccess;
import use_case.Enrolled.EnrolledInputData;
import use_case.Enrolled.EnrolledInteractor;
import interface_adapters.Enrolled.EnrolledPresenter;
import interface_adapters.Enrolled.EnrolledViewModel;
import interface_adapters.ViewModelManager;
import use_case.Enrolled.EnrolledOutputData;

public class EnrolledInteractorTest {

    public FirebaseDataAccess firebaseDataAccess;
    public EnrolledPresenter enrolledPresenter;
    public EnrolledViewModel enrolledViewModel;

    public ViewModelManager viewModelManager;
    public EnrolledInteractor enrolledInteractor;

    public EnrolledInteractorTest(){
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
