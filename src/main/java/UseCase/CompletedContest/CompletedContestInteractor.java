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
            float user_profit = getProfit(data.get(username));

            // adding the username and thier profit to the LinkedHashMap
            ordered_leaderboard.put(username, user_profit);
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


    private float getProfit(HashMap<String, HashMap<String, String>> portfolio){
        /**
         * Calculates the total profit from a given portfolio.
         *
         * @param  portfolio  a HashMap representing the user's portfolio, where the keys are tickers and the values are arrays of stock information
         * @return            the total profit calculated from the portfolio
         */

        // TODO: Ensure "Value" is correct in terms of capitalization once logic for setting portfiolio is implemented
        float user_profit = 0;
        for (String ticker: portfolio.keySet()){
            user_profit += Float.parseFloat((String) portfolio.get(ticker).get("Value"));
        }

        return user_profit;
    }
}
