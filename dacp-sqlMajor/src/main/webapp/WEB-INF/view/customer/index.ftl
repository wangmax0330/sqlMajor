<#setting number_format="#">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Users | Strass</title>
    <#include "../res/base.ftl">

   <script type="text/javascript" src="${rc.contextPath}/resources/customer/customer-list.js"></script>
</head>
<body>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-to ggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar">11</span>
                <span class="icon-bar">22</span>
                <span class="icon-bar">33</span>
            </a>
            <a class="brand" href="#">Strass Administration</a>

            <div class="btn-group pull-right">
                <a class="btn" href="#"><i class="icon-user"></i> ${userName}</a>
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="${rc.contextPath}/login/logout">注销</a></li>
                </ul>
            </div>
            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="../customer/index">客户管理</a></li>


                    <#if permission_state??>
                        <li><a href="../permission/index">权限管理</a></li>
                    <#else>

                    </#if>

                </ul>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <#--<div class="span3">-->
        <#--<div class="well sidebar-nav">-->
            <#--<ul class="nav nav-list">-->
                <#--<li class="nav-header"><i class="icon-user"></i>客户管理</li>-->
                <#--<li class="active"><a href="#customer.customer-list" onclick="tk.load('${rc.contextPath}/page/customer.customer_list','',tk.menuFocus,[this]);">客户管理</a></li>-->
            <#--&lt;#&ndash;<li class="nav-header"><i class="icon-signal"></i>数据统计</li>&ndash;&gt;-->
            <#--&lt;#&ndash;<li><a href="visitor-stats.html">Visitors</a></li>&ndash;&gt;-->
            <#--&lt;#&ndash;<li class="nav-header"><i class="icon-wrench"></i>个性化</li>&ndash;&gt;-->
            <#--&lt;#&ndash;<li><a href="my-profile.html">My profile</a></li>&ndash;&gt;-->
            <#--&lt;#&ndash;<li><a href="#">Settings</a></li>&ndash;&gt;-->
            <#--</ul>-->
        <#--</div>-->
    <#--</div>-->

    <div>
            <div class="row-fluid">
                <div class="page-header">
                    <h1>Users <small>All users</small></h1>
                </div>
                <div class="form-inline customer_search_panel">
                    <div class="form-group">
                        <label for="_search_key_input">关键词</label>
                        <input type="text" class="form-control" id="_search_key_input" placeholder="客户名称,手机号码">
                        <button  class="btn btn-default" id="_query_customer_btn">搜索</button>
                    </div>
                </div>
                <br />
                <#--<a href="new-user.html" class="btn btn-success">New User</a>-->
            </div>
        <#include "./customer_list.ftl">
        </div>
    </div>

    <hr>

<footer class="well navbar-fixed-bottom">
    <p class='copyright'>Copyright © 2016 信息中心. All Rights Reserved.</p>
</footer>

</div>

</body>
</html>
