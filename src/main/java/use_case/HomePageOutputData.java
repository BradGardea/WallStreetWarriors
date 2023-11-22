package use_case;

public class HomePageOutputData {

    private final String username;
    private boolean useCaseFailed;

    public HomePageOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

}
