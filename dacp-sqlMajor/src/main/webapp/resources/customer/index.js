var customer = customer || {};
$(function() {
    console.info("000000000000000000");
    customer.customerDoHash = function(hash) {
        console.info(hash);
        if (hash) {
            if ("#customer.customer-list" == hash) {
            	console.info("1111111111111111111");
                tk.load(contextPath + '/page/customer.customer_list', '', tk.menuFocus, [$("#sidebar li a[href='#customer.customer-list']")[0]]);
            } else if ("#customer.customer-new" == hash) {
                console.info("2222222222222222222");
                console.debug($("#sidebar  li a[href='#customer.customer-new']")[0]);
                tk.load(contextPath + '/customer/editCustomer/0', '', tk.menuFocus, [$("#sidebar li a[href='#customer.customer-new']")[0]]);
            } else if (hash.indexOf("#customer.customer-edit") > -1) {
                console.info("333333333333333");
                var cid = hash.substring(hash.indexOf("!bid=") + 5);
                tk.load(contextPath + '/customer/editCustomer/' + cid, '', tk.menuFocus, [$("#sidebar li a[href='#customer.customer-edit']")[0]]);
            } else {
                console.info("4444444444444444");
            	$(".menu").find(".toggle li a[href='" + hash + "']").trigger("click");
            }
        } else {
            tk.load(contextPath + '/page/customer.index', '', tk.menuFocus, [$("#sidebar li a[href='#customer.customer-list']")[0]]);
        }
    };
    // customer.customerDoHash(firstClickMenu);
});