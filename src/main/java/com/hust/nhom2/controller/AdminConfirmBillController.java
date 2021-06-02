package com.hust.nhom2.controller;

import com.hust.nhom2.dao.BillDao;
import com.hust.nhom2.dao_impl.BillDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminConfirmBillController",value = "/admin/bill/confirm")
public class AdminConfirmBillController extends HttpServlet {

    BillDao billDao = new BillDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            billDao.confirmBill(id);
            System.out.println(billDao.confirmBill(id));
            response.sendRedirect("/web_ban_hang_war/admin/bill/view?id="+id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
