package InterfaceAdapters.Enrolled;

import InterfaceAdapters.ViewModelManager;
import UseCase.EnrolledContest.EnrolledOutputBoundary;
import UseCase.EnrolledContest.EnrolledOutputData;

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
        enrolledState.setPortfolios(enrolledOutputData.getPortfolios());
        enrolledState.setStartDate(enrolledOutputData.getStartDate());
        enrolledState.setEndDate(enrolledOutputData.getEndDate());
        enrolledState.setTitle(enrolledOutputData.getTitle());
        enrolledState.setDescription(enrolledOutputData.getDescription());
        enrolledState.setContestId(enrolledOutputData.getContestId());
        enrolledState.setIndustry(enrolledOutputData.getIndustry());
        enrolledState.setUsername(enrolledOutputData.getUsername());

        this.enrolledViewModel.setState(enrolledState);
        this.enrolledViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(enrolledViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
