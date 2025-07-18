import java.io.Serializable;
import java.util.Comparator;

/**
 * Project05 -- SellerSortCustomer
 *
 * This class implements the functionality
 * to sort customers by the number of
 * purchases they made from a store.
 *
 *   
 *  
 *  
 *   
 *
 *  @version December 5th, 2023
 */

public class SellerSortCustomer implements Serializable {
    String customerUserName;
    int numProducts;

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }

    public static Comparator<SellerSortCustomer> numProductsComparator = new Comparator<SellerSortCustomer>() {
        @Override
        public int compare(SellerSortCustomer o1, SellerSortCustomer o2) {
            return Integer.compare(o1.getNumProducts(), o2.getNumProducts());
        }
    };

    @Override
    public String toString() {
        return "customerName: " + customerUserName +
                ", numProducts=" + numProducts;
    }
}
