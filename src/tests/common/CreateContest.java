package common;


import app.Main;
import com.google.cloud.Timestamp;
import com.google.protobuf.util.Timestamps;
import entity.Contest;
import entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateContest {
    public static void initDefaultContests() throws IOException {
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
                "MDT", "TMO", "ABT", "SYK", "CI", "ISRG", "ANTM", "VRTX", "DHR", "HUM",
                "REGN", "BIIB", "CELG", "ZTS", "IDXX", "BAX", "EW", "BSX", "ALXN", "ALGN");

        addTickerSymbol(tickerSymbolMap, "Energy",
                "XOM", "CVX", "RDS.A", "TOT", "BP", "COP", "EOG", "SLB", "KMI", "PXD",
                "OXY", "HAL", "WMB", "VLO", "PSX", "MPC", "ET", "CHK", "HES", "DVN",
                "APA", "NOV", "EOCC", "RRC", "COG", "CXO", "XEC", "MRO", "CLR", "HFC");

        addTickerSymbol(tickerSymbolMap, "Consumer Goods",
                "PG", "KO", "PEP", "NKE", "MCD", "UL", "CL", "PM", "HD", "DIS",
                "F", "GM", "FCAU", "TM", "HMC", "NSANY", "TSLA", "FORD", "RACE", "HOG",
                "AAP", "AMZN", "WMT", "COST", "TGT", "SBUX", "MNST", "PEP", "KHC", "GIS");

        var dummyEnrolled = new ArrayList<String>();
        dummyEnrolled.add("0");
        dummyEnrolled.add("1");
        dummyEnrolled.add("2");
        dummyEnrolled.add("3");
        for (var i = 0; i < tickerSymbolMap.size() - 1; i++) {

//            var d1 = new User("dummy1", "1", new ArrayList<>(), dummyEnrolled);
//            var d2 = new User("dummy2", "1", new ArrayList<>(), dummyEnrolled);
//
//            var members = new ArrayList<User>();
//            members.add(d1);
//            members.add(d2);

            var currentTimestamp = Timestamps.fromMillis(System.currentTimeMillis());
            // Add 5 days to the current timestamp
            var endTime = Timestamp.fromProto(Timestamps.add(currentTimestamp, com.google.protobuf.Duration.newBuilder().setSeconds(5 * 24 * 60 * 60).build()));
            if (i == 3){
                endTime = Timestamp.fromProto(Timestamps.add(currentTimestamp, com.google.protobuf.Duration.newBuilder().setSeconds(2 * 60).build()));
            }
            new Contest(String.valueOf(i), String.valueOf(i), "Default contest: " + String.valueOf(i),  new ArrayList<>(), tickerSymbolMap.keySet().toArray()[i].toString(), Timestamp.now(), endTime, tickerSymbolMap.get(tickerSymbolMap.keySet().toArray()[i]), new HashMap<String, HashMap<String, HashMap<String, String>>>());

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
        try{
            initDefaultContests();
            createContest(2);
        }
        catch (Exception ex){
            System.out.println(ex);
        }

    }
}
