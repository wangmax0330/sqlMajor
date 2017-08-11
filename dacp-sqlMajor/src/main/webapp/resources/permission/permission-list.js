var permission = permission || {};
$(function () {
    var $permission_list_tbody = $("#permission_list_tbody");
    var $permission_list_pagination = $("#permission_list_pagination");
    var $_tpl_permission_list_tbody = $("#_tpl_permission_list_tbody");
    var $_tpl_permission_pagination = $("#_tpl_permission_pagination");


    console.info("+++++++++" + $("input[id='_search_key_input']").attr("value"));

    permission.permissionListing = function (pageIndex) {
        //tk.mask();
        var _search_key_account_input = $("#_search_key_account_input").val();
        var _search_key_role_input = $("#_search_key_role_input").val();
        var _search_key_dept_input = $("#_search_key_dept_input").val();
        var _search_key_state_select=$("#_search_key_state_select").val();

        $.ajax({
            url: contextPath + "/permission/list",
            dataType: "json",
            data: {
                pageSize: 20,
                pageIndex: pageIndex,
                account: _search_key_account_input,
                roleName: _search_key_role_input,
                deptName: _search_key_dept_input,
                state:_search_key_state_select
            },
            type: "post"
        }).done(function (result) {
            //tk.unmask();
            // tk.checkIsTimeout(data);
            if (result.isSuc == 1) {
                tk.showSuccessMsg("请求成功");
                $permission_list_tbody.html($_tpl_permission_list_tbody.tmpl(result));
                $permission_list_pagination.html($_tpl_permission_pagination.tmpl(1));
            } else {
                alert("请求错误:  " + result.msg);
            }
        });
    };
    permission.permissionModify = function (account, state) {
        $.ajax({
            url: contextPath + "/permission/modify",
            type: 'post',
            dataType: "json",
            async: false,//使用同步的方式,true为异步方式
            data: {
                'account': account, 'state': state
            },//这里使用json对象
            success: function (data) {
                console.info("------------------" + data.isSuc);
            },
            fail: function () {

            }
        }).done(function (result) {
            if (result.isSuc == 1) {


            } else {
                alert("请求错误:  " + result.msg);
            }

        });

    };
    permission.permissionListing(1);

    $("body").undelegate("#_query_permission_btn", "click").delegate("#_query_permission_btn", "click", function () {
        permission.permissionListing(1);
    });


    // $('#_search_key_input').bind('keypress',function(event) {
    //     if (event.keyCode == "13") {
    //         alert('你输入的内容为：' + $('#_search_key_input').val());
    //     }
    // }
    $("#_search_key_account_input").keydown(function (event) {
        if (event.which == "13") {
            permission.permissionListing(1);
        }
    });

    $("#_search_key_dept_input").keydown(function (event) {
        if (event.which == "13") {
            permission.permissionListing(1);
        }
    });

    $("#_search_key_role_input").keydown(function (event) {
        if (event.which == "13") {
            permission.permissionListing(1);
        }
    });

    $("#_search_key_state_select").keydown(function (event) {
        if (event.which == "13") {
            permission.permissionListing(1);
        }
    });

    /**
     * 外网访问权限的开启和关闭
     */
    $permission_list_tbody.undelegate(".permission_state", "click").delegate(".permission_state", "click", function () {
        var $this = $(this);
        var trList = $this.parent().parent().find("td");
        console.info("-----------------");
        var account = trList.eq(2).html();
        var state;

        var id = $this.attr("c-id");
        if ($this.hasClass("label-error")) {
            if (confirm('确定开启该账号的外网访问权限？')) {
                state = 1;
                permission.permissionModify(account, state);
                $this.removeClass("label-error").addClass("label-success");
                $this.html("禁用");
            }
        } else {
            if (confirm('确定关闭该账号的外网访问权限？')) {
                state = 0;
                permission.permissionModify(account, state);
                $this.removeClass("label-success").addClass("label-error");
                $this.html("启用");
            }
        }
    });
});