import java.io.Serializable;
import java.util.Comparator;

/**
 * Project05 -- SellerSortProduct
 *
 * This class implements the functionality
 * to sort products by the number of times they
 * were purchased.
 *
 *   
 *  
 *  
 *   
 *
 * @version December 5th, 2023
 */

public class SellerSortProduct implements Serializable {
    String flavorName;
    int numProducts;

    public String getFlavorName() {
        return flavorName;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }

    public static Comparator<SellerSortProduct> numProductsComparator = new Comparator<SellerSortProduct>() {
        @Override
        public int compare(SellerSortProduct o1, SellerSortProduct o2) {
            return Integer.compare(o1.getNumProducts(), o2.getNumProducts());
        }
    };

    @Override
    public String toString() {
        return "flavorName: " + flavorName +
                ", numProducts=" + numProducts;
    }
}
