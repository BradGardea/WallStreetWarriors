package interfaceAdapters.AvailableContests;

import useCase.AvailableContest.AvailableContestOutputBoundary;
import interfaceAdapters.ViewModelManager;
import useCase.AvailableContest.AvailableContestOutputData;

public class AvailableContestPresenter implements AvailableContestOutputBoundary {
    AvailableContestsViewModel availableContestsViewModel;
    ViewModelManager viewModelManager;
    public AvailableContestPresenter(AvailableContestsViewModel availableContestsViewModel, ViewModelManager viewModelManager) {
        this.availableContestsViewModel = availableContestsViewModel;
        this.viewModelManager = viewModelManager;
    }

    /**
     * Prepares the success response for the available contest.
     *
     * @param  finalizedContest  the finalized contest data
     */
    public void prepareSuccess(AvailableContestOutputData finalizedContest){
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
