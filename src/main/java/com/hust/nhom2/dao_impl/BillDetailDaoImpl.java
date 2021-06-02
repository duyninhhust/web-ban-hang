package com.hust.nhom2.dao_impl;

import com.hust.nhom2.dao.BillDetailDao;
import com.hust.nhom2.model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class BillDetailDaoImpl extends BaseDaoImpl<BillDetail> implements BillDetailDao {
    MyConnection myConnection = new MyConnection();

    public BillDetail getObject(ResultSet resultSet) throws SQLException {
        BillDetail billDetail = null;
        billDetail = new BillDetail(
                resultSet.getInt("product_id"),
                resultSet.getInt("bill_id"),
                resultSet.getDouble("price"),
                resultSet.getInt("quantity")
        );
      return billDetail;
    }

    public List<BillDetail> findAll() throws SQLException {
        String  sql = "select * from billdetail";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        return  getList(preparedStatement.executeQuery());
    }

    public BillDetail findById(int id) throws SQLException {
        return null;
    }

    public BillDetail insert(BillDetail billDetail) throws SQLException {
        BillDetail newBillDetail = null;
        String sql = "insert into billdetail (product_id, bill_id,price,quantity) values (?,?,?,?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, billDetail.getProductId());
        preparedStatement.setInt(2, billDetail.getBillId());
        preparedStatement.setDouble(3,billDetail.getPrice());
        preparedStatement.setInt(4,billDetail.getQuantity());
        int rs = preparedStatement.executeUpdate();
        //nếu insert thành công rs là số bản ghi mình vừa thay đổi dữ liệu
        if(rs > 0) {
            //sử dụng hầm getGenerateKeys để trả về cho mình resultSet
            // chứa id tương ứng với bản ghi vừa thêm vào;
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            //kiểm tra xem có kết quả result trả về hay không
            if(resultSet.next()) {
                //resultSet.getLong(số thứ tự cột) ở đây chỉ trả về 1 ô nên stt sẽ là 1
                //getGeneratedKeys chứa kiểu trả về là long
                newBillDetail = findById((int) resultSet.getLong(1));
            }
        }
        return newBillDetail;
    }

    public boolean update(BillDetail billDetail) throws SQLException {
        return false;
    }

    public boolean delete(int id) throws SQLException {
        boolean result = false;
        //xóa một bản ghi tức là chuyển trường deleted của bản ghi ấy về true
        String sql = "delete from billdetail where bill_id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if(rs > 0) result = true;
        return result;
    }

    public List<BillDetail> getAllProductInBill(int billId) throws SQLException {
        BillDetail billDetail = null;
        String sql = "select * from billdetail where bill_id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,billId);
        ResultSet resultSet = preparedStatement.executeQuery();

        return getList(resultSet);
    }

    public double totalMoney(int billId) throws SQLException {
        double totalMoney = 0;
        String sql = "SELECT sum(price*quantity) FROM billdetail where bill_id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1,billId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            //resultSet.getLong(số thứ tự cột) ở đây chỉ trả về 1 ô nên stt sẽ là 1
            //getGeneratedKeys chứa kiểu trả về là long
            totalMoney = resultSet.getInt(1);
        }
        return totalMoney;
    }
}
