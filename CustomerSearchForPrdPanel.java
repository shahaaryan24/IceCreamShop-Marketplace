import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Project05 -- CustomerSearchForPrdPanel
 *
 * This class creates the search product panel
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class CustomerSearchForPrdPanel extends JPanel {
    JLabel searchWelcomeMsg;
    JButton back;
    JLabel searchStringLbl;
    JTextField searchStringTxt;
    JTable searchResultsTable;
    JButton search;
    HashMap<String, Product> tableData;
    JScrollPane tableContainer;
    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public CustomerSearchForPrdPanel() {
    }

    public CustomerSearchForPrdPanel(JFrame frame, JPanel customerPanel, ActionListener actionListener,
                                     CurrentUser user) {
        customerPanel.setVisible(false);
        this.setLayout(null);
        frame.add(this);

        searchWelcomeMsg = new JLabel();
        searchWelcomeMsg.setBounds(150, 90, 300, 25);
        searchWelcomeMsg.setText("Search for Products");
        this.add(searchWelcomeMsg);

        searchStringLbl = new JLabel("Enter a search term");
        searchStringLbl.setBounds(225, 140, 200, 25);
        this.add(searchStringLbl);
        searchStringTxt = new JTextField();
        searchStringTxt.setBounds(425, 140, 200, 25);
        this.add(searchStringTxt);

        // creating select file button
        search = new JButton("Search");
        search.setBounds(650, 140, 100, 25);
        search.addActionListener(actionListener);
        this.add(search);

        back = new JButton("Back to Customer menu");
        back.setBounds(325, 180, 200, 25);
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
        searchResultsTable = new JTable(model);
        tableContainer = new JScrollPane(searchResultsTable);
        tableContainer.setBounds(150, 230, 800, 300);
        this.add(tableContainer);

        int i = 0;
        for (Map.Entry<String, Product> item : resultList.entrySet()) {
            Product prd = item.getValue();
            searchResultsTable.setValueAt(prd.getFlavorName(), i, 0);
            searchResultsTable.setValueAt(prd.getStore(), i, 1);
            searchResultsTable.setValueAt(prd.getDescription(), i, 2);
            searchResultsTable.setValueAt(prd.getQuantity(), i, 3);
            searchResultsTable.setValueAt(prd.getPrice(), i, 4);
            i++;
        }
    }
}
