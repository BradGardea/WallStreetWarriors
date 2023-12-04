package useCase.SignUp;

public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData);

    void executeSwitchScreen();
}
