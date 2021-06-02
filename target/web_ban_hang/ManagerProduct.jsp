<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Admin</title>
        <link rel="shortcut icon" href="https://set.hust.edu.vn/storage/logo/set-logo.png"
              type="image/x-icon">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="${pageContext.request.contextPath}/css/manager.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 120px;
                height: 120px;
            }
        </style>
    <body>
        <div class="container">
            <jsp:include page="AdminNavbar.jsp"></jsp:include>
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Manage <b>Product</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <a href="add" class="btn btn-success" ><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>

                        </div>
                    </div>
                </div>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>
                                <span class="custom-checkbox">
                                    <input type="checkbox" id="selectAll">
                                    <label for="selectAll"></label>
                                </span>
                            </th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Image</th>
                            <th>Price</th>
                            <th>Introduction</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listA}" var="o">
                            <tr>
                                <td>
                                    <span class="custom-checkbox">
                                        <input type="checkbox" id="checkbox1" name="options[]" value="1">
                                        <label for="checkbox1"></label>
                                    </span>
                                </td>
                                <td>${o.id}</td>
                                <td>${o.name}</td>
                                <td>
                                    <img src="${o.image}">
                                </td>
                                <td>${o.price} $</td>
                                <td>${o.introduction}</td>
                                <td>
                                    <a href="edit?id=${o.id}"  class="edit" data-toggle="modal"><i class="material-icons" title="Edit">&#xE254;</i></a>
                                    <form method="delete" action="delete">
                                        <a href="delete?id=${o.id}" class="delete" data-toggle="modal"><i class="material-icons" title="Delete">&#xE872;</i></a>
                                    </form>

                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix">
                    <div class="hint-text">Showing <b>3</b> out of <b>${count}</b> entries</div>
                    <ul class="pagination">
                        <c:if test="${tag > 1}">
                            <li class="page-item"><a href="manager-product?index=${tag-1}">Previous</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${endP}" var="i">
                            <li class="page-item ${tag == i?"active":""}"><a href="manager-product?index=${i}" class="page-link ">${i}</a></li>
                        </c:forEach>
                        <c:if test="${tag < endP}">
                            <li class="page-item"><a href="manager-product?index=${tag+1}" class="page-link">Next</a></li>
                        </c:if>
<%--                        <li class="page-item disabled"><a href="#">Previous</a></li>--%>
<%--                        <li class="page-item active"><a href="#" class="page-link">1</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">2</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">3</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">4</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">5</a></li>--%>
<%--                        <li class="page-item"><a href="#" class="page-link">Next</a></li>--%>
                    </ul>
                </div>
            </div>
            <a href="/web_ban_hang_war"><button type="button" class="btn btn-primary">Back to home</button></a>
        </div>
        <script src="${pageContext.request.contextPath}/js/manager.js" type="text/javascript"></script>
    </body>

        
        


</html>