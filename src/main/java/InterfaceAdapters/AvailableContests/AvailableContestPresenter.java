package InterfaceAdapters.AvailableContests;

import UseCase.AvailableContest.AvailableContestOuputBoundary;
import entity.Contest;
import InterfaceAdapters.ViewModelManager;

public class AvailableContestPresenter implements AvailableContestOuputBoundary {
    AvailableContestsViewModel availableContestsViewModel;
    ViewModelManager viewModelManager;
    public AvailableContestPresenter(AvailableContestsViewModel availableContestsViewModel, ViewModelManager viewModelManager) {
        this.availableContestsViewModel = availableContestsViewModel;
        this.viewModelManager = viewModelManager;
    }
    public void prepareSuccess(Contest finalizedContest){
        AvailableContestState state;
        if (availableContestsViewModel.getState() == null){
            state = new AvailableContestState(finalizedContest);
        }
        else{
            state = availableContestsViewModel.getState();
            state.setContestDetails(finalizedContest); // sets fields in the state
        }
        this.availableContestsViewModel.setState(state);
        availableContestsViewModel.firePropertyChanged();
    }
    public void prepareFail(){

    }
}
