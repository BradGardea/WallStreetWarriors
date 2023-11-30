package use_case;

import entity.Contest;
import entity.User;

import FirebaseDataAccess.FirebaseDataAccess;
import entity.User;
import interface_adapters.Contests.ContestState;

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

        ArrayList<Contest> enrolledContests = new ArrayList<>();

        ArrayList<Contest> completedContests = new ArrayList<>();

        //fix this to find available contests
        ArrayList<Contest> availableContests = new ArrayList<>();

        for (var eC: user.getEnrolledContests()){
            enrolledContests.add(homepageDataAccessObject.getEntity(Contest.class, "Contests", eC));
        }

        for (var cC: user.getEnrolledContests()){
            enrolledContests.add(homepageDataAccessObject.getEntity(Contest.class, "Contests", cC));
        }

        HomePageOutputData homepageOutputData = new HomePageOutputData(username, enrolledContests, completedContests, availableContests);
        homepagePresenter.prepareSuccessView(homepageOutputData);

        ///
    }

}