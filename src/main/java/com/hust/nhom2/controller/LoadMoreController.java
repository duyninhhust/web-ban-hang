package com.hust.nhom2.controller;

import com.hust.nhom2.dao.ProductDao;
import com.hust.nhom2.dao_impl.ProductDaoImpl;
import com.hust.nhom2.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LoadMoreController" ,urlPatterns = "/load")
public class LoadMoreController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDaoImpl();
        String amount = request.getParameter("exist");
        int iamount = Integer.parseInt(amount);
        try {
            List<Product> products = productDao.getNext3Product(iamount);
            PrintWriter out = response.getWriter();
            for (Product p : products) {
                out.println("<div class=\"product col-12 col-md-6 col-lg-4\">\n" +
                        "                                <div class=\"card\">\n" +
                        "                                    <a href=\"detail?id="+p.getId()+"\"><img class=\"card-img-top\" src=\""+p.getImage()+"\" alt=\""+p.getName()+"\"></a>\n" +
                        "                                    <div class=\"card-body\">\n" +
                        "                                        <h4 class=\"card-title show_txt\"><a href=\"detail?id="+p.getId()+"\" title=\"View Product\">"+p.getName()+"</a></h4>\n" +
                        "                                        <p class=\"card-text show_txt\"></p>\n" +
                        "                                        <div class=\"row\">\n" +
                        "                                            <div class=\"col\">\n" +
                        "                                                <p class=\"btn btn-danger btn-block\">"+p.getPrice()+" $</p>\n" +
                        "                                            </div>\n" +
                        "\n" +
                        "                                            <div class=\"col\">\n" +
                        "                                                <a href=\"cart?command=addCart&id="+p.getId()+"\" class=\"btn btn-success btn-block\">Add to cart</a>\n" +
                        "                                            </div>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </div>");

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
