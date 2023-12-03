package tests.CompletedContestTests.CompletedContest;

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

    public static HashMap<String, HashMap<String, HashMap<String, String>>> createPorfolios() {


        HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = new HashMap<>();
        HashMap<String, HashMap<String, String>> innerPortfolioDhruv = new HashMap<>();
        HashMap<String, String> innerPortfolioDhruv2 = new HashMap<>();

        innerPortfolioDhruv2.put("Ticker", "AAPL");
        innerPortfolioDhruv2.put("Purchase Price", "100");
        innerPortfolioDhruv2.put("Quantity", "100");
        innerPortfolioDhruv2.put("End Price", "120");
        innerPortfolioDhruv2.put("Value", "12000");

        innerPortfolioDhruv.put("AAPL", innerPortfolioDhruv2);

        portfolios.put("dhruv", innerPortfolioDhruv);


        HashMap<String, HashMap<String, String>> innerPortfolioBrad = new HashMap<>();
        HashMap<String, String> innerPortfolioBrad2 = new HashMap<>();

        innerPortfolioBrad2.put("Ticker", "MSFT");
        innerPortfolioBrad2.put("Purchase Price", "320");
        innerPortfolioBrad2.put("Quantity", "32");
        innerPortfolioBrad2.put("End Price", "300");
        innerPortfolioBrad2.put("Value", "9000");

        innerPortfolioBrad.put("MSFT", innerPortfolioBrad2);
        portfolios.put("brad", innerPortfolioBrad);

        HashMap<String, HashMap<String, String>> innerPortfolioMikhail = new HashMap<>();
        HashMap<String, String> innerPortfolioMikhail2 = new HashMap<>();

        innerPortfolioMikhail2.put("Ticker", "TSLA");
        innerPortfolioMikhail2.put("Purchase Price", "120");
        innerPortfolioMikhail2.put("Quantity", "83");
        innerPortfolioMikhail2.put("End Price", "130");
        innerPortfolioMikhail2.put("Value", "10790");

        innerPortfolioMikhail.put("TSLA", innerPortfolioMikhail2);

        portfolios.put("mikhail", innerPortfolioMikhail);

        HashMap<String, HashMap<String, String>> innerPortfolioGoncalo = new HashMap<>();
        HashMap<String, String> innerPortfolioGoncalo2 = new HashMap<>();

        innerPortfolioGoncalo2.put("Ticker", "GOOG");
        innerPortfolioGoncalo2.put("Purchase Price", "80");
        innerPortfolioGoncalo2.put("Quantity", "125");
        innerPortfolioGoncalo2.put("End Price", "85");
        innerPortfolioGoncalo2.put("Value", "10625");

        innerPortfolioGoncalo.put("GOOG", innerPortfolioGoncalo2);

        portfolios.put("goncalo", innerPortfolioGoncalo);

        return portfolios;

    }

    @org.junit.Test
    public void createLeaderboardTest(){

        ArrayList<String> leaderboardInteractor = this.completedContestInteractor.createLeaderboard(createPorfolios());
        ArrayList<String> leaderboardTest = new ArrayList<>();
        leaderboardTest.add("dhruv");
        leaderboardTest.add("mikhail");
        leaderboardTest.add("goncalo");
        leaderboardTest.add("brad");


        assert(leaderboardTest.equals(leaderboardInteractor));

    }
    @org.junit.Test
    public void getProfitTest(){
        HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = createPorfolios();
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
