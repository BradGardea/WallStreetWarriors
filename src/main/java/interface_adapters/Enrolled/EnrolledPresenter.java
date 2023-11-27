package main.java.interface_adapters.Enrolled;

import main.java.use_case.Enrolled.EnrolledOutputBoundary;
import main.java.use_case.Enrolled.EnrolledOutputData;
import main.java.interface_adapters.Enrolled.*;
import main.java.interface_adapters.;
import main.java.interface_adapters.Enrolled.EnrolledState;

/**
 *
 *
 *
 *
 * @author Mikhail Skazhenyuk
 * @version 0.0
 */
public class EnrolledPresenter implements EnrolledOutputBoundary {

    private final EnrolledViewModel enrolledViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public EnrolledPresenter(EnrolledViewModel enrolledViewModel, LoggedInViewModel loggedinViewModel, ViewManagerModel viewManagerModel) {
        this.enrolledViewModel = enrolledViewModel;
        this.loggedInViewModel = loggedinViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EnrolledOutputData enrolledOutputData) {
        EnrolledState enrolledState = enrolledViewModel.getState();

        // TODO set output data contest stuff
        enrolledState.setEndDate(enrolledOutputData.getEndDate());
        enrolledState.setOpponents(enrolledOutputData.getOpponents());
        enrolledState.setOpponentStocks(enrolledOutputData.getOpponentStocks());
        enrolledState.setStartDate(enrolledOutputData.getStartDate());
        enrolledState.setUserStocks(enrolledOutputData.getUserStocks());

        this.enrolledViewModel.setState(enrolledState);
        this.enrolledViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(enrolledViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
