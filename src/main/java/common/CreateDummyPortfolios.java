package common;

import api.ApiCall;
import api.Credentials;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CreateDummyPortfolios {

    /**
     * Creates dummy portfolios for a specified amount of users.
     *
     * @param  amount   the number of portfolios to create (precondition: amount <= 30)
     * @param  tickers  the list of stock tickers to choose from
     * @param  budget   the budget for each portfolio
     * @return          a HashMap containing the created portfolios for each user
     */
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

                if (stock_price != null){
                    HashMap<String, String> innerPorfolio = new HashMap<>();
                    innerPorfolio.put("Purchase Price", stock_price);


                    Integer shares = 1;
                    while (shares * Float.parseFloat(stock_price) < (budget * 0.1) ){
                        shares += 1;
                    }
                    // calculating the cash remaining
                    cashRemaining -= shares * Float.parseFloat(stock_price);

                    // while loop to adjust shares if cash_remaining is negative
                    while (cashRemaining < 0){
                        shares -= 1;
                        cashRemaining += Float.parseFloat(stock_price);
                    }

                    innerPorfolio.put("Quantity", shares.toString());
                    portfolio.put(random_ticker, innerPorfolio);

                }
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

    };
