package interface_adapters.HomePage;

import UseCase.HomePage.HomePageOutputBoundary;
import UseCase.HomePage.HomePageOutputData;
import interface_adapters.ViewModelManager;

public class HomePagePresenter implements HomePageOutputBoundary {
    private HomePageViewModel homepageViewModel;
    private ViewModelManager viewModelManager;

    public HomePagePresenter(HomePageViewModel homepageViewModel, ViewModelManager viewModelManager){
        this.homepageViewModel = homepageViewModel;
        this.viewModelManager = viewModelManager;
    }


    public void prepareSuccessView(HomePageOutputData response) {
        HomePageState state = homepageViewModel.getState();
        setStateFields(response, state); // sets fields in the state
        this.homepageViewModel.setState(state);
        homepageViewModel.firePropertyChanged();


    }

    private void setStateFields(HomePageOutputData response, HomePageState state) {
        state.setUsername(response.username);
        state.setCompletedContests(response.completedContests);
        state.setAvailableContests(response.availableContests);
        state.setEnrolledContests(response.enrolledContests);
    }


    // TODO: Add Functionality to Show a message or something if there is an error.
    @Override
    public void prepareFailView() {

    }
}
