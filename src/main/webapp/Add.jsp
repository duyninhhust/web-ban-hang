<%--
  Created by IntelliJ IDEA.
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ADD Product</title>
    <link rel="shortcut icon" href="https://set.hust.edu.vn/storage/logo/set-logo.png"
          type="image/x-icon">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/manager.css" rel="stylesheet" type="text/css"/>
    <link href="css/style.css" rel="stylesheet" type="text/css">

    <style>
        img{
            width: 200px;
            height: 120px;
        }
    </style>
<body>
<div class="container">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/web_ban_hang_war">PhoneShop</a>
            </div>
            <ul class="nav navbar-nav">
                <li class="active"><a href="manager-product">Manager Product</a></li>
                <%--                        <li><a href="signup">Đăng kí</a></li>--%>
                <li><a href="#"></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${acc.username}</a></li>
                <li><a href="/web_ban_hang_war/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
            </ul>
        </div>
    </nav>
    <div class="table-wrapper">
        <div class="table-title">
            <div class="row">
                <div class="col-sm-6">
                    <h2>ADD <b>Product</b></h2>
                </div>
                <div class="col-sm-6">
                </div>
            </div>
        </div>
    </div>
    <div id="editEmployeeModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="add" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Add Product</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><a href="manager-product">&times;</a></button>
                    </div>
                    <div class="modal-body">

                        <div class="form-group">
                            <label>Name</label>
                            <input  name="name" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Image</label>
                            <input  name="image" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Price</label>
                            <input  name="price" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Introduction</label>
                            <textarea name="introduction" class="form-control" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Category</label>
                            <select name="category" class="form-select" aria-label="Default select example">
                                <c:forEach items="${listCategory}" var="o">
                                    <option  value="${o.id}">${o.name}</option>
                                </c:forEach>
                            </select>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <input type="submit" class="btn btn-success" value="Add">
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>


<script src="js/manager.js" type="text/javascript"></script>
</body>
</html>