package enrolledContest;

import api.ApiCall;
import api.Credentials;
import entity.User;
import firebaseDataAccess.FirebaseDataAccess;
import app.ContestUseCaseFactory;
import app.Main;
import entity.Contest;
import firebaseDataAccess.IDataAccess;
import interfaceAdapters.Enrolled.EnrolledController;
import interfaceAdapters.Enrolled.EnrolledPresenter;
import interfaceAdapters.Enrolled.EnrolledViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.EnrolledContest.EnrolledInteractor;
import useCase.EnrolledContest.EnrolledOutputBoundary;
import view.EnrolledContest.EnrolledView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

/**
 * Tests for the Enrolled Contest view feature of the application.
 * This test class is responsible for conducting end-to-end tests to verify the integration
 * and correct functionality of the Enrolled Contest feature, including its controller, interactor,
 * presenter, view model, and view components.
 */
public class EnrolledContestViewTests {
    private EnrolledView enrolledView;
    private EnrolledViewModel enrolledViewModel;
    private ViewModelManager viewModelManager;
    private IDataAccess firebaseDataAccess;

    /**
     * Performs an end-to-end test of the Enrolled Contest feature.
     * This method initializes necessary components, sets up a test scenario for the Enrolled Contest view,
     * and asserts that the view displays the correct information as expected.
     * It simulates a complete flow from initializing Firebase, creating view and model components,
     * executing controller logic, and finally validating the output on the view.
     *
     * @throws IOException If there is an error during the test execution.
     */
    @org.junit.Test
    public void testEndToEnd() throws IOException {

        Main.FirebaseInit();
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.viewModelManager = new ViewModelManager();
        this.enrolledViewModel = new EnrolledViewModel();
        this.enrolledView = ContestUseCaseFactory.createEnrolledView(enrolledViewModel, firebaseDataAccess, viewModelManager, "EnrolledTest", "a");
        EnrolledOutputBoundary enrolledOutputBoundary = new EnrolledPresenter(enrolledViewModel, viewModelManager);
        EnrolledInteractor enrolledInteractor = new EnrolledInteractor(firebaseDataAccess, enrolledOutputBoundary);

        EnrolledController enrolledController = new EnrolledController(enrolledInteractor);
        enrolledController.execute("a", "EnrolledTest");

        Contest enrolledContest = firebaseDataAccess.getEntity(Contest.class, "Contests", "EnrolledTest");

        ArrayList<User> members = enrolledContest.getMembers();
        HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = enrolledContest.getPortfolios();
        String title = enrolledContest.getTitle();

        for (User u : members) {
            String user = u.getUsername();
            for (String s : portfolios.get(user).keySet()) {

                String quantity = portfolios.get(user).get(s).get("Quantity");

                String closePrice = "0";
                try {
                    closePrice = ApiCall.getClosePrice(s, Credentials.apiKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (closePrice == null) {
                    closePrice = "0";
                }

                if (s.equals("Cash")) {closePrice = "1";}

                String value = String.valueOf(Float.parseFloat(quantity) * Float.parseFloat(closePrice));

                portfolios.get(user).get(s).put("Close Price", closePrice);
                portfolios.get(user).get(s).put("Value", value);

            } }

        Component[] components = ((JPanel) enrolledView.frame.getComponents()[0]).getComponents();
        assert(components.length == 7);
        assert(title.equals(((JLabel) components[0]).getText()));

        assert("Industry: Technology".equals(((JLabel) components[1]).getText()));
        assert("Description: EnrolledTest".equals(((JLabel) components[2]).getText()));
        assert("Contest ID: EnrolledTest".equals(((JLabel) components[3]).getText()));
    }


}
