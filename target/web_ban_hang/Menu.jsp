
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of menu-->
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">Home</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">

            <form action="search" method="get" class="form-inline my-2 my-lg-0">
                <div class="input-group input-group-sm">
                    <input oninput="searchByName(this)" value="${searchTxt}" name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search...">
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-secondary btn-number">
                            <i class="fa fa-search"></i>
                        </button>
                    </div>
                </div>

                <a class="btn btn-success btn-sm ml-3" href="Cart.jsp">
                    <i class="fa fa-shopping-cart"></i> Cart
                    <span class="badge badge-light">${sessionScope.cart.size()}</span>
                </a>

            </form>
        </div>
    </div>
</nav>
<section class="jumbotron " style="
    background-image: url('https://images.pexels.com/photos/82256/pexels-photo-82256.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260');
    ">
</section>
<!--end of menu-->
