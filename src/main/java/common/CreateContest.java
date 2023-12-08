package common;


import app.Main;
import com.google.cloud.Timestamp;
import com.google.protobuf.util.Timestamps;
import entity.Contest;
import entity.User;
import firebaseDataAccess.FirebaseDataAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class CreateContest {

    /**
     * Initializes default contests with a given size parameter.
     *
     * This method first invokes the Firebase initialization to set up the necessary Firebase
     * environment. It then populates a HashMap with ticker symbols categorized by industry sectors
     * such as Technology, Finance, Healthcare, Energy, and Consumer Goods.
     *
     * After the ticker symbols are added, the method proceeds to create dummy portfolios for a
     * specified number of contests up to the provided 'size' parameter. Each contest is populated
     * with a specific number of portfolios based on an alternating pattern (3 portfolios for odd
     * indices and 2 for even indices). User objects are created and added to each contest, and a
     * timestamp for contest duration is set.
     *
     * The method handles the creation of contests, assigning them unique identifiers, names, and
     * member lists, along with start and end times.
     *
     * @param size The number of contests to initialize. It is bounded by the number of industries
     *             provided in the tickerSymbolMap.
     * @throws IOException If Firebase initialization encounters any IO problems.
     */
    public static void initDefaultContests(int size) throws IOException {
        Main.FirebaseInit();
        HashMap<String, ArrayList<String>> tickerSymbolMap = new HashMap<>();

        // Add ticker symbols for different industries
        addTickerSymbol(tickerSymbolMap, "Technology",
                "AAPL", "GOOGL", "MSFT", "AMZN", "FB", "TSLA", "NVDA", "INTC", "CSCO", "IBM",
                "ORCL", "HPQ", "QCOM", "TXN", "ADBE", "CRM", "NFLX", "AMD", "NOW", "UBER",
                "TWTR", "SNAP", "ZM", "PYPL", "SPOT", "EA", "ATVI", "V", "MA", "INTU");

        addTickerSymbol(tickerSymbolMap, "Finance",
                "JPM", "BAC", "GS", "C", "MS", "WFC", "AXP", "BLK", "PNC", "USB",
                "BK", "SCHW", "COF", "CME", "MET", "AIG", "PRU", "SPGI", "TROW", "ICE",
                "DFS", "MCO", "ALLY", "AMP", "RE", "HBAN", "FITB", "STT", "KEY", "NTRS");

        addTickerSymbol(tickerSymbolMap, "Healthcare",
                "JNJ", "PFE", "UNH", "MRK", "ABBV", "AMGN", "GILD", "CVS", "LLY", "BMY",
                "MDT", "TMO", "ABT", "SYK", "CI", "ISRG", "VRTX", "DHR", "HUM",
                "REGN", "BIIB", "ZTS", "IDXX", "BAX", "EW", "BSX", "ALGN");

        addTickerSymbol(tickerSymbolMap, "Energy",
                "XOM", "CVX", "BP", "COP", "EOG", "SLB", "KMI", "PXD",
                "OXY", "HAL", "WMB", "VLO", "PSX", "MPC", "ET", "CHK", "HES", "DVN",
                "APA", "NOV", "RRC", "MRO");

        addTickerSymbol(tickerSymbolMap, "Consumer Goods",
                "PG", "KO", "PEP", "NKE", "MCD", "UL", "CL", "PM", "HD", "DIS",
                "F", "GM", "TM", "HMC", "NSANY", "TSLA", "FORD", "RACE", "HOG",
                "AAP", "AMZN", "WMT", "COST", "TGT", "SBUX", "MNST", "PEP", "KHC", "GIS");

        if (size > tickerSymbolMap.size()) size = tickerSymbolMap.size();
        for (var i = 0; i < size; i++) {

            var ammount = 3;
            if (i % 2 == 0){
                ammount = 2;
            }
            var porfolios = CreateDummyPortfolios.createDummyPorfolios(ammount, tickerSymbolMap.get(tickerSymbolMap.keySet().toArray()[i]), 10000.0);
            var usernames = porfolios.keySet().toArray();
            var members = new ArrayList<User>();
            for (var username: usernames){
                members.add(new User(username.toString(), "1",  new ArrayList<>(Arrays.asList("D" + String.valueOf(i))), new ArrayList<>()));
            }
            var currentTimestamp = Timestamp.now().toProto();
            // Add 5 days to the current timestamp
            var endTime = Timestamp.fromProto(Timestamps.add(currentTimestamp, com.google.protobuf.Duration.newBuilder().setSeconds(5 * 24 * 60 * 60).build()));
            if (i == 3 || i == 4){
                endTime = Timestamp.fromProto(Timestamps.add(currentTimestamp, com.google.protobuf.Duration.newBuilder().setSeconds(6 * 60).build()));
            }
            new Contest("D" + String.valueOf(i), String.valueOf(i), "Open Contest for: " + tickerSymbolMap.keySet().toArray()[i].toString(),  members, tickerSymbolMap.keySet().toArray()[i].toString(), Timestamp.now(), endTime, tickerSymbolMap.get(tickerSymbolMap.keySet().toArray()[i]), porfolios);
        }
    }

    private static void addTickerSymbol(HashMap<String, ArrayList<String>> map, String industry, String... tickerSymbols) {
        // If industry key doesn't exist, create a new ArrayList
        map.computeIfAbsent(industry, k -> new ArrayList<>());

        // Add ticker symbols to the ArrayList under the industry key
        for (String ticker : tickerSymbols) {
            map.get(industry).add(ticker);
        }
    }

    /**
     * Creates a specified number of contests using predefined settings.
     *
     * This method initializes Firebase (if not already done) and creates a series of contests.
     * It creates a user 'a' to add to these contests. For each contest, the method sets up a
     * list of members (initially only containing 'a'), a timestamp for the current time, and
     * an end time set to 5 days after the current time. It also pre-defines a list of stock
     * options ('AAPL', 'MSFT', 'GOOG') for the contest participants.
     *
     * After creating the specified number of contests, it additionally creates two special
     * contests: 'CompletedTest' and 'EnrolledTest'. These contests have similar settings but
     * are distinguished by their names and the status of the user 'a' in relation to them.
     * 'a' is marked as enrolled in 'EnrolledTest' and as having completed 'CompletedTest'.
     *
     * Each contest is instantiated with a unique identifier, name, member list, industry category,
     * start and end times, stock options, and for the special contests, predefined portfolios.
     *
     * @param count The number of generic contests to create. Does not include the two special contests.
     */
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
                stockOptions.add("AAPL");
                stockOptions.add("MSFT");
                stockOptions.add("GOOG");
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

            var cT = new Contest("CompletedTest", "Test", "CompletedTest", members, "Technology", Timestamp.now(), Timestamp.now(), stockOptions, portfolios);
            var eT = new Contest("EnrolledTest", "Test", "EnrolledTest", members, "Technology", Timestamp.now(), fiveDays, stockOptions, portfolios);
            a.addEnrolledContest(eT.getContestId());
            a.addCompletedContest(cT.getContestId());

            a.updateInStorage();
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    public static void main(String[] args) { //This code will have errors for larger inputs.
        try{
            initDefaultContests(5);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
