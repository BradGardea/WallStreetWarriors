package view.AvailableContests;

import entity.User;
import interface_adapters.AvailableContests.AvailableContestState;
import interface_adapters.AvailableContests.AvailableContestsController;
import interface_adapters.AvailableContests.AvailableContestsViewModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
    private final AvailableContestsViewModel viewModel;
    private final AvailableContestsController controller;
    private final User user;
    private HashMap<String, HashMap<String, String>> currentPortfollio; //StockTickerName: {StockTickerMetaDataName: StockTickerMetaDataName}

    public AvailableContestDetailView(AvailableContestsViewModel viewModel, AvailableContestsController controller, User user) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.user = user;
        viewModel.addPropertyChangeListener(this); //trigger events based on state change

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        stockChoicesList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getSource().equals(stockChoicesList)){
                            controller.stockListChanged(e); //API call to get update prices here
                            updateStockSelectionUI(e.toString()); // Add extra param with api call here
                        }
                    }
        });

        // Add current changes to currentPortfolio
        stockQuantitySpinner.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                var currentStock = stockChoicesList.getSelectedValue().toString();
                if (currentPortfollio.getOrDefault(currentStock, null) != null){
                    var currentStockMetaData = currentPortfollio.get(currentStock);
                    currentStockMetaData.put("quantity", evt.getNewValue().toString());
                }
                else{
                    var newCurrentStockMetaData = new HashMap<String, String>();
                    newCurrentStockMetaData.put("quantity", evt.getNewValue().toString());
                    currentPortfollio.put(currentStock, newCurrentStockMetaData);
                }

//                totalCostLabel.setText();
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
    public void getUpdatedStockPrices(String stockName){
        controller.getUpdatedStockPrices(stockName);
    }
    public void setUiValues(){
        // Populate view with default params
        var contestConfig = this.viewModel.getState().getContestDetails();
        this.industryLabel.setText(contestConfig.getIndustry());
        this.numberOfPlayersLabel.setText(Integer.toString(contestConfig.getMembers().size()));
        this.contestIdLabel.setText(contestConfig.getContestId());
        this.startTimeLabel.setText(contestConfig.getStartTime().toString());
        this.endTimeLabel.setText(contestConfig.getEndTime().toString());
    }


    public void updateStockSelectionUI(String stockSelection){
        this.stockNameLabel.setText(stockSelection);
        if (this.currentPortfollio != null && this.currentPortfollio.getOrDefault(stockSelection, null) != null){
            this.stockQuantitySpinner.setValue(this.currentPortfollio.get(stockSelection).get("quantity"));
        }
        else{
            this.stockQuantitySpinner.setValue(0);
        }
        this.purchasePriceLabel.setText("100"); //TODO: get stock cost here
    }

    private void onOK() {
        // switch this contest and its configuration to enrolled
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void launch(AvailableContestsViewModel viewModel, AvailableContestsController controller, User user){
        AvailableContestDetailView dialog = new AvailableContestDetailView(viewModel, controller, user);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        AvailableContestState state = (AvailableContestState) evt.getNewValue();
        if (state.getContestDetails() != null && !state.getContestDetails().equals(this.viewModel.getState())){
            setUiValues();
        }
    }
//    public static void main(String[] args) {
//        AvailableContestDetailView dialog = new AvailableContestDetailView();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
