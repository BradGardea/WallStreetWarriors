package common;

import api.ApiCall;
import api.Credentials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CreateDummyPortfolios {

    public static HashMap<String, HashMap<String, HashMap<String, String>>> createDummyPorfolios(int amount, List<String> tickers, Double budget){
        // Precondition amount <= 30

        List<String> sampleUsernames = Arrays.asList(
                "TraderX", "Investor123", "StockGuru", "MarketMaster", "ProfitSeeker",
                "BullishBuddy", "CryptoKing", "WealthWizard", "EquityExplorer", "OptionOracle",
                "RiskTaker", "DividendDuke", "TechTrader", "AlphaInvestor", "MarketMaverick",
                "QuantQueen", "FuturesFanatic", "PennyProwler", "CapitalCommander", "DollarDynamo",
                "StrategySavvy", "CryptoCrafter", "MarketMaestro", "LeverageLord", "CashFlowCaptain",
                "ProfitsPilot", "AssetAdventurer", "WealthWanderer", "YieldYogi", "BlueChipBaller"
        );
        Random random = new Random();


        ArrayList<String> selectedUsernames = new ArrayList<>();
        int numCreated = 0;
        while (numCreated < amount){
            int randomIndex = random.nextInt(sampleUsernames.size());
            if (selectedUsernames.contains(sampleUsernames.get(randomIndex))){
                ;
            } else{
                selectedUsernames.add(sampleUsernames.get(randomIndex));
                numCreated += 1;
            }
        }
        // by the end of this loop selectedUsernames will be of size amount and will contain amount distinct usernames.

        // loop will iterate over the list of selected usernames and give them a portfolio.
        HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = new HashMap<>();

        for (String username: selectedUsernames){
            Double cashRemaining = budget.doubleValue(); // preventing aliasing
            HashMap<String, HashMap<String, String>> portfolio = new HashMap<>();
            while (cashRemaining > 500){

                String random_ticker = tickers.get(random.nextInt(tickers.size()));
                while (portfolio.keySet().contains(random_ticker)){
                    random_ticker = tickers.get(random.nextInt(tickers.size()));
                }
                String stock_price = ApiCall.getClosePrice(random_ticker, Credentials.apiKey);

                HashMap<String, String> innerPorfolio = new HashMap<>();
                innerPorfolio.put("Purchase Price", stock_price);


                Integer shares = 1;
                while (shares * Float.parseFloat(stock_price) < (budget * 0.1)){
                    shares += 1;
                }
                innerPorfolio.put("Quantity", shares.toString());
                portfolio.put(random_ticker, innerPorfolio);
                cashRemaining -= shares * Float.parseFloat(stock_price);
                }
            // adding the remaining cash to the porfolio
            HashMap<String, String> cashInner = new HashMap<>();
            cashInner.put("Purchase Price", "1");
            cashInner.put("Quantity", cashRemaining.toString());
            portfolio.put("Cash", cashInner);

            portfolios.put(username, portfolio);

            }

        return portfolios;

        }


    public static void main(String[] args) {
        List<String> techTickers = new ArrayList<>(Arrays.asList(
                "AAPL", "MSFT", "GOOGL", "AMZN", "FB", "TSLA", "NVDA", "INTC", "CSCO", "IBM",
                "ORCL", "ADBE", "CRM", "PYPL", "QCOM", "NFLX", "AMD", "UBER", "ZM", "SQ"
        ));
        System.out.println(createDummyPorfolios(2, techTickers, 10000.0));
    }


    };
