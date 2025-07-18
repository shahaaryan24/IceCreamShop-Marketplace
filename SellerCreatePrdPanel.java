import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Project05 -- SellerCreatePrdPanel
 *
 * This class creates the panel that
 * lets sellers create a new product in their stores.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class SellerCreatePrdPanel extends JPanel {
    JLabel productDetails;
    JLabel newFlavor;
    JLabel newDescription;
    JLabel newQuantity;
    JLabel newPrice;
    JTextField newFlavorTxt;
    JTextField newDesTxt;
    JTextField newQuantityTxt;
    JTextField newPriceTxt;
    JButton create;
    JButton cancel;
    Product prd = new Product();
    JTable sellerCreateProductTable;
    HashMap<String, Product> tableData;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public SellerCreatePrdPanel() {
    }

    public SellerCreatePrdPanel(JFrame frame, JPanel sellerPanel, ActionListener actionListener, CurrentUser user) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        productDetails = new JLabel();
        productDetails.setBounds(150, 90, 300, 25);
        productDetails.setText("Create a product");
        this.add(productDetails);

        newFlavor = new JLabel("Flavor:");
        newFlavor.setBounds(225, 140, 100, 25);
        this.add(newFlavor);
        newFlavorTxt = new JTextField();
        newFlavorTxt.setBounds(325, 140, 200, 25);
        this.add(newFlavorTxt);

        newDescription = new JLabel("Description:");
        newDescription.setBounds(225, 170, 100, 25);
        this.add(newDescription);
        newDesTxt = new JTextField();
        newDesTxt.setBounds(325, 170, 200, 25);
        this.add(newDesTxt);

        newQuantity = new JLabel("Quantity:");
        newQuantity.setBounds(225, 200, 100, 25);
        this.add(newQuantity);
        newQuantityTxt = new JTextField();
        newQuantityTxt.setBounds(325, 200, 200, 25);
        this.add(newQuantityTxt);

        newPrice = new JLabel("Price:");
        newPrice.setBounds(225, 230, 100, 25);
        this.add(newPrice);
        newPriceTxt = new JTextField();
        newPriceTxt.setBounds(325, 230, 200, 25);
        this.add(newPriceTxt);

        // creating edit button
        create = new JButton("Create");
        create.setBounds(225, 280, 80, 25);
        create.addActionListener(actionListener);
        this.add(create);

        cancel = new JButton("Back");
        cancel.setBounds(325, 280, 80, 25);
        cancel.addActionListener(actionListener);
        this.add(cancel);

    }

    public void setTableData(HashMap<String, Product> resultList) {
        tableData = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Flavor", "Store", "Description", "Quantity", "Price"};

            @Override
            public int getRowCount() {
                return resultList.size();
            }

            public int getColumnCount() {
                return 5;
            }

            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }

            @Override
            public String getColumnName(int index) {
                return columnHeaders[index];
            }
        };

        if (tableContainer != null) {
            this.remove(tableContainer);
        }
        sellerCreateProductTable = new JTable(model);
        tableContainer = new JScrollPane(sellerCreateProductTable);
        tableContainer.setBounds(150, 330, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Map.Entry<String, Product> item : resultList.entrySet()) {
            Product product = item.getValue();
            sellerCreateProductTable.setValueAt(product.getFlavorName(), i, 0);
            sellerCreateProductTable.setValueAt(product.getStore(), i, 1);
            sellerCreateProductTable.setValueAt(product.getDescription(), i, 2);
            sellerCreateProductTable.setValueAt(product.getQuantity(), i, 3);
            sellerCreateProductTable.setValueAt(product.getPrice(), i, 4);
            i++;
        }
    }
}
