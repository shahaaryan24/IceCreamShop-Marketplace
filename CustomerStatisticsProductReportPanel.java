import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Project05 -- CustomerStatisticsProductReportPanel
 *
 * This class creates the product report panel
 * for statistics.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class CustomerStatisticsProductReportPanel extends JPanel {
    JLabel productMessage;
    JButton back;
    JTable customerTable;
    HashMap<String, Integer> tableData;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public CustomerStatisticsProductReportPanel() {
    }

    public CustomerStatisticsProductReportPanel(JFrame frame, JPanel customerStatistics, ActionListener actionListener) {
        customerStatistics.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        productMessage = new JLabel();
        productMessage.setBounds(150, 90, 300, 25);
        productMessage.setText("Product Report");
        this.add(productMessage);

        back = new JButton("Back to Customer Statistics Menu");
        back.setBounds(700, 480, 250, 25);
        back.addActionListener(actionListener);
        this.add(back);
    }

    public void setTableData(HashMap<String, Integer> resultList) {
        tableData = resultList;
        DefaultTableModel model = new DefaultTableModel() {
            final String[] columnHeaders = {"Store Name", "Product purchased"};

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
