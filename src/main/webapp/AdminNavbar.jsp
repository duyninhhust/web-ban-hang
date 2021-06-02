<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/web_ban_hang_war/admin/manager-product">Admin Manager</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="${pageId != 2?"active": ""}"><a href="/web_ban_hang_war/admin/manager-product">Manager Product</a></li>
            <li class="${pageId == 2?"active": ""}"><a href="/web_ban_hang_war/admin/bill">Manager Bill</a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${acc.username}</a></li>
            <li><a href="/web_ban_hang_war/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
        </ul>
    </div>
</nav>