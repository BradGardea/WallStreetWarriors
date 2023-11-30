package app;


import FirebaseDataAccess.FirebaseDataAccess;
import entity.IUserFactory;
import entity.UserFactory;
import interface_adapters.SignUpLogIn.*;
import interface_adapters.ViewModelManager;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;
import view.LogInSignUp.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewModelManager viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel, FirebaseDataAccess userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
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

}
