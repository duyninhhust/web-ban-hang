package com.hust.nhom2.dao;

import com.hust.nhom2.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends BaseDao<Product> {
    List<Product> sortBy(String field, boolean isASC) throws SQLException;

    List<Product> findByCategory(int idCategory) throws SQLException;

    Product getLastProduct() throws SQLException;

    List<Product> search(String txtSearch) throws SQLException;

    List<Product> get3Product() throws SQLException;

    List<Product> getNext3Product(int amount) throws SQLException;

    int getTotalProduct() throws SQLException;

    List<Product> pagingProduct(int index) throws SQLException;

    List<Product> getProductByPrice(double price1, double price2) throws SQLException;

}
