package interface_adapters.AvailableContests;

import entity.Contest;
import interface_adapters.ViewModelManager;

public class AvailableContestsPresenter {
    private ViewModelManager viewModelManager;
    private AvailableContestsViewModel availableContestsViewModel;

    public AvailableContestsPresenter(ViewModelManager viewManagerModel, AvailableContestsViewModel availableContestsViewModel){
        this.viewModelManager = viewManagerModel;
        this.availableContestsViewModel = availableContestsViewModel;
    }

    public void modifyContestDetails(Contest contest){
        availableContestsViewModel.setState(new AvailableContestState(contest));
        availableContestsViewModel.firePropertyChanged();

        viewModelManager.setActiveView(availableContestsViewModel.getViewName());
        viewModelManager.firePropertyChanged();
    }
}
