package useCase.CompletedContest;

import firebaseDataAccess.FirebaseDataAccess;
import entity.Contest;
import firebaseDataAccess.IDataAccess;

import java.util.*;

public class CompletedContestInteractor implements CompletedContestInputBoundary{

    public final IDataAccess completedContestDataAccessObject;

    public final CompletedContestOutputBoundary completedContestPresenter;

    public String username;

    private String contestId;


    public CompletedContestInteractor(FirebaseDataAccess completedContestDataAccessObject, CompletedContestOutputBoundary completedContestPresenter, CompletedContestInputData completedContestInputData) {
        this.completedContestDataAccessObject = completedContestDataAccessObject;
        this.completedContestPresenter = completedContestPresenter;
        this.contestId = completedContestInputData.getContestId();
        this.username = completedContestInputData.getUsername();
    }


    @Override
    public void execute() {
        Contest contest = completedContestDataAccessObject.getEntity(Contest.class, "Contests", this.contestId);
        // portfolio for the logged in user.
        HashMap<String, HashMap<String, String>> portfolio = contest.getPortfolios().get(username);
        var portfolios = contest.getPortfolios();
        // creating the leaderboard
        ArrayList<String> leaderboard = createLeaderboard(contest.getPortfolios());
        String portfolioValue = Float.toString(getPortfolioValue(portfolio));
        String placement = String.valueOf(leaderboard.indexOf(username) + 1);

        CompletedContestOutputData completedContestOutputData = new CompletedContestOutputData(contest, portfolio, leaderboard, portfolioValue, placement, portfolios);
        completedContestPresenter.prepareSuccessView(completedContestOutputData);


    }

    /**
     * Generates a leaderboard based on the given data.
     *
     * @param  data  a HashMap containing the user stock data
     * @return       an ArrayList of usernames in descending order of profit
     */
    public ArrayList<String> createLeaderboard(HashMap<String, HashMap<String, HashMap<String, String>>> data){
        /**
         * Generates a leaderboard based on the given data.
         *
         * @param  data  a HashMap containing the user stock data
         * @return       an ArrayList of usernames in descending order of profit
         */

        Map<String, Float> ordered_leaderboard = new LinkedHashMap<>();
        for (String username: data.keySet()){
            // iterates through each user's portfolio to get the total profit
            // calculating total profit passed over to helper function.
            float porfolio_value = getPortfolioValue(data.get(username));

            // adding the username and thier profit to the LinkedHashMap
            ordered_leaderboard.put(username, porfolio_value);
        }

        // by the end of this loop ordered_leaderboard will contain all usernames as keys
        // and their profits as values.

        // fills this temporary arraylist with the entries from the LinkedHashMap
        List<Map.Entry<String, Float>> entries = new ArrayList<>(ordered_leaderboard.entrySet());
        // sorting entries by value
        Collections.sort(entries, Map.Entry.comparingByValue());
        // reversing the list to have the highest profit at index 0 and lowest profit at index n - 1
        Collections.reverse(entries);
        // Creating the actual leaderboard that will be returned
        ArrayList<String> leaderboard = new ArrayList<>();

        // for loop that will iterate through the entries and add the usernames to the leaderboard
        for (Map.Entry<String, Float> entry: entries){
            leaderboard.add(entry.getKey());
        }
        return leaderboard;

    }

    /**
     * Calculates the total profit from a given portfolio.
     *
     * @param  portfolio  a HashMap representing the user's portfolio, where the keys are tickers and the values are arrays of stock information
     * @return            the total profit calculated from the portfolio
     */
    public float getPortfolioValue(HashMap<String, HashMap<String, String>> portfolio){
        /**
         * Calculates the total profit from a given portfolio.
         *
         * @param  portfolio  a HashMap representing the user's portfolio, where the keys are tickers and the values are arrays of stock information
         * @return            the total profit calculated from the portfolio
         */

        // TODO: Ensure "Value" is correct in terms of capitalization once logic for setting portfiolio is implemented
        float user_portfolio_value = 0;
        for (String ticker: portfolio.keySet()){
            user_portfolio_value += Float.parseFloat(portfolio.get(ticker).get("Value"));
        }

        return user_portfolio_value;
    }
}
