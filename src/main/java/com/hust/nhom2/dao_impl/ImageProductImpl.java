package com.hust.nhom2.dao_impl;

import com.hust.nhom2.dao.ImageProductDao;

import com.hust.nhom2.model.ImageProduct;
import com.hust.nhom2.model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ImageProductImpl extends BaseDaoImpl<ImageProduct> implements ImageProductDao {

    private MyConnection myConnection = new MyConnection();

    public ImageProduct getObject(ResultSet resultSet) throws SQLException {
        ImageProduct imageProduct  = null;
        imageProduct = new ImageProduct(resultSet.getInt("id"),
                resultSet.getString("source"),
                resultSet.getInt("product_id"),
                resultSet.getString("name"));
        return imageProduct;
    }

    public List<ImageProduct> findAll() throws SQLException {
        String sql = "select * from imageproduct";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    public ImageProduct findById(int id) throws SQLException {
        ImageProduct imageProduct = null;
        String sql = "select * from imageproduct where id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        //kiểm tra xem có bản ghi hay không nếu có sử dụng hàm getObject để lấy ra đối tượng.
        if (resultSet.next()) {
            imageProduct = getObject(resultSet);
        }
        return imageProduct;
    }

    public ImageProduct insert(ImageProduct imageProduct) throws SQLException {
        return null;
    }

    public boolean update(ImageProduct imageProduct) throws SQLException {
        return false;
    }

    public boolean delete(int id) throws SQLException {
        return false;
    }

    public List<ImageProduct> findByProductId(int productId) throws SQLException {
        String sql = "select * from imageproduct,product where imageproduct.product_id = product.id and product_id = ?;";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, productId);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }
}
