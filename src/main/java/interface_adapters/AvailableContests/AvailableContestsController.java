package interface_adapters.AvailableContests;

import UseCase.AvailableContest.AvailableContestInteractor;

import javax.swing.event.ListSelectionEvent;

public class AvailableContestsController {
    AvailableContestInteractor availableContestInteractor;
    public AvailableContestsController(AvailableContestInteractor availableContestInteractor){
        this.availableContestInteractor = availableContestInteractor;
        this.availableContestInteractor.execute();
    }
    public float getUpdatedStockPrice(String stockName) throws Exception{
        return availableContestInteractor.getUpdatedStockPrice(stockName);
    }
    public boolean enrollUserInContest(){
        return availableContestInteractor.enrollUserInContest();
    }
}
