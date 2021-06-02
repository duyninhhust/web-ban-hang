package com.hust.nhom2.controller;

import com.hust.nhom2.model.Cart;
import com.hust.nhom2.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CartController", urlPatterns = "/cart")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<Cart> cart = new ArrayList<Cart>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String id = request.getParameter("id");
//        String image = request.getParameter("image");
        if (command.equals("addCart")) {
            Product p = new Product(Integer.parseInt(id), "", 0.0, "",
                    "" , false, 0);
            addToCart(p);
            // sau khi them vao gio hang ta se chuyen toi trang gio hang
            // can tao session de luu tru gia tri
            HttpSession session = request.getSession();

            // ta test xem gio hang co them duoc ko?
            System.out.println(cart.size());
            session.setAttribute("cart", cart);
            response.sendRedirect("Cart.jsp");
        } else{
            if(command.equals("deleteCart")){
                Product p = new Product(Integer.parseInt(id),  "", 0.0, "",
                        "" , false, 0);
                deleteFromCart(p);
                HttpSession session = request.getSession();

                // ta test xem gio hang co them duoc ko?
                System.out.println(cart.size());
                session.setAttribute("cart", cart);
                response.sendRedirect("Cart.jsp");
            } else{
                if(command.equals("removeCart")){
                    Product p = new Product(Integer.parseInt(id),  "", 0.0, "",
                            "" , false, 0);
                    removeFromCart(p);
                    HttpSession session = request.getSession();

                    //lưu vào session
                    session.setAttribute("cart", cart);
                    response.sendRedirect("Cart.jsp");
                }else{
                    if(command.equals("setCart")){
                        Product p = new Product(Integer.parseInt(id),  "", 0.0, "",
                                "" , false, 0);
                        setCart(p,Integer.parseInt((String) request.getParameter("soluong")));
                        HttpSession session = request.getSession();

                        session.setAttribute("cart", cart);
                        response.sendRedirect("Cart.jsp");
                    }
                }
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    private String removeFromCart(Product p) {
        for (Cart item : cart) {
            if (item.getProduct().getId() == p.getId()) {
                cart.remove(item);
                return "cart";
            }
        }
        return "cart";
    }

    private String setCart(Product p, int s) {
        for (Cart item : cart) {
            if (item.getProduct().getId() == p.getId()) {
                item.setQuantity(s);
                return "cart";
            }
        }
        Cart c = new Cart();
        c.setProduct(p);
        c.setQuantity(s);
        cart.add(c);
        return "cart";
    }

    // phuongw thuc them san pham moi vao trong gio hang
    public String addToCart(Product p) {
        for (Cart item : cart) {
            if (item.getProduct().getId() == p.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return "cart";
            }
        }
        Cart c = new Cart();
        c.setProduct(p);
        c.setQuantity(1);
        cart.add(c);
        return "cart";
    }

    // phuongw thuc giam bot 1 san pham khoi trong gio hang
    public String deleteFromCart(Product p) {
        for (Cart item : cart) {
            if (item.getProduct().getId() == p.getId() && item.getQuantity()>1) {
                item.setQuantity(item.getQuantity() - 1);
                return "cart";
            }
        }
        return "cart";
    }

}
