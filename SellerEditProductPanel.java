import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Project05 -- SellerEditProductPanel
 *
 * This class creates the panel that lets
 * a seller edit a product in their store.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class SellerEditProductPanel extends JPanel {
    JLabel productDetails;
    JLabel newDescription;
    JLabel newQuantity;
    JLabel newPrice;
    JTextField newDesTxt;
    JTextField newQuantityTxt;
    JTextField newPriceTxt;
    JButton edit;
    JButton cancel;
    Product selectedProduct;

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public SellerEditProductPanel() {
    }

    public SellerEditProductPanel(JFrame frame, JPanel editPrdSelPanel, ActionListener actionListener, Product prd) {
        selectedProduct = prd;
        editPrdSelPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        productDetails = new JLabel();
        productDetails.setBounds(175, 90, 300, 50);
        productDetails.setText("Flavor being edited is " + prd.getFlavorName() +
                "   Store: " + prd.getStore());
        this.add(productDetails);

        newDescription = new JLabel("New Description:");
        newDescription.setBounds(225, 170, 100, 25);
        this.add(newDescription);
        newDesTxt = new JTextField();
        newDesTxt.setBounds(325, 170, 200, 25);
        newDesTxt.setText(prd.getDescription());
        this.add(newDesTxt);

        newQuantity = new JLabel("New Quantity:");
        newQuantity.setBounds(225, 200, 100, 25);
        this.add(newQuantity);
        newQuantityTxt = new JTextField();
        newQuantityTxt.setBounds(325, 200, 200, 25);
        newQuantityTxt.setText(Integer.toString(prd.getQuantity()));
        this.add(newQuantityTxt);

        newPrice = new JLabel("New Price:");
        newPrice.setBounds(225, 230, 100, 25);
        this.add(newPrice);
        newPriceTxt = new JTextField();
        newPriceTxt.setBounds(325, 230, 200, 25);
        newPriceTxt.setText(Double.toString(prd.getPrice()));
        this.add(newPriceTxt);

        // creating edit button
        edit = new JButton("Edit");
        edit.setBounds(225, 280, 80, 25);
        edit.addActionListener(actionListener);
        this.add(edit);

        cancel = new JButton("Cancel");
        cancel.setBounds(325, 280, 80, 25);
        cancel.addActionListener(actionListener);
        this.add(cancel);
    }
}
