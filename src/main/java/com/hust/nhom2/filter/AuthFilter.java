package com.hust.nhom2.filter;

import com.hust.nhom2.dao.AccountDao;
import com.hust.nhom2.dao_impl.AccountDaoImpl;
import com.hust.nhom2.model.Account;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/admin/*")
public class AuthFilter implements Filter {

    AccountDao accountDao = new AccountDaoImpl();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        Cookie cookies[] = request.getCookies();
        if(cookies != null) {
            for (Cookie c: cookies) {
                if(c != null && c.getName().equals("userId")) {
                    try {
                        Account account = accountDao.findById(Integer.parseInt(c.getValue()));
                        if(account == null) {
                            response.sendRedirect(request.getContextPath() + "/login");
                        }else {
                            chain.doFilter(req, resp);
                        }


                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }
            }
        }

        if (!response.isCommitted()){
            // redirect or dispatch whereever you want
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
