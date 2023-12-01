package app;


import FirebaseDataAccess.FirebaseDataAccess;
import entity.UserFactory;
import interface_adapters.HomePage.HomePageViewModel;
import interface_adapters.SignUpLogIn.SignupViewModel;
import interface_adapters.ViewModelManager;
import interface_adapters.SignUpLogIn.LoginController;
import interface_adapters.SignUpLogIn.LoginPresenter;
import interface_adapters.SignUpLogIn.LoginViewModel;
import UseCase.login.LoginInputBoundary;
import UseCase.login.LoginInteractor;
import UseCase.login.LoginOutputBoundary;
import view.LogInSignUp.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewModelManager viewManagerModel,
            LoginViewModel loginViewModel,
            HomePageViewModel homepageViewModel,
            FirebaseDataAccess userDataAccessObject,
            SignupViewModel signupViewModel) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, homepageViewModel, userDataAccessObject, signupViewModel);
            return new LoginView(loginViewModel, loginController, viewManagerModel, homepageViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewModelManager viewManagerModel,
            LoginViewModel loginViewModel,
            HomePageViewModel homepageViewModel,
            FirebaseDataAccess userDataAccessObject,
            SignupViewModel signupViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homepageViewModel, loginViewModel, signupViewModel);

        UserFactory userFactory = new UserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
