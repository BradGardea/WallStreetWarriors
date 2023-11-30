package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;

    final private String id;

    public SignupInputData(String username, String password, String repeatPassword, String id) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.id = id;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    String getId() {
        return id;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

}
