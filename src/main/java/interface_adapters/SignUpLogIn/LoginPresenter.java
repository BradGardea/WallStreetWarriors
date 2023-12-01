package interface_adapters.SignUpLogIn;

import interface_adapters.HomePage.HomePageState;
import interface_adapters.HomePage.HomePageViewModel;
import interface_adapters.ViewModelManager;
import UseCase.login.LoginOutputBoundary;
import UseCase.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final HomePageViewModel homePageViewModel;
    private ViewModelManager viewManagerModel;

    public LoginPresenter(ViewModelManager viewManagerModel,
                          HomePageViewModel loggedInViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homePageViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        HomePageState homepageState = homePageViewModel.getState();
        homepageState.setUsername(response.getUsername());
        this.homePageViewModel.setState(homepageState);
        this.homePageViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(homePageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }
}
