package com.hust.nhom2.model;

public class BillDetail {
    private int productId;
    private int billId;
    private double price;
    private int quantity;

    public BillDetail(int productId, int billId, double price, int quantity) {
        this.productId = productId;
        this.billId = billId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "BillDetail{" +
                "productId=" + productId +
                ", billId=" + billId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
