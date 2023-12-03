package InterfaceAdapters.SignUpLogIn;

import InterfaceAdapters.ViewModelManager;
import UseCase.signup.SignupOutputBoundary;
import UseCase.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewModelManager viewManagerModel;

    public SignupPresenter(ViewModelManager viewManagerModel,
                           SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessViewLogin(SignupOutputData response) {
        // On success, switch to the login view when sign up is successful

        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void prepareSuccessViewButton() {
        // switch to the login view when button is clicked

        LoginState loginState = loginViewModel.getState();
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }
}
