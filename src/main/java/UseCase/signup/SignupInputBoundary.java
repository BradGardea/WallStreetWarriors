package UseCase.signup;

public interface SignupInputBoundary {
    void execute(SignupInputData signupInputData);

    void executeSwitchScreen();
}
