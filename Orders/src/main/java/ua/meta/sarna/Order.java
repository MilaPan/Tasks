package ua.meta.sarna;

import java.util.Random;

public class Order {
    @Id
    private int id;

    private int clientId;
    private int productId;
    private int quantity;
    public Order() {
    }

    public Order(int clientId, int productId, int quantity) {
        Random r = new Random();
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.id = r.nextInt(1000000);
    }
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
