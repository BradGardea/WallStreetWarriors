package completedContests;

import app.Main;
import firebaseDataAccess.FirebaseDataAccess;
import interfaceAdapters.CompletedContests.CompletedContestController;
import interfaceAdapters.CompletedContests.CompletedContestPresenter;
import interfaceAdapters.CompletedContests.CompletedContestViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.CompletedContest.CompletedContestInputData;
import useCase.CompletedContest.CompletedContestInteractor;

import java.io.IOException;

public class CompletedContestInterfaceAdaptersTests {

    public FirebaseDataAccess firebaseDataAccess;
    public CompletedContestPresenter completedContestPresenter;
    public CompletedContestViewModel completedContestViewModel;

    public ViewModelManager viewModelManager;
    public CompletedContestInteractor completedContestInteractor;

    public CompletedContestController completedContestController;

    public CompletedContestInterfaceAdaptersTests() throws IOException {
        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.completedContestViewModel = new CompletedContestViewModel();
        this.viewModelManager = new ViewModelManager();
        this.completedContestPresenter = new CompletedContestPresenter(this.completedContestViewModel, this.viewModelManager);
        // hardcoded values for test purposes
        var completedContestInputData = new CompletedContestInputData("a", "CompletedTest");
        this.completedContestInteractor = new CompletedContestInteractor(this.firebaseDataAccess, this.completedContestPresenter, completedContestInputData);

        this.completedContestController = new CompletedContestController(completedContestInteractor);

    }

    @org.junit.Test
    public void testControllerExecute(){
        this.completedContestController.execute();

    }



}
