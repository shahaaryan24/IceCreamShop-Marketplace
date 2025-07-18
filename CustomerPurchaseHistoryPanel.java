import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Project05 -- CustomerPurchaseHistoryPanel
 *
 * This class creates the panel to view the
 * customer's purchase history
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */


public class CustomerPurchaseHistoryPanel extends JPanel {
    JLabel viewHistoryMessage;
    JButton back;
    JTable purchasedProductsTable;
    ArrayList<String> tableData;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public CustomerPurchaseHistoryPanel() {
    }

    public CustomerPurchaseHistoryPanel(JFrame frame, JPanel sellerPanel, ActionListener actionListener) {
        sellerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        viewHistoryMessage = new JLabel();
        viewHistoryMessage.setBounds(150, 90, 300, 25);
        viewHistoryMessage.setText("Purchase History:");
        this.add(viewHistoryMessage);

        back = new JButton("Back to Customer Menu");
        back.setBounds(750, 480, 200, 25);
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
        purchasedProductsTable = new JTable(model);
        tableContainer = new JScrollPane(purchasedProductsTable);
        tableContainer.setBounds(150, 130, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (String item : resultList) {
            purchasedProductsTable.setValueAt(item.split(",")[0], i, 0);
            purchasedProductsTable.setValueAt(item.split(",")[2], i, 1);
            purchasedProductsTable.setValueAt(item.split(",")[3], i, 2);
            purchasedProductsTable.setValueAt(item.split(",")[4], i, 3);
            purchasedProductsTable.setValueAt(item.split(",")[5], i, 4);
            purchasedProductsTable.setValueAt(item.split(",")[6], i, 5);
            purchasedProductsTable.setValueAt(item.split(",")[7], i, 6);
            i++;
        }
    }
}
