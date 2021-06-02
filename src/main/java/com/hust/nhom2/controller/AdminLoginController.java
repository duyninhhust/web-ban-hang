package com.hust.nhom2.controller;

import com.hust.nhom2.dao.AccountDao;
import com.hust.nhom2.dao_impl.AccountDaoImpl;
import com.hust.nhom2.model.Account;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AdminLoginController", urlPatterns = "/login")
public class AdminLoginController extends HttpServlet {

    AccountDao accountDao = new AccountDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // lấy dữ liệu
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Account account = accountDao.checkLogin(username,password);

            if(account == null) {
                String mess = "Wrong username or password";
                request.setAttribute("mess",mess);
                request.getRequestDispatcher("Login.jsp").forward(request,response);
            }
            else {
                //set Cookie
                Cookie cookie = new Cookie("userId",  String.valueOf(account.getId()));
                cookie.setMaxAge(2 * 60 * 60);
                response.addCookie(cookie);
                request.setAttribute("acc",account);
                response.sendRedirect(request.getContextPath() + "/admin/manager-product");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/Login.jsp").forward(request,response);
    }

}

