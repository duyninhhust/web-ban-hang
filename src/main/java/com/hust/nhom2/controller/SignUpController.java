package com.hust.nhom2.controller;

import com.hust.nhom2.dao.AccountDao;
import com.hust.nhom2.dao_impl.AccountDaoImpl;
import com.hust.nhom2.model.Account;
import com.hust.nhom2.model.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "SignUpController", urlPatterns ={"/signup"})
public class SignUpController extends HttpServlet {

    AccountDao accountDao = new AccountDaoImpl();
    private List<Cart> cart = new ArrayList<Cart>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String repass = request.getParameter("repass");
            String email = request.getParameter("email");
            String sdt = request.getParameter("sdt");
            String err = "";
            String url = "/Login.jsp";

            if (username.equals("") || password.equals("") || repass.equals("") || email.equals("") || sdt.equals("")){
                err += "Phải nhập đầy đủ thông tin!";
            } else{
                    if (!password.equals(repass)){
                        err += "Tài khoản và mật khẩu không khớp";
                    }
                    if (accountDao.checkAccountExist(username) != null){
                        err += "Tài khoản đã tồn tại !";
                    }else {
                        Pattern pattenObj = Pattern
                                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
                        Matcher matcherObj = pattenObj.matcher(email);
                        if (!matcherObj.matches()) {
                            err += "Email sai định dạng!";
                        }
                    }
            }

            if (err.length() > 0){
                request.setAttribute("err",err);
            }

            try {
                if (err.length() == 0) {
                    HttpSession session = request.getSession();
                    session.setAttribute("cart", cart);
                    accountDao.insert(new Account(username, password, email, sdt));
                    accountDao.checkLogin(username, password);
                    response.sendRedirect(request.getContextPath() + "/login");

                } else {
                    RequestDispatcher rd = getServletContext()
                            .getRequestDispatcher(url);
                    rd.forward(request, response);
                }

            }catch (Exception e){
                e.printStackTrace();
                response.sendRedirect("/Login.jsp");
            }

//            Account account = new Account(username, password, email, sdt);
//            if (!password.equals(repass)){
//                response.sendRedirect("Login.jsp");
//            }
//            else {
//                Account a = accountDao.checkAccountExist(username);
//                if (a == null){
//                    accountDao.insert(account);
//                    response.sendRedirect(request.getContextPath() + "/login");
//                }else {
//                    response.sendRedirect("Login.jsp");
//                }
//            }
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect("/Login.jsp");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/Login.jsp").forward(request,response);

    }
}
