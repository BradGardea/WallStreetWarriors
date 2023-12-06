package interfaceAdapters.CompletedContests;

import useCase.CompletedContest.CompletedContestOutputData;
import useCase.CompletedContest.CompletedContestOutputBoundary;
import interfaceAdapters.ViewModelManager;

public class CompletedContestPresenter implements CompletedContestOutputBoundary {

    private CompletedContestViewModel completedContestViewModel;
    private ViewModelManager viewModelManager;

    public CompletedContestPresenter(CompletedContestViewModel completedContestViewModel, ViewModelManager viewModelManager){
        this.completedContestViewModel = completedContestViewModel;
        this.viewModelManager = viewModelManager;
    }

    /**
     * A description of the prepareSuccessView function.
     *
     * @param  response  an instance of CompletedContestOutputData class
     *                   containing the response data
     * @return           void
     */
    @Override
    public void prepareSuccessView(CompletedContestOutputData response) {
        CompletedContestState state = completedContestViewModel.getState();
        setStateFields(response, state); // sets fields in the state
        this.completedContestViewModel.setState(state);
        completedContestViewModel.firePropertyChanged();


        // TODO: See if this code is necessary
        viewModelManager.setActiveView(completedContestViewModel.getViewName());
        viewModelManager.firePropertyChanged();
    }


    // helper method to set all fields in state
    private void setStateFields(CompletedContestOutputData response, CompletedContestState state){
        state.setContestName(response.contestName);
        state.setIndustry(response.industry);
        state.setStartDate(response.startTime);
        state.setEndDate(response.endTime);
        state.setPortfolio(response.portfolio);
        state.setLeaderboard(response.leaderboard);
        state.setPortfolioValue(response.portfolioValue);
        state.setPlacement(response.placement);
        state.setPortfolios(response.portfolios);
    }


    // TODO: Add Functionality to Show a message or something if there is an error.
    @Override
    public void prepareFailView() {

    }
}
