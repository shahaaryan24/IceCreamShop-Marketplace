import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * Project05 -- CustomerPurchasePrdPanel
 *
 * This class creates the panel that lets the customer
 * purchase a product.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class CustomerPurchasePrdPanel extends JPanel {
    JLabel productDetails;
    JLabel newDescription;
    JLabel newQuantity;
    JLabel quantityBeingPurchased;
    JTextField newDesTxt;
    JTextField newQuantityTxt;
    JTextField quantityPurchasedTxt;
    JButton purchase;
    JButton addToShoppingCart;
    JButton cancel;
    Product selectedProduct;

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public CustomerPurchasePrdPanel() {
    }

    public CustomerPurchasePrdPanel(JFrame frame, JPanel viewMarketPlacePanel, ActionListener actionListener, Product
            prd) {
        selectedProduct = prd;
        viewMarketPlacePanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        productDetails = new JLabel();
        productDetails.setBounds(175, 90, 400, 50);
        productDetails.setText("Flavor being purchased is: " + prd.getFlavorName() +
                "   Store: " + prd.getStore() + "   Price: " + prd.getPrice());
        this.add(productDetails);

        newDescription = new JLabel("Description:");
        newDescription.setBounds(225, 170, 100, 25);
        this.add(newDescription);
        newDesTxt = new JTextField();
        newDesTxt.setBounds(325, 170, 200, 25);
        newDesTxt.setText(prd.getDescription());
        newDesTxt.setEditable(false);
        this.add(newDesTxt);

        newQuantity = new JLabel("Quantity:");
        newQuantity.setBounds(225, 200, 100, 25);
        this.add(newQuantity);
        newQuantityTxt = new JTextField();
        newQuantityTxt.setBounds(325, 200, 200, 25);
        newQuantityTxt.setText(Integer.toString(prd.getQuantity()));
        newQuantityTxt.setEditable(false);
        this.add(newQuantityTxt);

        quantityBeingPurchased = new JLabel("Enter the quantity to purchase:");
        quantityBeingPurchased.setBounds(225, 230, 200, 25);
        this.add(quantityBeingPurchased);
        quantityPurchasedTxt = new JTextField();
        quantityPurchasedTxt.setBounds(425, 230, 200, 25);
        this.add(quantityPurchasedTxt);

        // creating purchase button
        purchase = new JButton("Purchase");
        purchase.setBounds(225, 280, 100, 25);
        purchase.addActionListener(actionListener);
        this.add(purchase);

        addToShoppingCart = new JButton("Add to shopping cart");
        addToShoppingCart.setBounds(350, 280, 200, 25);
        addToShoppingCart.addActionListener(actionListener);
        this.add(addToShoppingCart);

        cancel = new JButton("Cancel");
        cancel.setBounds(575, 280, 80, 25);
        cancel.addActionListener(actionListener);
        this.add(cancel);
    }
}
