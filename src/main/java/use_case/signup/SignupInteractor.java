package use_case.signup;

// import data_access.UserSignupDataAccessInterface;
import FirebaseDataAccess.FirebaseDataAccess;
import entity.User;
import entity.IUserFactory;
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

    @Override
    public void execute(SignupInputData signupInputData) {
        if (userDataAccessObject.getEntity(User.class, "Users", signupInputData.getId()) != null) {
        userPresenter.prepareFailView("User already exists."); }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            User user = userFactory.create(signupInputData.getUsername(), signupInputData.getPassword());
            userDataAccessObject.setOrUpdateEntity(User.class, "Users", user.getUUID());

            SignupOutputData signupOutputData = new SignupOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}