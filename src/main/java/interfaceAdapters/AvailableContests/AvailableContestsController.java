package interfaceAdapters.AvailableContests;

import useCase.AvailableContest.AvailableContestInteractor;

import java.util.HashMap;

public class AvailableContestsController {
    AvailableContestInteractor availableContestInteractor;
    public AvailableContestsController(AvailableContestInteractor availableContestInteractor){
        this.availableContestInteractor = availableContestInteractor;
        this.availableContestInteractor.execute();
    }

    /**
     * Retrieves the updated stock price for a given stock name.
     *
     * @param  stockName  the name of the stock
     * @return            the updated stock price
     * @throws Exception if an error occurs while retrieving the stock price
     */
    public float getUpdatedStockPrice(String stockName) throws Exception{
        return availableContestInteractor.getUpdatedStockPrice(stockName);
    }

    /**
     * Enrolls a user in a contest.
     *
     * @param  currentPortfollio   a map representing the user's current portfolio
     * @return                     true if the user is successfully enrolled in the contest, false otherwise
     */
    public boolean enrollUserInContest(HashMap<String, HashMap<String, String>> currentPortfollio){
        return availableContestInteractor.enrollUserInContest(currentPortfollio);
    }
}
