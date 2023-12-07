package view.AvailableContests;

import interfaceAdapters.AvailableContests.AvailableContestState;
import interfaceAdapters.AvailableContests.AvailableContestsController;
import interfaceAdapters.AvailableContests.AvailableContestsViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class AvailableContestDetailView extends JDialog implements PropertyChangeListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel contestIdLabel;
    private JLabel industryLabel;
    private JLabel numberOfPlayersLabel;
    private JLabel startTimeLabel;
    private JLabel endTimeLabel;
    private JLabel cashRemainingLabel;
    private JList stockChoicesList;
    private JSpinner stockQuantitySpinner;
    private JLabel stockNameLabel;
    private JLabel totalCostLabel;
    private JLabel purchasePriceLabel;
    private JLabel ErrorLabel;
    private final AvailableContestsViewModel viewModel;
    private final AvailableContestsController controller;
    public boolean enrollSuccess; //TODO: move to state
    public static final String viewName = "availableContestDetailView";
    private HashMap<String, HashMap<String, String>> currentPortfollio = new HashMap<String, HashMap<String, String>>(); //StockTickerName: {StockTickerMetaDataName: StockTickerMetaDataName}

    public AvailableContestDetailView(AvailableContestsController controller, AvailableContestsViewModel viewModel, boolean setModal) {
        this.viewModel = viewModel;
        this.controller = controller;
        viewModel.addPropertyChangeListener(this); //trigger events based on state change

        setContentPane(contentPane);
        setModal(setModal);
        getRootPane().setDefaultButton(buttonOK);

        ErrorLabel.setVisible(false);
        setListValues();
        setUiValues();
        setDefaultStockSelectionUiValues();

        stockChoicesList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()){
                            Float updatedCost = null;
                            try {
                                updatedCost = getUpdatedStockPrices(stockChoicesList.getSelectedValue().toString());
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }

                            try {
                                updateStockSelectionUI(stockChoicesList.getSelectedValue().toString());
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            checkNextPurchaseOverflow(updatedCost);
                        }
                    }
        });

        // Add current changes to currentPortfolio
        stockQuantitySpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (Integer.parseInt(stockQuantitySpinner.getValue().toString()) < 0) {
                    stockQuantitySpinner.setValue(0);
                }
                else if (stockChoicesList.getSelectedValue() != null){
                    var currentStock = stockChoicesList.getSelectedValue().toString();
                    var updatedCost = getUpdatedStockPrices(currentStock);

                    updateCurrentPortfolio(currentStock, updatedCost);
                    checkNextPurchaseOverflow(updatedCost);
                    totalCostLabel.setText(String.valueOf(Float.parseFloat(stockQuantitySpinner.getValue().toString()) * updatedCost));
                }
            }
        });


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    public String getContestIdLabel(){
        return this.contestIdLabel.getText();
    }
    public JList getStockChoicesList(){
        return this.stockChoicesList;
    }
    public String getTotalCostLabel(){
        return this.totalCostLabel.getText();
    }
    public JButton getButtonOK(){
        return this.buttonOK;
    }
    public JButton getButtonCancel(){
        return this.buttonOK;
    }
    public JSpinner getStockQuantitySpinner() {
        return stockQuantitySpinner;
    }

    public HashMap<String, HashMap<String, String>> getCurrentPortfollio() {
        return currentPortfollio;
    }

    public String getStockNameLabel(){
        return this.stockNameLabel.getText();
    }
    public AvailableContestsViewModel getViewModel(){
        return this.viewModel;
    }

    /**
     * Updates the current portfolio with the given stock and cost.
     *
     * @param  currentStock  the stock to be updated in the portfolio
     * @param  updatedCost   the updated cost of the stock
     */
    private void updateCurrentPortfolio(String currentStock, Float updatedCost){
        if (currentPortfollio.get(currentStock) != null){
            var currentStockMetaData = currentPortfollio.get(currentStock);
            currentStockMetaData.put("Quantity", stockQuantitySpinner.getValue().toString());
            currentStockMetaData.put("Purchase Price", String.valueOf(updatedCost));
        }
        else{
            var newCurrentStockMetaData = new HashMap<String, String>();
            newCurrentStockMetaData.put("Quantity", stockQuantitySpinner.getValue().toString());
            newCurrentStockMetaData.put("Purchase Price", String.valueOf(updatedCost));
            currentPortfollio.put(currentStock, newCurrentStockMetaData);
        }
    }

    /**
     * Check if the next purchase will cause an overflow in the cost.
     *
     * @param  updatedCost  the updated cost of the purchase
     */
    private void checkNextPurchaseOverflow(Float updatedCost){
        if (calculateCashRemaining() < 0){
            this.stockQuantitySpinner.setValue(0);
            updateCurrentPortfolio(stockChoicesList.getSelectedValue().toString(), updatedCost);
        }
    }

    /**
     * Calculates the remaining cash by subtracting the total value of stocks from the initial cash amount.
     *
     * @return  the remaining cash after deducting the value of stocks
     */
    private float calculateCashRemaining(){
        var cashRemaining = 10000;
        for (var stock : currentPortfollio.keySet()) {
            var stockMetaData = currentPortfollio.get(stock);
            cashRemaining -= Float.parseFloat(stockMetaData.get("Quantity")) * Float.parseFloat(stockMetaData.get("Purchase Price"));
        }
        cashRemainingLabel.setText(String.valueOf(cashRemaining));
        return cashRemaining;
    }

    /**
     * Sets the values of the JList based on the stock options in the contest details.
     *
     */
    private void setListValues() {
        var keyset = this.viewModel.getState().getContestDetails().getStockOptions();
        var model = new String[keyset.size()];
        for(var i = 0; i < keyset.size(); i++){
            try{
                if (controller.getUpdatedStockPrice(keyset.get(i).toString()) > 0) //Go through the controller directly to avoid error label
                    model[i] = keyset.get(i).toString();
            }
            catch (Exception ex){
                System.out.println("Could not find stock: " + keyset.get(i).toString());
            }
        }
        stockChoicesList.setListData(model);
    }

    /**
     * Retrieves the updated stock prices for a given stock.
     *
     * @param  stockName  the name of the stock
     * @return            the updated stock price as a float
     */
    public float getUpdatedStockPrices(String stockName){
        try{
            ErrorLabel.setVisible(false);
            return controller.getUpdatedStockPrice(stockName);
        } catch (Exception ex){
            System.out.println(ex);
            ErrorLabel.setVisible(true);
            ErrorLabel.setText("Unable to get updated price");
            return 0;
        }
    }

    /**
     * Set the UI values based on the contest configuration.
     *
     */
    public void setUiValues(){
        // Populate view with default params
        var contestConfig = this.viewModel.getState().getContestDetails();
        this.industryLabel.setText(contestConfig.getIndustry());
        this.numberOfPlayersLabel.setText(Integer.toString(contestConfig.getMembers().size()));
        this.contestIdLabel.setText(contestConfig.getContestId());

        String startDateString = formatAsDateString(contestConfig.getStartTime().toDate());
        String endDateString = formatAsDateString(contestConfig.getEndTime().toDate());
        this.startTimeLabel.setText(startDateString);
        this.endTimeLabel.setText(endDateString);
    }

    /**
     * Updates the stock selection UI with the given stock selection.
     *
     * @param  stockSelection  the stock selection to update the UI with
     * @throws Exception      if an error occurs during the update process
     */
    public void updateStockSelectionUI(String stockSelection) throws Exception {
        this.stockNameLabel.setText(stockSelection);
        if (this.currentPortfollio != null && this.currentPortfollio.getOrDefault(stockSelection, null) != null){
            this.stockQuantitySpinner.setValue(Integer.parseInt(this.currentPortfollio.get(stockSelection).get("Quantity")));
        }
        else{
            this.stockQuantitySpinner.setValue(0);
        }
        this.purchasePriceLabel.setText(Float.toString(getUpdatedStockPrices(stockSelection)));
    }

    /**
     * Sets the default values for the stock selection user interface.
     *
     * This function sets the text of the stockNameLabel to "Select a stock",
     * sets the value of the stockQuantitySpinner to 0,
     * sets the text of the purchasePriceLabel to "N/A",
     * sets the text of the totalCostLabel to "N/A",
     * and sets the text of the cashRemainingLabel to "10000".
     */
    public void setDefaultStockSelectionUiValues(){
        this.stockNameLabel.setText("Select a stock");
        this.stockQuantitySpinner.setValue(0);
        this.purchasePriceLabel.setText("N/A");
        this.totalCostLabel.setText("N/A");
        this.cashRemainingLabel.setText("10000");
    }

    /**
     * Sets the users portoflio and pushes the contest to enrolled.
     *
     * @param  None
     * @return None
     */
    private void onOK() {
        var cashMap = new HashMap<String, String>();
        cashMap.put("Quantity", cashRemainingLabel.getText());
        cashMap.put("Purchase Price", "1");
        currentPortfollio.put("Cash", cashMap);
        enrollSuccess = controller.enrollUserInContest(currentPortfollio);
        dispose();
    }

    /**
     * Disposes of the JDialog.
     *
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AvailableContestState state = (AvailableContestState) evt.getNewValue();
        if (state.getContestDetails() != null && !state.getContestDetails().equals(this.viewModel.getState())){
            setUiValues();
        }
    }

    /**
     * A description of the entire Java function.
     *
     */
    public void forceDispose(){
        dispose();
    }
    public static void launch(AvailableContestDetailView dialog) throws IOException {
        dialog.setSize(new Dimension(600,1000));
        dialog.setVisible(true);
//        System.exit(0);
    }

    /**
     * Formats the given date as a string in the specified format.
     *
     * @param  date  the date to be formatted
     * @return       the formatted date string
     */
    private static String formatAsDateString(Date date) {
        // Choose your desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the date as a string
        return dateFormat.format(date);
    }

}
