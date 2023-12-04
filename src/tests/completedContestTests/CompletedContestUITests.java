package completedContestTests;

import app.ContestUseCaseFactory;
import app.Main;
import firebaseDataAccess.FirebaseDataAccess;
import interfaceAdapters.CompletedContests.CompletedContestController;
import interfaceAdapters.CompletedContests.CompletedContestPresenter;
import interfaceAdapters.CompletedContests.CompletedContestViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.CompletedContest.CompletedContestInputData;
import useCase.CompletedContest.CompletedContestInteractor;
import view.CompletedContests.CompletedContestView;

import java.io.IOException;
import java.util.Objects;

public class CompletedContestUITests {

    public FirebaseDataAccess firebaseDataAccess;
    public CompletedContestPresenter completedContestPresenter;
    public CompletedContestViewModel completedContestViewModel;

    public ViewModelManager viewModelManager;
    public CompletedContestInteractor completedContestInteractor;

    public CompletedContestController completedContestController;

    public CompletedContestUITests() throws IOException {
        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.completedContestViewModel = new CompletedContestViewModel();
        this.viewModelManager = new ViewModelManager();
        this.completedContestPresenter = new CompletedContestPresenter(this.completedContestViewModel, this.viewModelManager);
        // hardcoded values for test purposes
        var completedContestInputData = new CompletedContestInputData("a", "CompletedTest");
        this.completedContestInteractor = new CompletedContestInteractor(this.firebaseDataAccess, this.completedContestPresenter, completedContestInputData);
    }

    @org.junit.Test
    public void construct(){
        CompletedContestView completedContestView = ContestUseCaseFactory.createCompletedContestView(completedContestViewModel,firebaseDataAccess, viewModelManager, "CompletedTest", "a");
        assert(completedContestView != null);
    }

    @org.junit.Test
    public void launch(){
        try {
            CompletedContestView completedContestView = ContestUseCaseFactory.createCompletedContestView(completedContestViewModel, firebaseDataAccess, viewModelManager, "CompletedTest", "a");
            CompletedContestView.launch(completedContestView);
            completedContestView.forceDispose();
            assert(true);

        } catch (Exception ex){
            assert(false);
        }
    }

    @org.junit.Test
    public void testUIFields(){
        CompletedContestView completedContestView = ContestUseCaseFactory.createCompletedContestView(completedContestViewModel, firebaseDataAccess, viewModelManager, "CompletedTest", "a");
        System.out.println(completedContestView.getContestIndustry().getText());
        assert(Objects.equals(completedContestView.getContestName().getText(), "Test"));
        assert(Objects.equals(completedContestView.getContestIndustry().getText(), "Technology"));
        assert(Objects.equals(completedContestView.getPlacement().getText(), "Placement: 1"));
        assert(Objects.equals(completedContestView.getProfit().getText(), "Profit: 12000.0"));

    }







}
