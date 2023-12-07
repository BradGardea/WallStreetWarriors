package common;

import entity.Contest;
import firebaseDataAccess.FirebaseDataAccess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateContestTest {

    @Test
    void initDefaultContests() {
        try{
            CreateContest.initDefaultContests(1);
            assert(FirebaseDataAccess.getInstance().getEntity(Contest.class, "Contests", "D0") != null);
        }
        catch (Exception ex){
            assert(false);
        }
    }
}