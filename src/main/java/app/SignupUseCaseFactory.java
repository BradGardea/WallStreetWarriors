package app;

// import data_access.FileUserDataAccessObject;
// import data_access.UserSignupDataAccessInterface;
import entity.SignUpLogIn.CommonUserFactory;
import entity.SignUpLogIn.UserFactory;
import interface_adapters.SignUpLogIn.*;
import interface_adapters.ViewModelManager;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.LogInSignUp.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewModelManager viewManagerModel, LoginViewModel loginViewModel, SignupViewModel signupViewModel) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewModelManager viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel) throws IOException {
        // UserSignupDataAccessInterface userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                //userDataAccessObject
                signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
