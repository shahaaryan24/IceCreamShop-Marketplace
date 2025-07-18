import javax.swing.*;
import java.awt.event.ActionListener;
/**
 * Project05 -- CreateCustomerPanel
 *
 * This class creates the customer panel
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */


public class CreateCustomerPanel extends JPanel {
    JButton viewMarketPlace;
    JButton searchForProducts;
    JButton viewPurchaseHistory;
    JButton exportPurchaseHistory;
    JButton viewStatistics;
    JButton viewShoppingCart;
    JButton editAcc;
    JButton logout;
    public CreateCustomerPanel() {
    }

    public CreateCustomerPanel(JFrame frame, JPanel loginPanel, ActionListener actionListener) {
        // removes the login panel
        loginPanel.setVisible(false);
        this.setLayout(null);
        // replacing the login panel with the next panel
        frame.add(this);
        // creating welcome message label
        JLabel welcomeLbl = new JLabel("Welcome to MarketPlace Application!");
        welcomeLbl.setBounds(125, 120, 300, 25);
        this.add(welcomeLbl);

        // adding view marketplace button
        viewMarketPlace = new JButton("View MarketPlace");
        viewMarketPlace.setBounds(275, 200, 200, 25);
        viewMarketPlace.addActionListener(actionListener);
        this.add(viewMarketPlace);

        // add search for products button
        searchForProducts = new JButton("Search for Products");
        searchForProducts.setBounds(525, 200, 200, 25);
        searchForProducts.addActionListener(actionListener);
        this.add(searchForProducts);

        // creating view purchase history button
        viewPurchaseHistory = new JButton("View Purchase History");
        viewPurchaseHistory.setBounds(275, 300, 200, 25);
        viewPurchaseHistory.addActionListener(actionListener);
        this.add(viewPurchaseHistory);

        // creating export purchase history button
        exportPurchaseHistory = new JButton("Export purchase history");
        exportPurchaseHistory.setBounds(525, 300, 200, 25);
        exportPurchaseHistory.addActionListener(actionListener);
        this.add(exportPurchaseHistory);

        // creating view statistics
        viewStatistics = new JButton("Statistics Dashboard");
        viewStatistics.setBounds(275, 400, 200, 25);
        viewStatistics.addActionListener(actionListener);
        this.add(viewStatistics);

        // view shopping cart button
        viewShoppingCart = new JButton("Shopping Cart");
        viewShoppingCart.setBounds(525, 400, 200, 25);
        viewShoppingCart.addActionListener(actionListener);
        this.add(viewShoppingCart);

        // creating edit account button
        editAcc = new JButton("Edit Account");
        editAcc.setBounds(900, 30, 125, 25);
        editAcc.addActionListener(actionListener);
        this.add(editAcc);

        // creating log out button
        logout = new JButton("Logout");
        logout.setBounds(1050, 30, 80, 25);
        logout.addActionListener(actionListener);
        this.add(logout);
    }

}
