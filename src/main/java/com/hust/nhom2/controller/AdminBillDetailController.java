package com.hust.nhom2.controller;

import com.hust.nhom2.dao.BillDao;
import com.hust.nhom2.dao.BillDetailDao;
import com.hust.nhom2.dao.ProductDao;
import com.hust.nhom2.dao_impl.BillDaoImpl;
import com.hust.nhom2.dao_impl.BillDetailDaoImpl;
import com.hust.nhom2.dao_impl.ProductDaoImpl;
import com.hust.nhom2.model.Bill;
import com.hust.nhom2.model.BillDetail;
import com.hust.nhom2.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminBillDetailController",value = "/admin/bill/view")
public class AdminBillDetailController extends HttpServlet {

    BillDetailDao billDetailDao= new BillDetailDaoImpl();
    BillDao billDao = new BillDaoImpl();
    ProductDao productDao = new ProductDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            List<BillDetail> billDetail = billDetailDao.getAllProductInBill(id);
            Bill bill = billDao.findById(id);
            //status don hang
            String status = "";
            switch (bill.getStatus()) {
                case 1 : {
                    status = "Chờ xử lý";
                    break;
                }
                case 2 : {
                    status = "Đã xác nhận";
                    break;
                }
            }
            //lay tong tien
            double totalMoney = billDetailDao.totalMoney(bill.getId());

            List<Product> products = new ArrayList<>();
            for (int i = 0; i < billDetail.size(); i++) {
                products.add(productDao.findById(billDetail.get(i).getProductId()));
            }
            request.setAttribute("bill",bill);
            request.setAttribute("billDetail",billDetail);
            request.setAttribute("products",products);
            request.setAttribute("status",status);
            request.setAttribute("totalMoney",totalMoney);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/AdminBillDetail.jsp").forward(request,response);
    }
}
