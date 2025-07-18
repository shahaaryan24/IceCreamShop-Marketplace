import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Project05 -- CustomerStatisticsStoreReportPanel
 *
 * This class creates the store report
 * panel for customer statistics.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class CustomerStatisticsStoreReportPanel extends JPanel {
    JLabel storeReportMessage;
    JButton back;
    JTable storeTable;
    JButton sort;
    HashMap<String, Integer> tableData;
    ArrayList<CustomerSortStore> tableDataArr;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public CustomerStatisticsStoreReportPanel() {
    }

    public CustomerStatisticsStoreReportPanel(JFrame frame, JPanel customerStatistics, ActionListener actionListener) {
        customerStatistics.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        storeReportMessage = new JLabel();
        storeReportMessage.setBounds(150, 90, 300, 25);
        storeReportMessage.setText("Store Report");
        this.add(storeReportMessage);

        // sorting by the number of items purchased by each customer
        sort = new JButton("Sort dashboard");
        sort.setBounds(450, 480, 200, 25);
        sort.addActionListener(actionListener);
        this.add(sort);

        back = new JButton("Back to Customer Statistics Menu");
        back.setBounds(700, 480, 250, 25);
        back.addActionListener(actionListener);
        this.add(back);
    }

    public void setTableData(HashMap<String, Integer> resultList) {
        tableData = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Store Name", "Number of products sold"};

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
        storeTable = new JTable(model);
        tableContainer = new JScrollPane(storeTable);
        tableContainer.setBounds(150, 130, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Map.Entry<String, Integer> item : resultList.entrySet()) {
            storeTable.setValueAt(item.getKey(), i, 0);
            storeTable.setValueAt(item.getValue(), i, 1);
            i++;
        }
    }

    public void setTableData(ArrayList<CustomerSortStore> resultList) {
        tableDataArr = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Store Name", "Number of products sold"};

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
        storeTable = new JTable(model);
        tableContainer = new JScrollPane(storeTable);
        tableContainer.setBounds(150, 130, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (CustomerSortStore item : resultList) {
            storeTable.setValueAt(item.getStoreName(), i, 0);
            storeTable.setValueAt(item.getNumProducts(), i, 1);
            i++;
        }
    }
}
