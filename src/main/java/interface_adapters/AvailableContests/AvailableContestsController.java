package interface_adapters.AvailableContests;

import UseCase.AvailableContest.AvailableContestInteractor;

import javax.swing.event.ListSelectionEvent;

public class AvailableContestsController {
    AvailableContestInteractor availableContestInteractor;
    public AvailableContestsController(AvailableContestInteractor availableContestInteractor){
        this.availableContestInteractor = availableContestInteractor;
        this.availableContestInteractor.execute();
    }
    public String stockListChanged(String stockName) {
        var cost = getUpdatedStockPrice(stockName);
        return String.valueOf(cost);
    }
    public float getUpdatedStockPrice(String stockName){
        //TODO: api call here
        return 100;
    }
    public void enrollUserInContest(){

    }
}
