import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Project05 -- SellerViewSalesByStore
 *
 * this class creates the panel that displays the seller's
 * sales by store
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class SellerViewSalesByStore extends JPanel {
    JLabel viewSalesMessage;
    JButton back;
    JTable sellerProductsTable;
    ArrayList<String> tableData;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public SellerViewSalesByStore() {
    }

    public SellerViewSalesByStore(JFrame frame, JPanel sellerPanel, ActionListener actionListener) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        viewSalesMessage = new JLabel();
        viewSalesMessage.setBounds(150, 90, 300, 25);
        viewSalesMessage.setText("Sales for this store are:");
        this.add(viewSalesMessage);

        back = new JButton("Back to Seller Menu");
        back.setBounds(800, 480, 150, 25);
        back.addActionListener(actionListener);
        this.add(back);

    }

    public void setTableData(ArrayList<String> resultList) {
        tableData = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Transaction Time", "Customer Name", "Store", "Flavor", "Quantity " +
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
        sellerProductsTable = new JTable(model);
        tableContainer = new JScrollPane(sellerProductsTable);
        tableContainer.setBounds(150, 130, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (String item : resultList) {
            sellerProductsTable.setValueAt(item.split(",")[0], i, 0);
            sellerProductsTable.setValueAt(item.split(",")[2], i, 1);
            sellerProductsTable.setValueAt(item.split(",")[3], i, 2);
            sellerProductsTable.setValueAt(item.split(",")[4], i, 3);
            sellerProductsTable.setValueAt(item.split(",")[5], i, 4);
            sellerProductsTable.setValueAt(item.split(",")[6], i, 5);
            sellerProductsTable.setValueAt(item.split(",")[7], i, 6);
            i++;
        }
    }
}
