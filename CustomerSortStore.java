import java.io.Serializable;
import java.util.Comparator;

/**
 * Project05 -- CustomerSortStore
 *
 * This class implements all the functionality
 * for the application to sort products.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class CustomerSortStore implements Serializable {
    String storeName;
    int numProducts;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }

    public static Comparator<CustomerSortStore> numProductsComparator = new Comparator<CustomerSortStore>() {
        @Override
        public int compare(CustomerSortStore o1, CustomerSortStore o2) {
            return Integer.compare(o1.getNumProducts(), o2.getNumProducts());
        }
    };

    @Override
    public String toString() {
        return "storeName: " + storeName +
                ", numProducts=" + numProducts;
    }
}
