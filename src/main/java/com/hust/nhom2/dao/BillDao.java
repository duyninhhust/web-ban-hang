package com.hust.nhom2.dao;

import com.hust.nhom2.model.Bill;

import java.sql.SQLException;
import java.util.List;

public interface BillDao extends BaseDao<Bill> {
    int getTotalBill() throws SQLException;

    List<Bill> pagingBill(int index) throws SQLException;

    boolean confirmBill(int billId) throws SQLException;
}
