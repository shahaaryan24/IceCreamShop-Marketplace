import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Project05 -- CustomerStatistics
 *
 * This class implements the functionality
 * for the customer statistics.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class CustomerStatistics {
    // list of stores by number of products sold
    HashMap<String, Integer> storeList;
    // list of products and the store they were bought in
    HashMap<String, String> productList;
    ArrayList<CustomerSortStore> sortedStoreList = new ArrayList<>();

    // list of stores with number of items purchased from that store
    public HashMap<String, Integer> loadStores(String customerFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(customerFileName));
            storeList = new HashMap<>();
            String line = reader.readLine();

            while (line != null) {
                // only adds to the dictionary if the store doesn't previously exist
                if (storeList.get(line.split(",")[3].trim()) == null) {
                    // key is the customer username
                    // value is the number of items purchased
                    storeList.put(line.split(",")[3].trim(), Integer.parseInt(
                            line.split(",")[5].trim()));
                    // if store is already added increment the number of items purchased
                } else {
                    int newValue = storeList.get(line.split(",")[3]).intValue() +
                            Integer.parseInt(line.split(",")[5].trim());
                    storeList.put(line.split(",")[3].trim(), newValue);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return storeList;
    }

    // sorts the store by number of products purchased from that store
    public ArrayList<CustomerSortStore> sortStores(String customerFileName) {
        loadStores(customerFileName);
        sortedStoreList.clear();
        for (Map.Entry<String, Integer> item : storeList.entrySet()) {
            CustomerSortStore cs = new CustomerSortStore();
            cs.setStoreName(item.getKey());
            cs.setNumProducts(item.getValue());
            sortedStoreList.add(cs);
        }
        Collections.sort(sortedStoreList, CustomerSortStore.numProductsComparator);
        return sortedStoreList;
    }

    public HashMap<String, String> loadProducts(String customerFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(customerFileName));
            productList = new HashMap<>();
            String line = reader.readLine();

            while (line != null) {
                // only adds to the dictionary if the store doesn't previously exist
                if (productList.get(line.split(",")[3].trim()) == null) {
                    // key is the store name
                    // value is items purchased from that store
                    productList.put(line.split(",")[3].trim(), line.split(",")[4].trim());
                    // if store is already there add products to the value string
                } else {
                    // item hasn't already been added to product list
                    if (!productList.get(line.split(",")[3]).contains(line.split(",")[4].trim())) {
                        String newValue = productList.get(line.split(",")[3]) + ", " +
                                line.split(",")[4].trim();
                        productList.put(line.split(",")[3].trim(), newValue);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return productList;
    }
}

//class TestCustomerStatistics {
//    public static void main(String[] args) {
//        CustomerStatistics cs = new CustomerStatistics();
////        cs.sortStores("ColdStoneCreameryTransaction.txt");
////        System.out.println(cs.getSortedStoreList().toString());
//        cs.loadProducts("ColdStoneCreameryTransaction.txt");
//        System.out.println(cs.getProductList());
//    }
//}
