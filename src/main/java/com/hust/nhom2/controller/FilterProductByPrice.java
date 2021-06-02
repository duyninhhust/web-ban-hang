package com.hust.nhom2.controller;

import com.hust.nhom2.dao.CategoryDao;
import com.hust.nhom2.dao.ProductDao;
import com.hust.nhom2.dao_impl.CategoryDaoImpl;
import com.hust.nhom2.dao_impl.ProductDaoImpl;
import com.hust.nhom2.model.Category;
import com.hust.nhom2.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "FilterProductByPrice", urlPatterns = "/product")
public class FilterProductByPrice extends HttpServlet {
    ProductDao productDao = new ProductDaoImpl();
    CategoryDao categoryDao = new CategoryDaoImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // lấy dữ liệu
            double p1 = Double.parseDouble(request.getParameter("price1"));
            double p2 = Double.parseDouble(request.getParameter("price2"));

            List<Category> listCategory = categoryDao.findAll();
            Product lastProduct = productDao.getLastProduct();
            List<Product> listProduct = productDao. getProductByPrice(p1,p2);

            // đổ lên view
            request.setAttribute("listProduct",listProduct);
            request.setAttribute("listCategory",listCategory);
            request.setAttribute("lastProduct",lastProduct);

            request.getRequestDispatcher("Home.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
