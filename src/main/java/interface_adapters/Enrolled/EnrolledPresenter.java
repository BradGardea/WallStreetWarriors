package interface_adapters.Enrolled;

import interface_adapters.ViewModelManager;
import use_case.Enrolled.EnrolledOutputBoundary;
import use_case.Enrolled.EnrolledOutputData;
import interface_adapters.Enrolled.EnrolledState;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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
    private ViewModelManager viewManagerModel;

    public EnrolledPresenter(EnrolledViewModel enrolledViewModel, ViewModelManager viewManagerModel) {
        this.enrolledViewModel = enrolledViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EnrolledOutputData enrolledOutputData) {
        EnrolledState enrolledState = enrolledViewModel.getState();

        enrolledState.setOpponents(enrolledOutputData.getOpponents());
        enrolledState.setUserPortfolio(enrolledOutputData.getUserPortfolio());
        enrolledState.setOpponentPortfolios(enrolledOutputData.getOpponentPortfolios());
        enrolledState.setStartDate(enrolledOutputData.getStartDate());
        enrolledState.setEndDate(enrolledOutputData.getEndDate());
        enrolledState.setTitle(enrolledOutputData.getTitle());
        enrolledState.setDescription(enrolledOutputData.getDescription());
        enrolledState.setContestId(enrolledOutputData.getContestId());
        enrolledState.setIndustry(enrolledOutputData.getIndustry());

        this.enrolledViewModel.setState(enrolledState);
        this.enrolledViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(enrolledViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
