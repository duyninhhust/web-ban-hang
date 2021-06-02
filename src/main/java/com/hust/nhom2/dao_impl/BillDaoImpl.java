package com.hust.nhom2.dao_impl;

import com.hust.nhom2.dao.BillDao;
import com.hust.nhom2.model.Bill;
import com.hust.nhom2.model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDaoImpl extends BaseDaoImpl<Bill> implements BillDao {

    MyConnection myConnection = new MyConnection();

    public Bill getObject(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill(resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5)
                );
        return bill;
    }

    public List<Bill> findAll() throws SQLException {
        String sql = "select * from bill";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return getList(preparedStatement.executeQuery());
    }

    public Bill findById(int id) throws SQLException {
        Bill bill = null;
        String sql = "select * from bill where id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            bill = getObject(resultSet);
        }
        return bill;
    }

    public Bill insert(Bill bill) throws SQLException {
        Bill newBill = null;
        String sql = "insert into bill (name,phoneNumber,address,status) values (?,?,?,?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, bill.getName());
        preparedStatement.setString(2, bill.getPhoneNumber());
        preparedStatement.setString(3, bill.getAddress());
        preparedStatement.setInt(4,bill.getStatus());
        int rs = preparedStatement.executeUpdate();
        //nếu insert thành công rs là số bản ghi mình vừa thay đổi dữ liệu
        if(rs > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                System.out.println((int) resultSet.getLong(1));
                newBill = findById((int) resultSet.getLong(1));
            }

        }
        return newBill;
    }

    public boolean update(Bill bill) throws SQLException {
        return false;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = false;
        //xóa một bản ghi tức là chuyển trường deleted của bản ghi ấy về true
        String sql = "delete from bill where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if(rs > 0) result = true;
        return result;
    }

    public int getTotalBill() throws SQLException {
        String sql = "select count(*) from bill";
        PreparedStatement statement = myConnection.prepare(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) return resultSet.getInt(1);
        return 0;
    }

    public List<Bill> pagingBill(int index) throws SQLException {
        String sql = "select * from bill order by id limit 3 offset ? ";
        PreparedStatement statement = myConnection.prepare(sql);
        statement.setInt(1, (index-1)*3);
        ResultSet resultSet = statement.executeQuery();
        return getList(resultSet);
    }

    public boolean confirmBill(int billId) throws SQLException {
        boolean result = false;
        String sql ="update bill set status = 2 where bill.id = ?";
        PreparedStatement statement = myConnection.prepareUpdate(sql);
        statement.setInt(1,billId);
        int rs = statement.executeUpdate();
        if(rs>0) result = true;
        return  result;
    }

}
