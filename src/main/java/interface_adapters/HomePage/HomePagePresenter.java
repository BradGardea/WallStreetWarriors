package interface_adapters.HomePage;

import UseCase.HomePage.HomePageOutputBoundary;
import UseCase.HomePage.HomePageOutputData;
import interface_adapters.SignUpLogIn.LoginState;
import interface_adapters.SignUpLogIn.LoginViewModel;
import interface_adapters.ViewModelManager;
import org.apache.commons.logging.Log;

public class HomePagePresenter implements HomePageOutputBoundary {
    private HomePageViewModel homepageViewModel;
    private ViewModelManager viewModelManager;

    private LoginViewModel loginViewModel;

    public HomePagePresenter(HomePageViewModel homepageViewModel, ViewModelManager viewModelManager, LoginViewModel loginViewModel){
        this.homepageViewModel = homepageViewModel;
        this.viewModelManager = viewModelManager;
        this.loginViewModel = loginViewModel;
    }


    public void prepareSuccessView(HomePageOutputData response) {
        HomePageState state = homepageViewModel.getState();
        setStateFields(response, state); // sets fields in the state
        this.homepageViewModel.setState(state);
        homepageViewModel.firePropertyChanged();


    }

    public void prepareSuccessViewSignOut() {
        // switch to the login view when button is clicked

        LoginState loginState = loginViewModel.getState();
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewModelManager.setActiveView(loginViewModel.getViewName());
        viewModelManager.firePropertyChanged();
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
