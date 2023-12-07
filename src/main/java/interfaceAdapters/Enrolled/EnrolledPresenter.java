package interfaceAdapters.Enrolled;

import interfaceAdapters.ViewModelManager;
import useCase.EnrolledContest.EnrolledOutputBoundary;
import useCase.EnrolledContest.EnrolledOutputData;

/**
 * Presenter class for the "Enrolled" view in an application, implementing the EnrolledOutputBoundary interface.
 * This class is responsible for handling the presentation logic for the view that displays enrolled contests.
 *
 * The presenter updates the enrolled view model with data received from the use case and instructs the view model
 * manager to update the user interface.
 *
 * @author Mikhail Skazhenyuk
 * @version 1.0
 */
public class EnrolledPresenter implements EnrolledOutputBoundary {

    private final EnrolledViewModel enrolledViewModel;
    private ViewModelManager viewManagerModel;

    /**
     * Constructs an EnrolledPresenter with specified view model and view model manager.
     *
     * @param enrolledViewModel The view model for the enrolled view.
     * @param viewManagerModel The view model manager responsible for managing multiple view models.
     */
    public EnrolledPresenter(EnrolledViewModel enrolledViewModel, ViewModelManager viewManagerModel) {
        this.enrolledViewModel = enrolledViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the enrolled output data.
     * This method updates the enrolled view model with data received from the enrolled output data.
     * It sets various state properties like opponents, portfolios, start and end dates, contest details,
     * and then notifies the view model manager to update the view.
     *
     * @param enrolledOutputData The data output from an enrolled operation, containing contest and user-related details.
     */
    @Override
    public void prepareSuccessView(EnrolledOutputData enrolledOutputData) {
        EnrolledState enrolledState = enrolledViewModel.getState();

        enrolledState.setOpponents(enrolledOutputData.getOpponents());
        enrolledState.setPortfolios(enrolledOutputData.getPortfolios());
        enrolledState.setStartDate(enrolledOutputData.getStartDate());
        enrolledState.setEndDate(enrolledOutputData.getEndDate());
        enrolledState.setTitle(enrolledOutputData.getTitle());
        enrolledState.setDescription(enrolledOutputData.getDescription());
        enrolledState.setContestId(enrolledOutputData.getContestId());
        enrolledState.setIndustry(enrolledOutputData.getIndustry());
        enrolledState.setUsername(enrolledOutputData.getUsername());
        enrolledState.setTimeLeft(enrolledOutputData.getTimeLeft());

        this.enrolledViewModel.setState(enrolledState);
        this.enrolledViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(enrolledViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
