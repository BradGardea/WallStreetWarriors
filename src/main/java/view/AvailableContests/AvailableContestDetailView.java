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

    private void checkNextPurchaseOverflow(Float updatedCost){
        if (calculateCashRemaining() < 0){
            this.stockQuantitySpinner.setValue(0);
            updateCurrentPortfolio(stockChoicesList.getSelectedValue().toString(), updatedCost);
        }
    }

    private float calculateCashRemaining(){
        var cashRemaining = 10000;
        for (var stock : currentPortfollio.keySet()) {
            var stockMetaData = currentPortfollio.get(stock);
            cashRemaining -= Float.parseFloat(stockMetaData.get("Quantity")) * Float.parseFloat(stockMetaData.get("Purchase Price"));
        }
        cashRemainingLabel.setText(String.valueOf(cashRemaining));
        return cashRemaining;
    }
    private void setListValues() {
        var keyset = this.viewModel.getState().getContestDetails().getStockOptions();
        var model = new String[keyset.size()];
        for(var i = 0; i < keyset.size(); i++){
            model[i] = keyset.get(i).toString();
        }
        stockChoicesList.setListData(model);
    }

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

    public void updateStockSelectionUI(String stockSelection) throws Exception {
        this.stockNameLabel.setText(stockSelection);
        if (this.currentPortfollio != null && this.currentPortfollio.getOrDefault(stockSelection, null) != null){
            this.stockQuantitySpinner.setValue(Integer.parseInt(this.currentPortfollio.get(stockSelection).get("Quantity")));
        }
        else{
            this.stockQuantitySpinner.setValue(0);
        }
        this.purchasePriceLabel.setText(Float.toString(controller.getUpdatedStockPrice(stockSelection))); //TODO: get stock cost here
    }
    public void setDefaultStockSelectionUiValues(){
        this.stockNameLabel.setText("Select a stock");
        this.stockQuantitySpinner.setValue(0);
        this.purchasePriceLabel.setText("N/A");
        this.totalCostLabel.setText("N/A");
        this.cashRemainingLabel.setText("10000");
    }

    private void onOK() {
        var cashMap = new HashMap<String, String>();
        cashMap.put("Quantity", cashRemainingLabel.getText());
        cashMap.put("Purchase Price", "1");
        currentPortfollio.put("Cash", cashMap);
        enrollSuccess = controller.enrollUserInContest(currentPortfollio);
        dispose();
    }

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
    public void forceDispose(){
        dispose();
    }
    public static void launch(AvailableContestDetailView dialog) throws IOException {
        dialog.setSize(new Dimension(600,800));
        dialog.setVisible(true);
//        System.exit(0);
    }

    private static String formatAsDateString(Date date) {
        // Choose your desired date format
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Format the date as a string
        return dateFormat.format(date);
    }

}
