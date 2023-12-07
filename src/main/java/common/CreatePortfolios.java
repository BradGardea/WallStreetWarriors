package common;

import java.util.HashMap;

public class CreatePortfolios {

        public static HashMap<String, HashMap<String, HashMap<String, String>>> createPorfolios(){


            HashMap<String, HashMap<String, HashMap<String, String>>> portfolios = new HashMap<>();

            HashMap<String, HashMap<String, String>> innerPortfolioDummy = new HashMap<>();
            HashMap<String, String> innerPortfolioDummy2 = new HashMap<>();

            innerPortfolioDummy2.put("Ticker", "AAPL");
            innerPortfolioDummy2.put("Purchase Price", "100");
            innerPortfolioDummy2.put("Quantity", "100");
            innerPortfolioDummy2.put("End Price", "120");
            innerPortfolioDummy2.put("Value", "11000");

            innerPortfolioDummy.put("AAPL", innerPortfolioDummy2);

            portfolios.put("dummy", innerPortfolioDummy);

            HashMap<String, HashMap<String, String>> innerPortfolioDhruv = new HashMap<>();
            HashMap<String, String> innerPortfolioDhruv2 = new HashMap<>();

            innerPortfolioDhruv2.put("Ticker", "AAPL");
            innerPortfolioDhruv2.put("Purchase Price", "100");
            innerPortfolioDhruv2.put("Quantity", "100");
            innerPortfolioDhruv2.put("End Price", "120");
            innerPortfolioDhruv2.put("Value", "12000");

            innerPortfolioDhruv.put("AAPL", innerPortfolioDhruv2);

            portfolios.put("dhruv", innerPortfolioDhruv);


            HashMap<String, HashMap<String, String>> innerPortfolioBrad = new HashMap<>();
            HashMap<String, String> innerPortfolioBrad2 = new HashMap<>();

            innerPortfolioBrad2.put("Ticker", "MSFT");
            innerPortfolioBrad2.put("Purchase Price", "320");
            innerPortfolioBrad2.put("Quantity", "32");
            innerPortfolioBrad2.put("End Price", "300");
            innerPortfolioBrad2.put("Value", "9000");

            innerPortfolioBrad.put("MSFT", innerPortfolioBrad2);
            portfolios.put("brad", innerPortfolioBrad);

            HashMap<String, HashMap<String, String>> innerPortfolioMikhail = new HashMap<>();
            HashMap<String, String> innerPortfolioMikhail2 = new HashMap<>();

            innerPortfolioMikhail2.put("Ticker", "TSLA");
            innerPortfolioMikhail2.put("Purchase Price", "120");
            innerPortfolioMikhail2.put("Quantity", "83");
            innerPortfolioMikhail2.put("End Price", "130");
            innerPortfolioMikhail2.put("Value", "10790");

            innerPortfolioMikhail.put("TSLA", innerPortfolioMikhail2);

            portfolios.put("mikhail", innerPortfolioMikhail);

            HashMap<String, HashMap<String, String>> innerPortfolioGoncalo = new HashMap<>();
            HashMap<String, String> innerPortfolioGoncalo2 = new HashMap<>();

            innerPortfolioGoncalo2.put("Ticker", "GOOG");
            innerPortfolioGoncalo2.put("Purchase Price", "80");
            innerPortfolioGoncalo2.put("Quantity", "125");
            innerPortfolioGoncalo2.put("End Price", "85");
            innerPortfolioGoncalo2.put("Value", "10625");

            innerPortfolioGoncalo.put("GOOG", innerPortfolioGoncalo2);

            portfolios.put("goncalo", innerPortfolioGoncalo);

            return portfolios;

        }

}
