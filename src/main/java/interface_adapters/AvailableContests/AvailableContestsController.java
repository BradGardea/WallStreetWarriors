package interface_adapters.AvailableContests;

import UseCase.AvailableContest.AvailableContestInteractor;

import javax.swing.event.ListSelectionEvent;

public class AvailableContestsController {
    AvailableContestInteractor availableContestInteractor;
    public AvailableContestsController(AvailableContestInteractor availableContestInteractor){
        this.availableContestInteractor = availableContestInteractor;
        this.availableContestInteractor.execute();
    }
    public Float stockListChanged(String stockName) {
        return getUpdatedStockPrice(stockName);
    }
    public float getUpdatedStockPrice(String stockName){
        return availableContestInteractor.getUpdatedStockPrice(stockName);
    }
    public boolean enrollUserInContest(){
        return availableContestInteractor.enrollUserInContest();
    }
}
