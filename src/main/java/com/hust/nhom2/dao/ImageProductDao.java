package com.hust.nhom2.dao;

import com.hust.nhom2.model.ImageProduct;

import java.sql.SQLException;
import java.util.List;

public interface ImageProductDao extends BaseDao<ImageProduct>{
    List<ImageProduct> findByProductId(int productId) throws SQLException;
}
