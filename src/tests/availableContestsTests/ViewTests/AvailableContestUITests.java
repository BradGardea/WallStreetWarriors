package availableContestsTests.ViewTests;

import app.ContestUseCaseFactory;
import common.CreateContest;
import entity.Contest;
import entity.User;
import firebaseDataAccess.FirebaseDataAccess;
import interfaceAdapters.AvailableContests.AvailableContestPresenter;
import interfaceAdapters.AvailableContests.AvailableContestState;
import interfaceAdapters.AvailableContests.AvailableContestsController;
import interfaceAdapters.AvailableContests.AvailableContestsViewModel;
import interfaceAdapters.ViewModelManager;
import useCase.AvailableContest.AvailableContestInputData;
import useCase.AvailableContest.AvailableContestInteractor;
import useCase.AvailableContest.AvailableContestOutputData;
import view.AvailableContests.AvailableContestDetailView;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.Objects;

public class AvailableContestUITests {
    public FirebaseDataAccess firebaseDataAccess;
    public AvailableContestPresenter availableContestsPresenter;
    public AvailableContestsViewModel availableContestsViewModel;

    public ViewModelManager viewModelManager;
    public AvailableContestInteractor availableContestInteractor;
    public AvailableContestsController availableContestsController;
    public AvailableContestDetailView availableContestDetailView;
    public AvailableContestUITests(){
        CreateContest.createContest(1);
        new User("dummy", "dummy", new ArrayList<>(), new ArrayList<>());
        this.firebaseDataAccess = FirebaseDataAccess.getInstance();
        this.availableContestsViewModel = new AvailableContestsViewModel();
        this.viewModelManager = new ViewModelManager();
        this.availableContestsPresenter = new AvailableContestPresenter(this.availableContestsViewModel, this.viewModelManager );

        var availableContestInputData = new AvailableContestInputData("dummy", "0");
        this.availableContestInteractor = new AvailableContestInteractor(this.availableContestsPresenter, availableContestInputData);
        this.availableContestsController = new AvailableContestsController(this.availableContestInteractor);
        this.availableContestDetailView = new AvailableContestDetailView(this.availableContestsController, availableContestsViewModel, false);
    }

    @org.junit.Test
    public void construct(){
        var detailView = new AvailableContestDetailView(this.availableContestsController, availableContestsViewModel, false);
        assert(detailView != null);
    }

    @org.junit.Test
    public void launch() {
        try{
            AvailableContestDetailView.launch(availableContestDetailView);
            availableContestDetailView.forceDispose();
            assert(true);
        }
        catch (Exception ex){
            assert(false);
        }
    }

    @org.junit.Test
    public void getUpdatedStockPrices() {
        try{
            AvailableContestDetailView.launch(availableContestDetailView);
            availableContestDetailView.forceDispose();
            assert(availableContestDetailView.getUpdatedStockPrices("AAPL") != 0);
        }
        catch (Exception ex){
            assert(false);
        }
    }

    @org.junit.Test
    public void setUiValues() {
        try{
            AvailableContestDetailView.launch(availableContestDetailView);
            availableContestDetailView.forceDispose();
            assert(availableContestDetailView.getContestIdLabel() != "Label");
        }
        catch (Exception ex){
            assert(false);
        }
    }

    @org.junit.Test
    public void updateStockSelectionUI() {
        try{
            AvailableContestDetailView.launch(availableContestDetailView);
            availableContestDetailView.getStockChoicesList().setSelectedValue("AAPL", false);
            availableContestDetailView.forceDispose();
            assert(Objects.equals(availableContestDetailView.getStockNameLabel(), "AAPL"));
        }
        catch (Exception ex){
            assert(false);
        }
    }

    @org.junit.Test
    public void setDefaultStockSelectionUiValues() {
        try{
            AvailableContestDetailView.launch(availableContestDetailView);
            availableContestDetailView.setDefaultStockSelectionUiValues();
            availableContestDetailView.forceDispose();
            assert(Objects.equals(availableContestDetailView.getTotalCostLabel(), "N/A"));
        }
        catch (Exception ex){
            assert(false);
        }
    }

    @org.junit.Test
    public void propertyChange() {
        try{
            AvailableContestDetailView.launch(availableContestDetailView);
            var contest = FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", "0");
            var outputData = new AvailableContestOutputData(contest.getContestId(), contest.getTitle(), contest.getDescription() + "foo", contest.getMembers(), contest.getIndustry(), contest.getStartTime(), contest.getEndTime(), contest.getStockOptions(), contest.getPortfolios());
            var newState = new AvailableContestState(outputData);
            var propChange = new PropertyChangeEvent(this, null, null, newState);
            availableContestDetailView.getViewModel().setState(newState);
            availableContestDetailView.getViewModel().firePropertyChanged();
            availableContestDetailView.propertyChange(propChange);
            availableContestDetailView.forceDispose();
            assert(Objects.equals(availableContestDetailView.getViewModel().getState().getContestDetails().getDescription(), contest.getDescription() + "foo"));
        }
        catch (Exception ex){
            assert(false);
        }
    }
}
