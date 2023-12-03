package UseCase.AvailableContest;

import Api.ApiCall;
import Api.Credentials;
import FirebaseDataAccess.FirebaseDataAccess;
import entity.Contest;
import entity.User;

import java.util.HashMap;

public class AvailableContestInteractor implements AvailableContestInputBoundary{
    private AvailableContestOuputBoundary  availableContestOuputBoundary;
    private String contestId;
    private String username;

    public AvailableContestInteractor(AvailableContestOuputBoundary availableContestOuputBoundary, String contestId, String username ){
        this.availableContestOuputBoundary = availableContestOuputBoundary;
        this.contestId = contestId;
        this.username = username;
    }
    @Override
    public void execute() {
        var contest = FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", this.contestId);
        if (contest != null){
            availableContestOuputBoundary.prepareSuccess(contest);
        }
    }
    public boolean enrollUserInContest(HashMap<String, HashMap<String, String>> currentPortfolio){
        try{
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
        catch(Exception ex){
            return false;
        }

    }

    public float getUpdatedStockPrice(String stockName) throws RuntimeException{

        return Float.parseFloat(ApiCall.getClosePrice(stockName, Credentials.apiKey));

    }
}
