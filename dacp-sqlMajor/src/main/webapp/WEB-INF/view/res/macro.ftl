<!--一级导航栏-->
<#macro header_bar contextPath active>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Strass Administration</a>
            <div class="btn-group pull-right">
                <a class="btn" href="my-profile.html"><i class="icon-user"></i> Admin</a>
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="my-profile.html">Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Logout</a></li>
                </ul>
            </div>
            <div class="nav-collapse">
                <ul class="nav">
                    <li><a href="index.html">Home</a></li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Users <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="new-user.html">New User</a></li>
                            <li class="divider"></li>
                            <li><a href="users.html">Manage Users</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Roles <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="new-role.html">New Role</a></li>
                            <li class="divider"></li>
                            <li><a href="roles.html">Manage Roles</a></li>
                        </ul>
                    </li>
                    <li><a href="stats.html">Stats</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</#macro>
<!---->

<!--二级侧边栏-->
<#macro secondary_bar contextPath>
<div class="row-fluid">
    <div class="span3">
        <div class="well sidebar-nav">
            <ul class="nav nav-list">
                <li class="nav-header"><i class="icon-wrench"></i> Administration</li>
                <li class="active"><a href="users.html">Users</a></li>
                <li><a href="roles.html">Roles</a></li>
                <li class="nav-header"><i class="icon-signal"></i> Statistics</li>
                <li><a href="stats.html">General</a></li>
                <li><a href="user-stats.html">Users</a></li>
                <li><a href="visitor-stats.html">Visitors</a></li>
                <li class="nav-header"><i class="icon-user"></i> Profile</li>
                <li><a href="my-profile.html">My profile</a></li>
                <li><a href="#">Settings</a></li>
                <li><a href="#">Logout</a></li>
            </ul>
        </div>
    </div>
</#macro>

<#macro footer_left contextPath>
    <div class="footer_left">
   		<ul>
          <li><a href="#1"><img src="${rc.contextPath}/dev-lib/assets/images/m1.png"></a></li>
          <li><a class="submenu" ><img src="${rc.contextPath}/dev-lib/assets/images/m2.png"></a>
              <div class="drop_menu_div" style="display:none">
                <ul>
                    <li><a href="#1">新情境...</a></li>
                    <li><a href="#1">新项目...</a></li>
                    <li><a href="#1">新目标...</a></li>
                </ul>
                <i></i>
               </div>
          </li>
          <li ><a href="#1"><img src="${rc.contextPath}/dev-lib/assets/images/m3.png"></a></li>
        </ul>
    </div>
</#macro>
<!--载入loading效果-->
<#macro loadingPage contextPath>
<div class='loading_wp'>
	<div>
		<img src='${rc.contextPath}/dev-lib/assets/images/loading.gif' />
		<p><span class='colorBlue'>小提示</span>：正在进入后台管理系统!</p>
	</div>
</div>
</#macro>

<!--数据选择器-->
<#macro dataSelector single param...>
    <div class='add_workgrounp <#if single>single</#if>' style="background:#fff;margin:0;" >
    	<div class='grounp_add'>
    		<input class="selectedInput" id="${param["inputId"]}" name='${param["inputId"]}' value="${param["initIds"]}" type="hidden">
    		<a class="add_idcon" func="${param["func"]!''}"><i></i><#if param["title"]??>${param["title"]}<#else>用户</#if></a>
    		<a><input class="dataSelectorInput" relObjId="${param["relObjId"]!'0'}" relObjId2="${param["relObjId2"]!'0'}" action="${param["action"]}" style="border: none;height: 22px;" type='text' value=''></input></a> 
    	</div>
    	<div class="cwidthdiv" style="display:none;"></div>
    </div>
    <script>
    	var initIds = '${param["initIds"]}'.split(",");
    	var initVals = '${param["initVals"]}'.split(",");
    	if('${param["initIds"]}'!=""){
	    	var $add_idconInit = $("#${param["inputId"]}").closest(".add_workgrounp").find(".add_idcon");
	    	$.each(initIds,function(i){
	    		$add_idconInit.before("<span class='z' did='"+initIds[i]+"'>"+initVals[i]+"<i class='del-dataSelector'></i></span>");
	    	});
    	}
    </script>
</#macro>

<#macro imgPath>http://121.42.212.240/</#macro>