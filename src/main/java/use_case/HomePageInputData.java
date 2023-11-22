package use_case;

public class HomePageInputData {

    final private String username;
    final private String password;

    public HomePageInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

}
