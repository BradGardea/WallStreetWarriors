package UseCase.HomePage;

import entity.Contest;
import entity.User;

import FirebaseDataAccess.FirebaseDataAccess;

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
        execute();
    }

    @Override
    public void execute() {
        User user = homepageDataAccessObject.getEntity(User.class, "Users", username);

        ArrayList<Contest> enrolledContests = new ArrayList<>();

        ArrayList<Contest> completedContests = new ArrayList<>();

        ArrayList<Contest> availableContests = new ArrayList<>();

        for (var eC: user.getEnrolledContests()){
            enrolledContests.add(homepageDataAccessObject.getEntity(Contest.class, "Contests", eC));
        }

        for (var cC: user.getCompletedContests()){
            completedContests.add(homepageDataAccessObject.getEntity(Contest.class, "Contests", cC));
        }

        for (var contest: homepageDataAccessObject.getEntities(Contest.class, "Contests")){
            if (!user.getEnrolledContests().contains(contest.getContestId()) && !user.getCompletedContests().contains(contest.getContestId())){
                availableContests.add(contest);
            }
        }

        HomePageOutputData homepageOutputData = new HomePageOutputData(username, enrolledContests, completedContests, availableContests);
        homepagePresenter.prepareSuccessView(homepageOutputData);

        ///
    }

    @Override
    public void executeSignOut() {
        homepagePresenter.prepareSuccessViewSignOut();
    }

}