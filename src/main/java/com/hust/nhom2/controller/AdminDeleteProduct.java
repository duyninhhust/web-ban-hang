package com.hust.nhom2.controller;

import com.hust.nhom2.dao.ProductDao;
import com.hust.nhom2.dao_impl.ProductDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminDeleteProduct", urlPatterns = "/admin/delete")
public class AdminDeleteProduct extends HttpServlet {

    ProductDao productDao = new ProductDaoImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        System.out.println(id);
        try {
            productDao.delete(Integer.parseInt(id));
            response.sendRedirect("manager-product");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
