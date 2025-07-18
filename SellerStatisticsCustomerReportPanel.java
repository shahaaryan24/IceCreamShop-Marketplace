import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Project05 -- SellerStatisticsCustomerReportPanel
 *
 * This class creates the panel to
 * display the customer report.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class SellerStatisticsCustomerReportPanel extends JPanel {
    JLabel customerReportMessage;
    JButton back;
    JTable customerTable;
    JButton sort;
    ArrayList<SellerSortCustomer> tableDataArr;
    HashMap<String, Integer> tableData;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public SellerStatisticsCustomerReportPanel() {
    }

    public SellerStatisticsCustomerReportPanel(JFrame frame, JPanel sellerStatistics, ActionListener actionListener) {
        sellerStatistics.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        customerReportMessage = new JLabel();
        customerReportMessage.setBounds(150, 90, 300, 25);
        customerReportMessage.setText("Customer Report");
        this.add(customerReportMessage);

        // sorting by the number of items purchased by each customer
        sort = new JButton("Sort dashboard");
        sort.setBounds(450, 480, 200, 25);
        sort.addActionListener(actionListener);
        this.add(sort);

        back = new JButton("Back to Seller Statistics Menu");
        back.setBounds(730, 480, 220, 25);
        back.addActionListener(actionListener);
        this.add(back);
    }

    public void setTableData(ArrayList<SellerSortCustomer> resultList) {
        tableDataArr = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Customer UserID", "Number of items purchased"};

            @Override
            public int getRowCount() {
                return resultList.size();
            }

            public int getColumnCount() {
                return 2;
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
        customerTable = new JTable(model);
        tableContainer = new JScrollPane(customerTable);
        tableContainer.setBounds(150, 130, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (SellerSortCustomer item : resultList) {
            customerTable.setValueAt(item.getCustomerUserName(), i, 0);
            customerTable.setValueAt(item.getNumProducts(), i, 1);
            i++;
        }
    }

    public void setTableData(HashMap<String, Integer> resultList) {
        tableData = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Customer UserID", "Number of items purchased"};

            @Override
            public int getRowCount() {
                return resultList.size();
            }

            public int getColumnCount() {
                return 2;
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
        customerTable = new JTable(model);
        tableContainer = new JScrollPane(customerTable);
        tableContainer.setBounds(150, 130, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Map.Entry<String, Integer> item : resultList.entrySet()) {
            customerTable.setValueAt(item.getKey(), i, 0);
            customerTable.setValueAt(item.getValue(), i, 1);
            i++;
        }
    }
}
