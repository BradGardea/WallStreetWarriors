package useCase.AvailableContest;

import api.ApiCall;
import api.Credentials;
import firebaseDataAccess.FirebaseDataAccess;
import entity.Contest;
import entity.User;

import java.util.HashMap;

public class AvailableContestInteractor implements AvailableContestInputBoundary{
    private AvailableContestOutputBoundary availableContestOutputBoundary;
    private String contestId;
    private String username;

    public AvailableContestInteractor(AvailableContestOutputBoundary availableContestOutputBoundary, AvailableContestInputData availableContestInputData ){
        this.availableContestOutputBoundary = availableContestOutputBoundary;
        this.contestId = availableContestInputData.getContestId();
        this.username = availableContestInputData.getUsername();
    }
    @Override
    public void execute() {
        var contest = FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", this.contestId);
        var contestOutput = new AvailableContestOutputData(contest.getContestId(), contest.getTitle(), contest.getDescription(), contest.getMembers(), contest.getIndustry(), contest.getStartTime(), contest.getEndTime(), contest.getStockOptions(), contest.getPortfolios());
        if (contestOutput != null){
            availableContestOutputBoundary.prepareSuccess(contestOutput);
        }
    }
    public boolean enrollUserInContest(HashMap<String, HashMap<String, String>> currentPortfolio){
            var contest = FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", this.contestId);
            var user = FirebaseDataAccess.getInstance().getEntity(User.class, "Users", this.username);

            if (user != null && contest != null){
                contest.addMember(user);
                contest.setPortfolios(this.username, currentPortfolio);
                user.addEnrolledContest(this.contestId);

                contest.updateInStorage();
                user.updateInStorage();
                return true;
            }
            return false;
    }

    public float getUpdatedStockPrice(String stockName) throws RuntimeException{
        return Float.parseFloat(ApiCall.getClosePrice(stockName, Credentials.apiKey));
    }

    public String getUsername(){
        return this.username;
    }
    public String getContestId(){
        return this.contestId;
    }
}
