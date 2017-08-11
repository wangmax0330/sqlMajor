var customer = customer || {};
$(function() {
	var $customer_list_tbody = $("#customer_list_tbody");
	var $customer_list_pagination = $("#customer_list_pagination");
	var $_tpl_customer_list_tbody = $("#_tpl_customer_list_tbody");
    var $_tpl_customer_pagination = $("#_tpl_customer_pagination");


   	 console.info("+++++++++"+ $("input[id='_search_key_input']").attr("value"));

	customer.customerListing = function(pageIndex) {
		//tk.mask();
        var _search_key = $("#_search_key_input").val();
        console.info("1111111111111111");
        console.info(_search_key);

		$.ajax({
			url : contextPath + "/customer/list",
			dataType : "json",
			data : {
				pageSize : 20,
				pageIndex : pageIndex,
                search_key :_search_key
			},
			type : "post"
		}).done(function(result) {
			//tk.unmask();
			// tk.checkIsTimeout(data);
			if (result.isSuc == 1) {
				tk.showSuccessMsg("请求成功");
			   $customer_list_tbody.html($_tpl_customer_list_tbody.tmpl(result));
				$customer_list_pagination.html($_tpl_customer_pagination.tmpl(1));
			} else {
				alert("请求错误:  "+result.msg);
			}
		});
	};
	// customer.customerListing(1);

	$("body").undelegate("#_query_customer_btn", "click").delegate("#_query_customer_btn", "click", function() {
		customer.customerListing(1);
	});

    // $('#_search_key_input').bind('keypress',function(event) {
    //     if (event.keyCode == "13") {
    //         alert('你输入的内容为：' + $('#_search_key_input').val());
    //     }
    // }
    $("#_search_key_input").keydown(function(event){
        if(event.which == "13") {
            customer.customerListing(1);
        }
    });
    $(".customer_search_panel").undelegate("input[name='password']", "keydown").delegate("input[name='password']", "keydown", function(e) {
        var key = e.which;
        if (key == 13) {
            login.toLogin(false);
        }
    });
        $customer_list_tbody.undelegate("#customer_del", "click").delegate("#customer_del", "click", function() {
		var $this = $(this);
		var id = $this.attr("c-id");
		if (confirm('确认对此地址进行删除操作？')) {
			// customer.customerDelete(id);
		}
	});
});