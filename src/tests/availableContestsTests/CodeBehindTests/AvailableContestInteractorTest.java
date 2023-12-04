package availableContestsTests.CodeBehindTests;

import common.CreateContest;
import common.CreatePortfolios;
import firebaseDataAccess.FirebaseDataAccess;
import interfaceAdapters.AvailableContests.AvailableContestPresenter;
import interfaceAdapters.AvailableContests.AvailableContestsViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.AvailableContest.AvailableContestInputData;
import useCase.AvailableContest.AvailableContestInteractor;
import entity.User;

import java.util.ArrayList;


public class AvailableContestInteractorTest {
    public FirebaseDataAccess firebaseDataAccess;
    public AvailableContestPresenter availableContestsPresenter;
    public AvailableContestsViewModel availableContestsViewModel;

    public ViewModelManager viewModelManager;
    public AvailableContestInteractor availableContestInteractor;


    public AvailableContestInteractorTest(){
        CreateContest.createContest(1);
        new User("dummy", "dummy", new ArrayList<>(), new ArrayList<>());
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.availableContestsViewModel = new AvailableContestsViewModel();
        this.viewModelManager = new ViewModelManager();
        this.availableContestsPresenter = new AvailableContestPresenter(this.availableContestsViewModel, this.viewModelManager );
        // hardcoded values for test purposes
        var availableContestInputData = new AvailableContestInputData("dummy", "0");
        this.availableContestInteractor = new AvailableContestInteractor(this.availableContestsPresenter, availableContestInputData);
    }

    @org.junit.Test
    public void construct(){
        var availableContestInputData = new AvailableContestInputData("dummy", "0");
        var interactor = new AvailableContestInteractor(this.availableContestsPresenter, availableContestInputData);
        assert(interactor.getContestId() == "0" && interactor.getUsername() == "dummy");
    }

    // execute test should validate whether the view model gets updated with a non-null state
    @org.junit.Test
    public void execute() {
        this.availableContestInteractor.execute();
        assert(this.availableContestsViewModel.getState() != null);
    }

    // enrollUserInContest test should validate whether a user can be added to a contest
    @org.junit.Test
    public void enrollUserInContestSuccess() {
        assert(this.availableContestInteractor.enrollUserInContest(CreatePortfolios.createPorfolios().get("dummy")));
    }
    @org.junit.Test
    public void enrollUserInContestFail(){
        var availableContestInputData = new AvailableContestInputData("dummy", "10000");
        var interactor = new AvailableContestInteractor(this.availableContestsPresenter, availableContestInputData);
        assert(!interactor.enrollUserInContest(CreatePortfolios.createPorfolios().get("dummy")));
    }

    // getUpdatedStockPrice test should validate whether a price is indeed fetched
    @org.junit.Test
    public void getUpdatedStockPrice() {
        assert(this.availableContestInteractor.getUpdatedStockPrice("AAPL") >= 0);
    }
}