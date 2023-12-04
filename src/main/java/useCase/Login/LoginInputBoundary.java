package useCase.Login;

public interface LoginInputBoundary {
    boolean execute(LoginInputData loginInputData);
    void executeSwitchScreen();
}
