import java.io.Serializable;
import java.util.Comparator;

/**
 * Project05 -- Product
 *
 * The product class, establishing a fundamental structure
 * that plays a key role in the MarketPlace's design.
 *
 *   
 *
 * @version November 22, 2023
 */
public class Product implements Serializable {
    private String flavor;
    private String store;
    private String description;
    private int quantity;
    private double price;

    public Product() {
    }

    public Product(String name, String store, String description, int quantity, double price) {
        this.flavor = name;
        this.store = store;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public String getFlavorName() {
        return flavor;
    }

    public void setFlavorName(String name) {
        this.flavor = name;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flavor: " + flavor + "\t" +
                "Store: " + store + "\t" +
                "Price=" + String.format("%.2f" , price);
    }

    public static Comparator<Product> quantityComparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return Integer.compare(o1.getQuantity(), o2.getQuantity());
        }
    };

    public static Comparator<Product> priceComparator = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    };
}
