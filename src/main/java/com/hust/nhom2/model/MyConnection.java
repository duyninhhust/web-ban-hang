package com.hust.nhom2.model;

import com.hust.nhom2.common.AppConfig;

import java.sql.*;

public class MyConnection {

    public static Connection connection = null;

    public void driverTest() throws ClassNotFoundException{
        try {
            Class.forName(AppConfig.DRIVER);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("JDBC Driver not found" + e.getMessage());
        }
    }

    public Connection connectDB() throws ClassNotFoundException, SQLException {
        if(connection == null) {
            driverTest();
            try {
                //sử dụng drivermanager,getconnection truyền vào 3 tham số để connect
                connection = DriverManager.getConnection(AppConfig.URL_DATABASE, AppConfig.USERNAME, AppConfig.PASSWORD);
                if(connection != null) System.out.println("Connect DB successfully");
            } catch (SQLException e) {
                throw new SQLException("Connect DB fail " + e.getMessage());
            }
        }

        return  connection;
    }


    public PreparedStatement prepare(String sql) {

        try {
            return connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public PreparedStatement prepareUpdate(String sql) {
        System.out.println(">> "+sql);
        try {
            return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    public void closeConnection() throws SQLException {
        if(connection != null) {
            connection.close();
            System.out.println("Connection is closed");
        }
    }
}
