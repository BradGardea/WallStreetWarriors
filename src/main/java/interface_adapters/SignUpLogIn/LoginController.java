package interface_adapters.SignUpLogIn;

import UseCase.login.LoginInputBoundary;
import UseCase.login.LoginInputData;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    public boolean execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);
        if (loginUseCaseInteractor.execute(loginInputData)){
            return true;
        }
        else{
            return false;
        }
    }
}
