import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Project05 -- Transaction
 *
 * This class records all the details of a
 * transaction made by a customer
 *
 *   
 *
 * @version November 22, 2023
 */
public class Transaction implements Serializable {
    String transactionID;
    String transactionTime;
    String customerId;
    String storeName;
    String flavorName;
    String quantity;
    String purchasePrice;
    String totalPrice;

    public Transaction() {
        this.transactionID = String.valueOf(Timestamp.valueOf(LocalDateTime.now()));
        this.transactionTime = LocalDateTime.now().toString();
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getFlavorName() {
        return flavorName;
    }

    public void setFlavorName(String flavorName) {
        this.flavorName = flavorName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
