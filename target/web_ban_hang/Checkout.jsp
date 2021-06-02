<%@ page import="java.text.NumberFormat" %>
<%@ page import="com.hust.nhom2.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.hust.nhom2.dao_impl.ProductDaoImpl" %>
<%@ page import="com.hust.nhom2.dao.ProductDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CheckOut</title>
    <link rel="shortcut icon" href="https://set.hust.edu.vn/storage/logo/set-logo.png"
          type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>

<body>
<jsp:include page="Menu.jsp"></jsp:include>

<div class="container">
    <div class="py-5 text-center">

        <h2>Checkout form</h2>
    </div>

    <div class="row">
        <div class="col-md-6 order-md-2 mb-4">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-muted">Your cart</span>
                <span class="badge badge-secondary badge-pill">${sessionScope.cart.size()}</span>
            </h4>

            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col" class="border-0 bg-light">
                            <div class="p-2 px-3 text-uppercase">Product</div>
                        </th>
                        <th scope="col" class="border-0 bg-light">
                            <div class="py-2 text-uppercase">Price</div>
                        </th>
                        <th scope="col" class="border-0 bg-light">
                            <div class="py-2 text-uppercase">Quantity</div>
                        </th>
                        <th scope="col" class="border-0 bg-light">
                            <div class="py-2 text-uppercase">Delete</div>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        ProductDao productDAO = new ProductDaoImpl();
                        NumberFormat nf = NumberFormat.getInstance();
                        nf.setMinimumIntegerDigits(0);
                        double total = 0;
                        ArrayList<Cart> cart=null;
                        if(session.getAttribute("cart")!=null){
                            cart = (ArrayList<Cart>) session
                                    .getAttribute("cart");}
                    %>
                    <%
                        if (cart != null) {
                            for (Cart c : cart) {
                                total = total
                                        + (c.getQuantity() * productDAO.findById(
                                        c.getProduct().getId()).getPrice());
                    %>
                    <tr>
                        <td scope="row">
                            <div class="p-2">
                                <img src="<%=productDAO.findById(c.getProduct().getId())
							.getImage()%>" alt="" width="70" class="img-fluid rounded shadow-sm">
                                <div class="ml-3 d-inline-block align-middle">
                                    <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block"><%=productDAO.findById(c.getProduct().getId())
                                            .getName()%></a></h5><span class="text-muted font-weight-normal font-italic"></span>
                                </div>
                            </div>
                        </td>
                        <td class="align-middle"><strong><%=nf.format(productDAO.findById(
                                c.getProduct().getId()).getPrice())%></strong></td>
                        <td class="align-middle">
                            <a href="cart?command=deleteCart&id=<%=c.getProduct().getId()%>"><button class="btnSub">-</button></a>
                            <strong><%=c.getQuantity()%></strong>
                            <a href="cart?command=addCart&id=<%=c.getProduct().getId()%>"><button class="btnAdd">+</button></a>
                        </td>
                        <td class="align-middle"><a href="cart?command=removeCart&id=<%=c.getProduct().getId()%>" class="text-dark">
                            <button type="button" class="btn btn-danger">Delete</button>
                        </a>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col-md-6 order-md-1">
            <h4 class="mb-3">Thông tin khách hàng</h4>
            <form class="needs-validation" novalidate method="post" action="checkout">
                <div class="row">
                    <div class="col-md-6">
                        <label for="name">Tên khách hàng</label>
                        <input type="text" class="form-control" name="name" id="name" placeholder="" value="" required>
                        <div class="invalid-feedback">
                            Valid first name is required.
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="phoneNumber">Số điện thoại</label>
                        <div class="input-group">
                            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Số điện thoại" required>
                            <div class="invalid-feedback" style="width: 100%;">
                                Your username is required.
                            </div>
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" name="address" id="address" placeholder="" required>
                    <div class="invalid-feedback">
                        Please enter your shipping address.
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Đặt hàng</button>

            </form>
        </div>
    </div>
</div>


</body>

</html>
</html>
