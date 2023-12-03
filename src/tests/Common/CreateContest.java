package Common;


import app.Main;
import com.google.cloud.Timestamp;
import com.google.protobuf.util.Timestamps;
import entity.Contest;
import entity.User;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateContest {
    public static void createContest(int count){
        try {
            Main.FirebaseInit();
            var a = new User("a", "1", new ArrayList<>(), new ArrayList<>());
            for(var i = 0; i < count; i ++){
                var members = new ArrayList<User>();
                members.add(a);
                var currentTimestamp = Timestamps.fromMillis(System.currentTimeMillis());
                // Add 5 days to the current timestamp
                var fiveDays = Timestamp.fromProto(Timestamps.add(currentTimestamp, com.google.protobuf.Duration.newBuilder().setSeconds(5 * 24 * 60 * 60).build()));
                var stockOptions = new ArrayList<String>();
                stockOptions.add("GOOG");
                stockOptions.add("YAHOO");
                stockOptions.add("Foo");
                new Contest(String.valueOf(i), "Test", String.valueOf(i), members, "Technology", Timestamp.now(), fiveDays, stockOptions, null);
            }
            var members = new ArrayList<User>();
            members.add(a);
            var currentTimestamp = Timestamps.fromMillis(System.currentTimeMillis());
            // Add 5 days to the current timestamp
            var fiveDays = Timestamp.fromProto(Timestamps.add(currentTimestamp, com.google.protobuf.Duration.newBuilder().setSeconds(5 * 24 * 60 * 60).build()));
            var stockOptions = new ArrayList<String>();
            stockOptions.add("AAPL");
            stockOptions.add("MSFT");
            stockOptions.add("GOOG");

            HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = new HashMap<>();
            HashMap<String, HashMap<String, String>> innerPortfolio1 = new HashMap<>();
            HashMap<String, String> innerPortfolio2 = new HashMap<>();

            innerPortfolio2.put("Ticker", "AAPL");
            innerPortfolio2.put("Purchase Price", "100");
            innerPortfolio2.put("Quantity", "100");
            innerPortfolio2.put("End Price", "120");
            innerPortfolio2.put("Value", "12000");

            innerPortfolio1.put("AAPL", innerPortfolio2);

            portfolios.put("a", innerPortfolio1);

            var cT = new Contest("CompletedTest", "Test", "CompletedTest", members, "Technology", Timestamp.now(), fiveDays, stockOptions, portfolios);
            var eT = new Contest("EnrolledTest", "Test", "EnrolledTest", members, "Technology", Timestamp.now(), fiveDays, stockOptions, portfolios);
            a.addEnrolledContest(eT.getContestId());
            a.addCompletedContest(cT.getContestId());

            a.updateInStorage();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void main(String[] args) {
        createContest(2);
    }
}
