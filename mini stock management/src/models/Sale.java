package models;

public class Sale {
    private static int counter = 0;  // Static counter to generate unique IDs
    private int id;
    private int productId;
    private int customerId;
    private int quantity;
    private double totalPrice;

    public Sale(int productId, int customerId, int quantity, double totalPrice) {
        this.id = ++counter;  // Increment counter to generate ID
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public int getProductId(){
        return productId;
    }

    public int getCustomerId(){
        return customerId;
    }
    public int getQuantity(){
        return quantity;
    }

    public int getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Sale{id=" + id + ", productId=" + productId + ", customerId=" + customerId + ", quantity=" + quantity + ", totalPrice=" + totalPrice + "}";
    }
}
