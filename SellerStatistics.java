import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Project05 -- SellerStatistics
 *
 * This class implements the functionality
 * for seller statistics
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */
public class SellerStatistics {
    HashMap<String, Integer> customerList;
    HashMap<String, Integer> productList;
    ArrayList<SellerSortCustomer> sortedCustomerList = new ArrayList<>();
    ArrayList<SellerSortProduct> sortedProductList = new ArrayList<>();
    public HashMap<String, Integer> loadCustomers(String storeFileName) { 
        // file name should be the storeTransaction file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(storeFileName));
            customerList = new HashMap<>();
            String line = reader.readLine();

            while (line != null) {
                // only adds to the dictionary if the customer doesn't previously exist
                if (customerList.get(line.split(",")[2].trim()) == null) {
                    // key is the customer username
                    // value is the number of items purchased
                    customerList.put(line.split(",")[2].trim(), Integer.parseInt(
                            line.split(",")[5].trim()));
                    // if customer is already added increment the number of items purchased
                } else {
                    int newValue = customerList.get(line.split(",")[2]).intValue() +
                            Integer.parseInt(line.split(",")[5].trim());
                    customerList.put(line.split(",")[2].trim(), newValue);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return customerList;
    }

    public HashMap<String, Integer> loadProducts(String storeFileName) { 
        // file name should be the storeTransaction file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(storeFileName));
            productList = new HashMap<>();
            String line = reader.readLine();

            while (line != null) {
                // only adds to the dictionary if the customer doesn't previously exist
                if (productList.get(line.split(",")[4].trim()) == null) {
                    // key is the customer username
                    // value is the number of items purchased
                    productList.put(line.split(",")[4].trim(), Integer.parseInt(
                            line.split(",")[5].trim()));
                    // if customer is already added increment the number of items purchased
                } else {
                    int newValue = productList.get(line.split(",")[4]).intValue() +
                            Integer.parseInt(line.split(",")[5].trim());
                    productList.put(line.split(",")[4].trim(), newValue);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            return null;
        }
        return productList;
    }

    public ArrayList<SellerSortCustomer> sortCustomers(String storeFileName) {
        loadCustomers(storeFileName);
        for (Map.Entry<String, Integer> item : customerList.entrySet()) {
            SellerSortCustomer sc = new SellerSortCustomer();
            sc.setCustomerUserName(item.getKey());
            sc.setNumProducts(item.getValue());
            sortedCustomerList.add(sc);
        }
        Collections.sort(sortedCustomerList, SellerSortCustomer.numProductsComparator);
        return sortedCustomerList;
    }

    public ArrayList<SellerSortProduct> sortProducts(String storeFileName) {
        loadProducts(storeFileName);
        for (Map.Entry<String, Integer> item : productList.entrySet()) {
            SellerSortProduct sp = new SellerSortProduct();
            sp.setFlavorName(item.getKey());
            sp.setNumProducts(item.getValue());
            sortedProductList.add(sp);
        }
        Collections.sort(sortedProductList, SellerSortProduct.numProductsComparator);
        return sortedProductList;
    }
}

//class TestSellerStatistics {
//    public static void main(String[] args) {
//        SellerStatistics ss = new SellerStatistics();
////        ss.sortCustomers("ColdStoneCreameryTransaction.txt");
////        System.out.println(ss.getSortedCustomerList().toString());
//        ss.sortProducts("ColdStoneCreameryTransaction.txt");
//        System.out.println(ss.getSortedProductList().toString());
//    }
//}
