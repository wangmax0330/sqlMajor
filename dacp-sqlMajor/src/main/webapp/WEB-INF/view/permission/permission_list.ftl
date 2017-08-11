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
        <th>分公司</th>
        <th>员工账号</th>
        <th>员工名称</th>
        <th>员工状态</th>
        <th>性别</th>
        <th>角色/岗位</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody  id="permission_list_tbody">

    </tbody>
</table>

<#--<div class="pagination" id="customer_list_pagination">-->


<#--</div>-->
<#include "./permission_list_tmpl.ftl">
</body>