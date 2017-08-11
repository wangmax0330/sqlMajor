<!doctype html>
<html>
<head>
</head>
<body>
    <#--<h2 class="sub-header">Section title</h2>-->
    <table class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>客户手机号码</th>
        <th>分公司</th>
        <th>客户名称</th>
        <th>客户编码</th>
        <th>性别</th>
        <th>客户状态</th>
        <th>客户项目状态</th>
        <th>备案时间</th>
        <th>首次到访时间</th>
        <th>楼盘名称</th>
        <th>门牌号</th>
        <th>建筑面积</th>
        <th>套内面积</th>
        <th>营销员</th>
        <th>角色</th>
        <th>营销部门</th>
        <th>非营销员</th>
        <th>角色</th>
        <th>非营销部门</th>
        <th>设计师</th>
        <th>角色</th>
        <th>设计部门</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody  id="customer_list_tbody">

    </tbody>
</table>

<#--<div class="pagination" id="customer_list_pagination">-->


<#--</div>-->
<#include "./customer_list_tmpl.ftl">
</body>