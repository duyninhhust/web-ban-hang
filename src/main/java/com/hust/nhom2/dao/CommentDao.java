package com.hust.nhom2.dao;

import com.hust.nhom2.model.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao extends BaseDao<Comment>{

    List<Comment> findByProduct(int idProduct) throws SQLException;


}
