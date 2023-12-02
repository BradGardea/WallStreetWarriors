package app;

import FirebaseDataAccess.FirebaseDataAccess;
import UseCase.HomePage.HomePageInputBoundary;
import UseCase.HomePage.HomePageInteractor;
import UseCase.HomePage.HomePageOutputBoundary;
import UseCase.login.LoginInputBoundary;
import UseCase.login.LoginInteractor;
import UseCase.login.LoginOutputBoundary;
import UseCase.signup.SignupInputBoundary;
import UseCase.signup.SignupInteractor;
import UseCase.signup.SignupOutputBoundary;
import entity.UserFactory;
import interface_adapters.HomePage.HomePageController;
import interface_adapters.HomePage.HomePagePresenter;
import interface_adapters.HomePage.HomePageViewModel;
import interface_adapters.SignUpLogIn.*;
import interface_adapters.ViewModelManager;
import view.HomePage.HomePageView;
import view.LogInSignUp.LoginView;
import view.LogInSignUp.SignupView;

import javax.swing.*;
import java.io.IOException;

public class MainNavigationFactory {

    // prevent init
    private MainNavigationFactory() {};

    /**
     * Parameterized factory that requires a view name to create a specified view with CA components embedded.
     * @param viewName name of view for class to be constructed
     * @return returns a MainNavigationView, empty interface used to generalize main components.
     */
    public static MainNavigationView createMainView(String viewName, ViewModelManager viewManagerModel, HomePageViewModel homepageViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, FirebaseDataAccess userDataAccessObject, String username){
        if (viewName == "sign up"){
            try {
                SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
                return new SignupView(signupController, signupViewModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }
        else if (viewName == "log in"){
            try {
                LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, homepageViewModel, userDataAccessObject, signupViewModel);
                return new LoginView(loginViewModel, loginController, viewManagerModel, homepageViewModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }
        else if (viewName == "homepage view"){
            try {
                HomePageController homepageController = createHomePageUseCase(viewManagerModel, homepageViewModel, userDataAccessObject, username);
                return new HomePageView(homepageViewModel, homepageController, viewManagerModel);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Could not open user data file.");
            }

            return null;
        }
        return null;
    }

    private static SignupController createUserSignupUseCase(ViewModelManager viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, FirebaseDataAccess userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new UserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject,
                signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
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

    private static HomePageController createHomePageUseCase(ViewModelManager viewManagerModel, HomePageViewModel homepageViewModel, FirebaseDataAccess userDataAccessObject, String username) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        HomePageOutputBoundary homePageOutputBoundary = new HomePagePresenter(homepageViewModel, viewManagerModel, new LoginViewModel());

        HomePageInputBoundary userHomePageInteractor = new HomePageInteractor(userDataAccessObject, homePageOutputBoundary, username);

        return new HomePageController(userHomePageInteractor);
    }
}
