<#setting number_format="#">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Strass 管理平台</title>
<#include "../res/base.ftl">

    <script type="text/javascript" src="${rc.contextPath}/resources/permission/permission-list.js"></script>
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
                    <li><a href="../customer/index">客户管理</a></li>

                    <#if permission_state??>
                        <li class="active"><a href="../permission/index">权限管理</a></li>
                    <#else>

                    </#if>


                </ul>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <div class="span3">
        <div class="well sidebar-nav">
            <ul class="nav nav-list">
                <li class="nav-header"><i class="icon-user"></i>权限控制</li>
                <li class="active"><a href="#permission.permission-list"
                                      onclick="tk.load('${rc.contextPath}/page/permission.permission_list','',tk.menuFocus,[this]);">外网访问权限</a>
                </li>
            <#--<li class="nav-header"><i class="icon-signal"></i>数据统计</li>-->
            <#--<li><a href="visitor-stats.html">Visitors</a></li>-->
            <#--<li class="nav-header"><i class="icon-wrench"></i>个性化</li>-->
            <#--<li><a href="my-profile.html">My profile</a></li>-->
            <#--<li><a href="#">Settings</a></li>-->
            </ul>
        </div>
    </div>
    <div class="span12">
        <div>
            <div class="row-fluid">
            <#--<div class="page-header">-->
            <#--<h1>Users <small>All users</small></h1>-->
            <#--</div>-->
                <div class="form-inline permission_search_panel">
                    <div class="form-group">
                        <label for="_search_key_account_input">员工搜索</label>
                        <input type="text" class="form-control" id="_search_key_account_input"
                               placeholder="例如:王二或80061">

                        <label for="_search_key_role_input">岗位搜索</label>
                        <input type="text" class="form-control" id="_search_key_role_input" placeholder="例如:设计师">

                        <label for="_search_key_dept_input">部门搜索</label>
                        <input type="text" class="form-control" id="_search_key_dept_input" placeholder="例如:交付一部">
                        <label for="_search_key_state_select">是否启用</label>
                        <select class="form-control" id="_search_key_state_select">
                            <option value='' selected="selected"></option>
                            <option value='1'>启用</option>
                            <option value='0'>禁用</option>
                        </select>
                    </div>
                </div>
                <br/>
            <#--<a href="new-user.html" class="btn btn-success">New User</a>-->
            </div>
        <#include "./permission_list.ftl">
        </div>
    </div>
</div>

<footer class="well navbar-fixed-bottom">
    <p class='copyright'>Copyright © 2016 信息中心. All Rights Reserved.</p>
</footer>

</div>

</body>
</html>
