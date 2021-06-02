package com.hust.nhom2.filter;

import com.hust.nhom2.model.MyConnection;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AllFilter", urlPatterns = "/*")
public class AllFilter implements Filter {
    MyConnection myConnection = new MyConnection();
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // viết tiếng việt
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "*");
        // kết nối database
        try{
            myConnection.connectDB();
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
