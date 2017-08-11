var permission = permission || {};
$(function() {
    console.info("000000000000000000");
    permission.permissionDoHash = function(hash) {
        console.info(hash);
        if (hash) {
            if ("#permission.permission-list" == hash) {
            	console.info("1111111111111111111");
                tk.load(contextPath + '/page/permission.permission_list', '', tk.menuFocus, [$("#sidebar li a[href='#permission.permission-list']")[0]]);
            } else if ("#permission.permission-new" == hash) {
                console.info("2222222222222222222");
                console.debug($("#sidebar  li a[href='#permission.permission-new']")[0]);
                tk.load(contextPath + '/permission/editCustomer/0', '', tk.menuFocus, [$("#sidebar li a[href='#permission.permission-new']")[0]]);
            } else if (hash.indexOf("#permission.permission-edit") > -1) {
                console.info("333333333333333");
                var cid = hash.substring(hash.indexOf("!bid=") + 5);
                tk.load(contextPath + '/permission/editCustomer/' + cid, '', tk.menuFocus, [$("#sidebar li a[href='#permission.permission-edit']")[0]]);
            } else {
                console.info("4444444444444444");
            	$(".menu").find(".toggle li a[href='" + hash + "']").trigger("click");
            }
        } else {
            tk.load(contextPath + '/page/permission.index', '', tk.menuFocus, [$("#sidebar li a[href='#permission.permission-list']")[0]]);
        }
    };
    // permission.permissionDoHash(firstClickMenu);
});