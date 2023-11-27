package UseCase.CompletedContest;

import FirebaseDataAccess.FirebaseDataAccess;
import com.google.type.DateTime;
import entity.Contest;

import java.util.*;

public class CompletedContestInteractor implements CompletedContestInputBoundary{

    public final FirebaseDataAccess completedContestDataAccessObject;

    public final CompletedContestOutputBoundary completedContestPresenter;

    public String username;

    private String contestId;

    public CompletedContestInteractor(FirebaseDataAccess completedContestDataAccessObject, CompletedContestOutputBoundary completedContestPresenter, String contestId, String username) {
        this.completedContestDataAccessObject = completedContestDataAccessObject;
        this.completedContestPresenter = completedContestPresenter;
        this.contestId = contestId;
        this.username = username;
    }


    @Override
    public void execute() {
        Contest contest = completedContestDataAccessObject.getEntity(Contest.class, "Contests", "1");
        // portfolio for the logged in user.
        HashMap<String, HashMap<String, String>> portfolio = contest.getPortfolios().get(username);
        // creating the leaderboard
        ArrayList<String> leaderboard = createLeaderboard(contest.getPortfolios());
        String profit = Float.toString(getProfit(portfolio));
        String placement = String.valueOf(leaderboard.indexOf(username) + 1);

        CompletedContestOutputData completedContestOutputData = new CompletedContestOutputData(contest, portfolio, leaderboard, profit, placement);
        completedContestPresenter.prepareSuccessView(completedContestOutputData);


    }
    //TODO: fix this
    public ArrayList<String> createLeaderboard(HashMap<String, HashMap<String, HashMap<String, String>>> data){
        /**
         * A description of the entire Java function.
         *
         * @param  data   A HashMap containing the data for creating the leaderboard.
         *                The HashMap has the following structure:
         *                - The keys are usernames.
         *                - The values are HashMaps representing portfolios.
         *                  Each portfolio HashMap has the following structure:
         *                  - The keys are tickers.
         *                  - The values are arrays of strings representing the ticker data.
         * @return       An ArrayList of strings representing the leaderboard.
         *               Each string in the ArrayList represents a username in the leaderboard.
         */



        // iterate through all the portfolios and create a hashmap with <Key = Profit, Value = Username>
        // sort this hashmap and return the value.
        Map<String, Float> ordered_leaderboard = new LinkedHashMap<>();
        for (String username: data.keySet()){
            // nested for loop to get the data for each ticker
            float user_profit = getProfit(data.get(username));

            ordered_leaderboard.put(username, user_profit);
        }

        // by the end of this loop ordered_leaderboard will contain all usernames as keys
        // and their profits as values.
        // creating an entry set that we will now sort
        List<Map.Entry<String, Float>> entries = new ArrayList<>(ordered_leaderboard.entrySet());
        // sorting the entries by value
        Collections.sort(entries, Map.Entry.comparingByValue());
        // creating an array list of the usernames
        ArrayList<String> leaderboard = new ArrayList<>();

        for (Map.Entry<String, Float> entry: entries){
            leaderboard.add(entry.getKey());
        }

        // by the end of this loop we will have an array list of usernames in order of their profits
        return leaderboard;
    }


    private float getProfit(HashMap<String, HashMap<String, String>> portfolio){
        /**
         * Calculates the total profit from a given portfolio.
         *
         * @param  portfolio  a HashMap representing the user's portfolio, where the keys are tickers and the values are arrays of stock information
         * @return            the total profit calculated from the portfolio
         */

        float user_profit = 0;
        for (String ticker: portfolio.keySet()){
            //TODO: verify profit functions work as expected with new contest structure
            user_profit += Float.parseFloat((String) portfolio.get(ticker).get("quantity"));
        }

        return user_profit;
    }
}
