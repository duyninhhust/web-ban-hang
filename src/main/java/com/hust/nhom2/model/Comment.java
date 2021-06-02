package com.hust.nhom2.model;

public class Comment {

    private int id;
    private String name;
    private String email;
    private String sdt;
    private String comment;
    private int idProduct;

    public Comment() {
    }

    public Comment(int id, String name, String email, String sdt, String comment, int idProduct) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sdt = sdt;
        this.comment = comment;
        this.idProduct = idProduct;
    }
    public Comment( String name, String email, String sdt, String comment, int idProduct) {
        this.name = name;
        this.email = email;
        this.sdt = sdt;
        this.comment = comment;
        this.idProduct = idProduct;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", sdt='" + sdt + '\'' +
                ", comment='" + comment + '\'' +
                ", idProduct=" + idProduct +
                '}';
    }
}
