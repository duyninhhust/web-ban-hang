package com.hust.nhom2.dao_impl;

import com.hust.nhom2.dao.CommentDao;
import com.hust.nhom2.model.Comment;
import com.hust.nhom2.model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

    MyConnection myConnection = new MyConnection();

    @Override
    public Comment getObject(ResultSet resultSet) throws SQLException {
        Comment comment = null;
        comment = new Comment();
        comment.setId(resultSet.getInt("id"));
        comment.setName(resultSet.getString("name"));
        comment.setEmail(resultSet.getString("email"));
        comment.setSdt(resultSet.getString("sdt"));
        comment.setComment(resultSet.getString("comment"));
        comment.setIdProduct(resultSet.getInt("idproduct"));
        return comment;
    }

    @Override
    public List<Comment> findAll() throws SQLException {
        String sql = "select * from comment";
        PreparedStatement statement = myConnection.prepare(sql);
        return getList(statement.executeQuery());
    }

    @Override
    public Comment findById(int id) throws SQLException {
        Comment comment = null;
        String sql = "select * from comment where id = ?";
        PreparedStatement statement = myConnection.prepare(sql);
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) comment = getObject(resultSet);
        return comment;
    }

    @Override
    public Comment insert(Comment comment) throws SQLException {
        Comment comment1 = null;
        String sql = "insert into comment(name,email,sdt,comment,idproduct) values (?,?,?,?,?)";
        PreparedStatement statement = myConnection.prepareUpdate(sql);
        statement.setString(1, comment.getName());
        statement.setString(2,comment.getEmail());
        statement.setString(3,comment.getSdt());
        statement.setString(4,comment.getComment());
        statement.setInt(5,comment.getIdProduct());
        int rs = statement.executeUpdate();
        if (rs > 0){
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                comment1 = findById((int) resultSet.getLong(1));
            }
        }
        return comment1;
    }

    @Override
    public List<Comment> findByProduct(int idProduct) throws SQLException {
        String sql = "select distinct C.* from comment as c, product as p where p.id = ? and c.idproduct = p.id";
        PreparedStatement statement = myConnection.prepare(sql);
        statement.setInt(1,idProduct);
        return getList(statement.executeQuery());
    }

    // k dùng
    @Override
    public boolean update(Comment comment) throws SQLException {
        return false;
    }
    // k dùng
    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }


}
