package UseCase.login;

import FirebaseDataAccess.FirebaseDataAccess;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final FirebaseDataAccess userDataAccessObject = FirebaseDataAccess.getInstance();
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginOutputBoundary loginOutputBoundary) {
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public boolean execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (userDataAccessObject.getEntity(User.class, "Users", username) == null) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
            return false;
        } else {
            String pwd = userDataAccessObject.getEntity(User.class, "Users", username).getPassword().trim();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
                return false;
            } else {

                User user = userDataAccessObject.getEntity(User.class, "Users", username);

                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
                return true;
            }
        }
    }
}