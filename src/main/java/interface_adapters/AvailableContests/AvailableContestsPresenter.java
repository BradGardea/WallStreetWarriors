package interface_adapters.AvailableContests;

import entity.AvailableContest;
import interface_adapters.ViewModelManager;

public class AvailableContestsPresenter {
    private ViewModelManager viewModelManager;
    private AvailableContestsViewModel availableContestsViewModel;

    public AvailableContestsPresenter(ViewModelManager viewManagerModel, AvailableContestsViewModel availableContestsViewModel){
        this.viewModelManager = viewManagerModel;
        this.availableContestsViewModel = availableContestsViewModel;
    }

    public void modifyContestDetails(AvailableContest contest){
        availableContestsViewModel.setState(new AvailableContestState(contest));
        availableContestsViewModel.firePropertyChanged();

        viewModelManager.setActiveView(availableContestsViewModel.getViewName());
        viewModelManager.firePropertyChanged();
    }
}
