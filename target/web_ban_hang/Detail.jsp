
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${product.name}</title>
        <link rel="shortcut icon" href="https://set.hust.edu.vn/storage/logo/set-logo.png"
              type="image/x-icon">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <link href="css/style.css" rel="stylesheet" type="text/css"/>

        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box
            }
            .show{
                width: 400px;
                height: 400px;
                z-index: 999;
            }
            #show-img { width: 400px; height: 400px; }
            .small-img{
                width: 350px;
                height: 70px;
                margin-top: 10px;
                position: relative;
                left: 25px;
            }
            .small-img .icon-left, .small-img .icon-right{
                width: 12px;
                height: 24px;
                cursor: pointer;
                position: absolute;
                top: 0;
                bottom: 0;
                margin: auto 0;
            }
            .small-img .icon-left{

            }
            .small-img .icon-right{
                right: 0;
            }
            .small-img .icon-left:hover, .small-img .icon-right:hover{
                opacity: .5;
            }
            .small-container{
                width: 310px;
                height: 70px;
                overflow: hidden;
                position: absolute;
                left: 0;
                right: 0;
                margin: 0 auto;
            }
            .small-container div{
                width: 800%;
                position: relative;
            }

            .small-container .show-small-img{
                width: 70px;
                height: 70px;
                margin-right: 6px;
                cursor: pointer;
                float: left;
            }
            .small-container .show-small-img:last-of-type{
                margin-right: 0;
            }

            .form-group.col-md-4,.form-group.col-md-6 {
                font-weight: bold;
                font-size: 16px;
            }
            .form-group.col-md-6 {
                margin-left: -16px;
            }
            .comment {
                width: max-content;
                position: relative;
                padding: 10px;
                border-radius: 15px;
                background-color: #EBEDF0;
                margin: 10px 0px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="home">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="#">${product.name}</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
            <div class="container">
                <div class="row">
                <jsp:include page="Left.jsp"></jsp:include>
                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-6 border-right">

                                    <div class="show" href="${product.image}">
                                        <img src="${product.image}" id="show-img">
                                    </div>
                                    <div class="small-img">
                                        <img src="images/online_icon_right@2x.png" class="icon-left" alt="" id="prev-img">
                                        <div class="small-container">
                                            <div id="small-img-roll">
                                                <img src="${product.image}" class="show-small-img" alt="">
                                                <c:forEach items="${listImage}" var="o">
                                                    <img src="${o.source}" class="show-small-img" alt="">
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <img src="images/online_icon_right@2x.png" class="icon-right" alt="" id="next-img">
                                    </div>
                                        </article>
                                    </aside>
                                </aside>
                                <aside class="col-sm-6">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${product.name}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">US $</span><span class="num">${product.price}</span>
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>
                                                    ${product.introduction}
                                                </p></dd>
                                        </dl>

                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <select class="form-control form-control-sm" style="width:70px;">
                                                            <option> 1 </option>
                                                            <option> 2 </option>
                                                            <option> 3 </option>
                                                        </select>
                                                    </dd>
                                                </dl>  <!-- item-property .// -->
                                            </div> <!-- col.// -->

                                        </div> <!-- row.// -->
                                        <hr>
                                        <a href="cart?command=addCart&id=${product.id}" class="btn btn-lg btn-primary text-uppercase"> <i class="fa fa-shopping-cart"></i> Buy now </a>
                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->


                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <form action="comment?id=${product.id}" method="post">
                <div class="panel-heading" style="background-color: #0cafe5;color: white;padding: 5px 18px;">
                    <div class="panel-title" style="font-size: 25px">Leave your comment</div>
                </div>
                <div class="row">
                    <div class="form-group col-md-4">
                        <label>Your Name</label>
                        <input class ="form-control" type="text" name="name" placeholder="Tiến Bịp" required="required">
                    </div>
                    <div class="clearfix"></div>
                    <div class="form-group col-md-4">
                        <label>Your Email</label>
                        <input class ="form-control" type="email" name="email" placeholder="abc@gmail.com(not required)" >
                    </div>
                    <div class="clearfix"></div>
                    <div class="form-group col-md-4">
                        <label>Your Phone Number</label>
                        <input class ="form-control" type="text" name="sdt" placeholder="10 numbers(not required)" >
                    </div>
                    <div class="clearfix"></div>
                </div>

                <div class="form-group col-md-6">
                    <label>Your Comment</label>
                    <textarea class="form-control" rows="4" name="comment" placeholder="Comment here" required="required"></textarea>
                </div>
                <div class="clearfix"></div>
                <div class="form-group col-md-6">
                    <button class="btn btn-primary bt-lg btn-block" )"
                            type="submit" >POST</button>
                </div>
            </form>
            <p id="print"></p>
            <div class="panel-heading" style="background-color: #0cafe5;color: white;padding: 5px 18px;">
                <div class="panel-title" style="font-size: 25px">Comments</div>
            </div>
            <div class="content">
                <c:forEach items="${comments}" var="c">
                    <div class="comment">
                        <strong>${c.name}</strong>
                        <div>${c.comment}</div>
                    </div>
                </c:forEach>
            </div>

        </div>
       <jsp:include page="Footer.jsp"></jsp:include>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/scripts/zoom-image.js"></script>
        <script src="${pageContext.request.contextPath}/scripts/main.js"></script>
    </body>
<%--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
<%--    <script>--%>
<%--        function comment() {--%>
<%--            $.ajax({--%>
<%--                url: "/web_ban_hang_war/comment",--%>
<%--                type: "post", //send it through post method--%>
<%--                data: {--%>
<%--                },--%>
<%--                success: function (data) {--%>
<%--                    var row = document.getElementById("content");--%>
<%--                    row.innerHTML += data;--%>
<%--                },--%>
<%--                error: function (xhr) {--%>
<%--                    //Do Something to handle error--%>
<%--                }--%>
<%--            });--%>
<%--        }--%>
<%--    </script>--%>
</html>
