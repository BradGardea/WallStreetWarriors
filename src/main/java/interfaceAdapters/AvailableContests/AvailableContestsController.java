package interfaceAdapters.AvailableContests;

import useCase.AvailableContest.AvailableContestInteractor;

import java.util.HashMap;

public class AvailableContestsController {
    AvailableContestInteractor availableContestInteractor;
    public AvailableContestsController(AvailableContestInteractor availableContestInteractor){
        this.availableContestInteractor = availableContestInteractor;
        this.availableContestInteractor.execute();
    }
    public float getUpdatedStockPrice(String stockName) throws Exception{
        return availableContestInteractor.getUpdatedStockPrice(stockName);
    }
    public boolean enrollUserInContest(HashMap<String, HashMap<String, String>> currentPortfollio){
        return availableContestInteractor.enrollUserInContest(currentPortfollio);
    }
}
