package CompletedContestTests.CompletedContest;

import Common.CreatePortfolios;
import FirebaseDataAccess.FirebaseDataAccess;
import UseCase.CompletedContest.CompletedContestInteractor;
import InterfaceAdapters.CompletedContests.CompletedContestPresenter;
import InterfaceAdapters.CompletedContests.CompletedContestViewModel;
import InterfaceAdapters.ViewModelManager;

import java.util.ArrayList;
import java.util.HashMap;

public class CompletedContestInteractorTests {

    public FirebaseDataAccess firebaseDataAccess;
    public CompletedContestPresenter completedContestPresenter;
    public CompletedContestViewModel completedContestViewModel;

    public ViewModelManager viewModelManager;
    public CompletedContestInteractor completedContestInteractor;

    public CompletedContestInteractorTests(){
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.completedContestViewModel = new CompletedContestViewModel();
        this.viewModelManager = new ViewModelManager();
        this.completedContestPresenter = new CompletedContestPresenter(this.completedContestViewModel, this.viewModelManager);
        // hardcoded values for test purposes
        this.completedContestInteractor = new CompletedContestInteractor(this.firebaseDataAccess, this.completedContestPresenter, "1", "dhruv");
    }

    @org.junit.Test
    public void createLeaderboardTest(){

        ArrayList<String> leaderboardInteractor = this.completedContestInteractor.createLeaderboard(CreatePortfolios.createPorfolios());
        ArrayList<String> leaderboardTest = new ArrayList<>();
        leaderboardTest.add("dhruv");
        leaderboardTest.add("mikhail");
        leaderboardTest.add("goncalo");
        leaderboardTest.add("brad");


        assert(leaderboardTest.equals(leaderboardInteractor));

    }
    @org.junit.Test
    public void getProfitTest(){
        HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = CreatePortfolios.createPorfolios();
        for (String username: portfolios.keySet()){
            HashMap<String, HashMap<String, String>> portfolio = portfolios.get(username);

            float profit = 0;
            for (String ticker: portfolio.keySet()){
                String value = portfolio.get(ticker).get("Value");
                profit += Float.parseFloat(value);
            }

            assert(this.completedContestInteractor.getProfit(portfolio) == profit);

        }

    }
}
