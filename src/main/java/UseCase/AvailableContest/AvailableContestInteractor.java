package UseCase.AvailableContest;

import FirebaseDataAccess.FirebaseDataAccess;
import entity.Contest;
import entity.User;

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
    public boolean enrollUserInContest(){
        try{
            var contest = FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", this.contestId);
            var user = FirebaseDataAccess.getInstance().getEntity(User.class, "Users", this.username);

            if (user != null && contest != null){
                contest.addMember(user);
                user.addEnrolledContest(this.contestId);

                contest.updateInFirebase();
                user.updateInFirebase();
                return true;
            }
            return false;
        }
        catch(Exception ex){
            return false;
        }

    }

    public float getUpdatedStockPrice(String stockName){
        return 100; //TODO: api call here
    }
}
