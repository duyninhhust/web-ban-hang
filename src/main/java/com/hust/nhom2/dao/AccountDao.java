package com.hust.nhom2.dao;

import com.hust.nhom2.model.Account;

import java.sql.SQLException;

public interface AccountDao extends BaseDao<Account>{
    public Account checkLogin(String username, String password) throws SQLException;

    public Account checkAccountExist(String username) throws SQLException;
}
