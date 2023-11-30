package use_case;

import entity.Contest;
import entity.User;

import FirebaseDataAccess.FirebaseDataAccess;
import entity.User;

import java.lang.reflect.Array;
import java.util.*;

public class HomePageInteractor implements HomePageInputBoundary {
    public final FirebaseDataAccess homepageDataAccessObject;

    public final HomePageOutputBoundary homepagePresenter;

    public String username;


    public HomePageInteractor(FirebaseDataAccess homepageDataAccessObject,
                              HomePageOutputBoundary homepagePresenter, String username) {
        this.homepageDataAccessObject = homepageDataAccessObject;
        this.homepagePresenter = homepagePresenter;
        this.username = username;
    }

    @Override
    public void execute() {
        User user = homepageDataAccessObject.getEntity(User.class, "Users", username);

        ArrayList<Contest> enrolledContests = user.getEnrolledContests();

        ArrayList<Contest> completedContests = user.getCompletedContests();

        //fix this to find available contests
        ArrayList<Contest> availableContests = user.getCompletedContests();

        HomePageOutputData homepageOutputData = new HomePageOutputData(username, enrolledContests, completedContests, availableContests);
        homepagePresenter.prepareSuccessView(homepageOutputData);

        ///
    }

}