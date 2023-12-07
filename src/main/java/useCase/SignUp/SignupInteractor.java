package useCase.SignUp;

// import data_access.UserSignupDataAccessInterface;
import firebaseDataAccess.FirebaseDataAccess;
import entity.User;
import entity.UserFactory;

public class SignupInteractor implements SignupInputBoundary {
    final FirebaseDataAccess userDataAccessObject;
    final SignupOutputBoundary userPresenter;
    final UserFactory userFactory;


    public SignupInteractor(FirebaseDataAccess signupUserDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = signupUserDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    /**
     * Executes the signup process for a user.
     *
     * @param  signupInputData  the input data for the signup process
     * @return                  void
     */
    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.getEntity(User.class, "Users", signupInputData.getUsername()) !=  null) {
        userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.setOrUpdateEntity(user, "Users", user.getUsername());

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessViewLogin(signupOutputData);
        }
    }

    /**
     * Switches the screen.
     *
     */
    public void executeSwitchScreen(){
        userPresenter.prepareSuccessViewButton();

    }
}