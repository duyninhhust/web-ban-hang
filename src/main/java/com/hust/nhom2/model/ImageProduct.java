package com.hust.nhom2.model;

public class ImageProduct {
    private int id;
    private String source;
    private int productId;
    private String name;

    public ImageProduct(String source, int productId, String name) {
        this.source = source;
        this.productId = productId;
        this.name = name;
    }
    public ImageProduct(int id,String source, int productId, String name) {
        this.id = id;
        this.source = source;
        this.productId = productId;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImageProduct{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", productId=" + productId +
                ", name='" + name + '\'' +
                '}';
    }
}
