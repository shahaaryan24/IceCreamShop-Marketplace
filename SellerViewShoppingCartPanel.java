import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Project05 -- SellerViewShoppingCartPanel
 *
 *This class creates the panel that lets the seller
 * view the shopping cart
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class SellerViewShoppingCartPanel extends JPanel {
    JLabel viewShoppingCartMessage;
    JButton back;
    JTable addedProductsTable;
    ArrayList<String> tableData;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public SellerViewShoppingCartPanel() {
    }

    public SellerViewShoppingCartPanel(JFrame frame, JPanel sellerPanel, ActionListener actionListener) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        viewShoppingCartMessage = new JLabel();
        viewShoppingCartMessage.setBounds(150, 90, 300, 25);
        viewShoppingCartMessage.setText("Products from All Customers Shopping Cart(s) :");
        this.add(viewShoppingCartMessage);

        back = new JButton("Back to Seller Menu");
        back.setBounds(750, 480, 200, 25);
        back.addActionListener(actionListener);
        this.add(back);
    }

    public void setTableData(ArrayList<String> resultList) {
        tableData = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Time Added", "Customer Name", "Store", "Flavor", "Quantity " +
                    "Purchased", "Unit Price", "Total Price"};

            @Override
            public int getRowCount() {
                return resultList.size();
            }

            public int getColumnCount() {
                return 7;
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
        addedProductsTable = new JTable(model);
        tableContainer = new JScrollPane(addedProductsTable);
        tableContainer.setBounds(150, 130, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (String item : resultList) {
            addedProductsTable.setValueAt(item.split(",")[0], i, 0);
            addedProductsTable.setValueAt(item.split(",")[2], i, 1);
            addedProductsTable.setValueAt(item.split(",")[3], i, 2);
            addedProductsTable.setValueAt(item.split(",")[4], i, 3);
            addedProductsTable.setValueAt(item.split(",")[5], i, 4);
            addedProductsTable.setValueAt(item.split(",")[6], i, 5);
            addedProductsTable.setValueAt(item.split(",")[7], i, 6);
            i++;
        }
    }
}
