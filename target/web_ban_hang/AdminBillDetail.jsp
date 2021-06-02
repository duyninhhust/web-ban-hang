<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Chi tiết đơn hàng</title>
    <link rel="shortcut icon" href="https://set.hust.edu.vn/storage/logo/set-logo.png"
          type="image/x-icon">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="${pageContext.request.contextPath}/css/manager.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="container">
    <jsp:include page="AdminNavbar.jsp"></jsp:include>
    <h2>Chi tiết đơn hàng</h2>
    <table class="table">
        <thead>
        <tr>
            <th style="width: 200px">Id đơn hàng</th>
            <td>${bill.id}</td>
        </tr>
        <tr>
            <th>Họ tên</th>
            <td>${bill.name}</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>Phone</th>
            <td>${bill.phoneNumber}</td>
        </tr>
        <tr>
            <th>Địa chỉ</th>
            <td>${bill.address}</td>
        </tr>
        <tr>
            <th>Trạng thái đơn hàng</th>
            <td>${status}</td>
        </tr>
        </tbody>
    </table>


    <table class="table table-striped table-hover">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Image</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var = "i" begin = "0" end = "${billDetail.size()-1}">
            <tr>
                <td>${billDetail.get(i).productId}</td>
                <td>${products.get(i).name}</td>
                <td>
                     <img src=" ${products.get(i).image}" alt="${products.get(i).name}" style="width: 70px;height: 70px">
                </td>
                <td>${billDetail.get(i).price}</td>
                <td>${billDetail.get(i).quantity}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${bill.status == 1}">
        <a href="/web_ban_hang_war/admin/bill/confirm?id=${bill.id}" class="btn btn-warning" style="padding: 10px;font-size: 30px;">Xác nhận</a>
     </c:if>



    <p style="font-size: 30px;font-weight: bold;display: inline;float: right">Tổng tiền : ${totalMoney} $</p>
</div>

</body>
</html>
