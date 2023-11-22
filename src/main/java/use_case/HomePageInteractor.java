package use_case;

import entity.User;

public class HomePageInteractor implements HomePageInputBoundary {
    final HomePageDataAccessInterface userDataAccessObject;
    final HomePageOutputBoundary loginPresenter;

    public HomePageInteractor(HomePageDataAccessInterface userDataAccessInterface,
                              HomePageOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(HomePageInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());

                HomePageOutputData loginOutputData = new HomePageOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}