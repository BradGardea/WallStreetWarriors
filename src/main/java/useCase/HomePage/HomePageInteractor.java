package useCase.HomePage;

import com.google.cloud.Timestamp;
import entity.Contest;
import entity.User;

import firebaseDataAccess.FirebaseDataAccess;

import java.util.*;

public class HomePageInteractor implements HomePageInputBoundary {
    public final FirebaseDataAccess homepageDataAccessObject;

    public final HomePageOutputBoundary homepagePresenter;

    public String username;


    public HomePageInteractor(FirebaseDataAccess homepageDataAccessObject,
                              HomePageOutputBoundary homepagePresenter, HomePageInputData homePageInputData) {
        this.homepageDataAccessObject = homepageDataAccessObject;
        this.homepagePresenter = homepagePresenter;
        this.username = homePageInputData.getUsername();
        execute();
    }

    /**
     * Executes the function by performing the following steps:
     *  1. Retrieves the user entity from the homepageDataAccessObject based on the given username.
     *  2. Initializes the enrolledContests, completedContests, and availableContests lists.
     *  3. If the user has enrolled contests, retrieves the Contest entities from the homepageDataAccessObject and adds them to the enrolledContests list.
     *  4. If the user has completed contests, retrieves the Contest entities from the homepageDataAccessObject and adds them to the completedContests list.
     *  5. Retrieves all Contest entities from the homepageDataAccessObject and adds those that the user has neither completed nor enrolled in to the availableContests list.
     *  6. Creates a HomePageOutputData object with the username, enrolledContests, completedContests, and availableContests lists.
     *  7. Calls the prepareSuccessView method of the homepagePresenter to prepare the success view with the homepageOutputData.
     */
    @Override
    public void execute() {
        User user = homepageDataAccessObject.getEntity(User.class, "Users", username);

        ArrayList<Contest> enrolledContests = new ArrayList<>();

        ArrayList<Contest> completedContests = new ArrayList<>();

        ArrayList<Contest> availableContests = new ArrayList<>();
        if (user.getEnrolledContests() != null){
            for (var eC: user.getEnrolledContests()){
                enrolledContests.add(homepageDataAccessObject.getEntity(Contest.class, "Contests", eC));
            }
        }
        if (user.getCompletedContests() != null){
            for (var cC: user.getCompletedContests()){
                completedContests.add(homepageDataAccessObject.getEntity(Contest.class, "Contests", cC));
            }
        }
        for (var contest: homepageDataAccessObject.getEntities(Contest.class, "Contests")){
            if (!user.getCompletedContests().contains(contest.getContestId()) && !user.getEnrolledContests().contains(contest.getContestId())){
                availableContests.add(contest);
            }
        }

        HomePageOutputData homepageOutputData = new HomePageOutputData(username, enrolledContests, completedContests, availableContests);
        homepagePresenter.prepareSuccessView(homepageOutputData);
    }

    /**
     * Executes the sign out action.
     *
     */
    @Override
    public void executeSignOut() {
        homepagePresenter.prepareSuccessViewSignOut();
    }

}