package AvailableContestsTests.CodeBehindTests;

import Common.CreateContest;
import Common.CreatePortfolios;
import FirebaseDataAccess.FirebaseDataAccess;
import InterfaceAdapters.AvailableContests.AvailableContestPresenter;
import InterfaceAdapters.AvailableContests.AvailableContestsViewModel;
import InterfaceAdapters.CompletedContests.CompletedContestPresenter;
import InterfaceAdapters.CompletedContests.CompletedContestViewModel;
import InterfaceAdapters.ViewModelManager;
import UseCase.AvailableContest.AvailableContestInteractor;
import UseCase.AvailableContest.AvailableContestOuputBoundary;
import UseCase.CompletedContest.CompletedContestInteractor;
import entity.Contest;
import entity.User;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AvailableContestInteractorTest {
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
        this.availableContestInteractor = new AvailableContestInteractor(this.availableContestsPresenter, "0", "dummy");
    }

    // execute test should validate whether the view model gets updated with a non-null state
    @Test
    void execute() {
        this.availableContestInteractor.execute();
        assert(this.availableContestsViewModel.getState() != null);
    }

    // enrollUserInContest test should validate whether a user can be added to a contest
    @Test
    void enrollUserInContest() {
        assert(this.availableContestInteractor.enrollUserInContest(CreatePortfolios.createPorfolios().get("dummy")));
    }

    // getUpdatedStockPrice test should validate whether a price is indeed fetched
    @Test
    void getUpdatedStockPrice() {
        assert(this.availableContestInteractor.getUpdatedStockPrice("AAPL") >= 0);
    }
}