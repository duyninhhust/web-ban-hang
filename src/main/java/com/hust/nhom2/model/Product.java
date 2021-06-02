package com.hust.nhom2.model;


public class Product {
    private int id;
    private String name;
    private double price;
    private String image;
    private String introduction;
    private boolean deleted;
    private int categoryId;

    public Product(int id, String name, double price, String image, String introduction, boolean deleted, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.introduction = introduction;
        this.deleted = deleted;
        this.categoryId = categoryId;
    }
    public Product(String name, double price, String image, String introduction, boolean deleted, int categoryId) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.introduction = introduction;
        this.deleted = deleted;
        this.categoryId = categoryId;
    }
    public Product() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getIntroduction() {
        return introduction;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", introduction='" + introduction + '\'' +
                ", deleted=" + deleted +
                ", categoryId=" + categoryId +
                '}';
    }
}
