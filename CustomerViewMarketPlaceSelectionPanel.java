import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Project05 -- CustomerViewMarketplaceSelectionPanel
 *
 * This class creates the panel that
 * lets the user view and select products
 * in the marketplace.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class CustomerViewMarketPlaceSelectionPanel extends JPanel {
    JTextField flavorToSelectTxt;
    JTextField storeSelectedTxt;
    JButton purchase;
    JButton back;
    JButton sortByPrice;
    JButton sortByQuantity;
    JButton refresh;
    JTable marketPlaceProductsTable;
    HashMap<String, Product> tableData;
    ArrayList<Product> tableDataArrayList;
    JScrollPane tableContainer;
    public CustomerViewMarketPlaceSelectionPanel() {
    }

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public CustomerViewMarketPlaceSelectionPanel(JFrame frame, JPanel customerPanel, ActionListener actionListener) {
        customerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        refresh = new JButton("Refresh");
        refresh.setBounds(30, 30, 80, 25);
        refresh.addActionListener(actionListener);
        this.add(refresh);

        JLabel testlbl = new JLabel("MarketPlace Listing: ");
        testlbl.setBounds(150, 100, 150, 25);
        this.add(testlbl);

        JLabel flavorNameLbl = new JLabel("Select a product to purchase:");
        flavorNameLbl.setBounds(200, 525, 200, 25);
        this.add(flavorNameLbl);

        flavorToSelectTxt = new JTextField();
        flavorToSelectTxt.setBounds(425, 525, 200, 25);
        this.add(flavorToSelectTxt);

        JLabel storeNameLbl = new JLabel("Enter the store name:");
        storeNameLbl.setBounds(200, 555, 200, 25);
        this.add(storeNameLbl);

        storeSelectedTxt = new JTextField();
        storeSelectedTxt.setBounds(425, 555, 200, 25);
        this.add(storeSelectedTxt);

        sortByPrice = new JButton("Sort Marketplace by Price");
        sortByPrice.setBounds(200, 605, 200, 25);
        sortByPrice.addActionListener(actionListener);
        this.add(sortByPrice);

        sortByQuantity = new JButton("Sort Marketplace by Quantity");
        sortByQuantity.setBounds(450, 605, 200, 25);
        sortByQuantity.addActionListener(actionListener);
        this.add(sortByQuantity);

        purchase = new JButton("Purchase");
        purchase.setBounds(700, 605, 100, 25);
        purchase.addActionListener(actionListener);
        this.add(purchase);

        back = new JButton("Back");
        back.setBounds(850, 605, 80, 25);
        back.addActionListener(actionListener);
        this.add(back);
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
        marketPlaceProductsTable = new JTable(model);
        tableContainer = new JScrollPane(marketPlaceProductsTable);
        tableContainer.setBounds(150, 150, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Map.Entry<String, Product> item : resultList.entrySet()) {
            Product prd = item.getValue();
            marketPlaceProductsTable.setValueAt(prd.getFlavorName(), i, 0);
            marketPlaceProductsTable.setValueAt(prd.getStore(), i, 1);
            marketPlaceProductsTable.setValueAt(prd.getDescription(), i, 2);
            marketPlaceProductsTable.setValueAt(prd.getQuantity(), i, 3);
            marketPlaceProductsTable.setValueAt(prd.getPrice(), i, 4);
            i++;
        }
    }

    public void setTableData(ArrayList<Product> resultList) {
        tableDataArrayList = resultList;
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
        marketPlaceProductsTable = new JTable(model);
        tableContainer = new JScrollPane(marketPlaceProductsTable);
        tableContainer.setBounds(150, 150, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Product item : resultList) {
            marketPlaceProductsTable.setValueAt(item.getFlavorName(), i, 0);
            marketPlaceProductsTable.setValueAt(item.getStore(), i, 1);
            marketPlaceProductsTable.setValueAt(item.getDescription(), i, 2);
            marketPlaceProductsTable.setValueAt(item.getQuantity(), i, 3);
            marketPlaceProductsTable.setValueAt(item.getPrice(), i, 4);
            i++;
        }
    }
}
