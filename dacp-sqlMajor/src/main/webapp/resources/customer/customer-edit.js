var customer = customer || {};
customer.isAutoSave = false;
customer.publishState = 1;
customer.assort = '';
customer.id = 0;
customer.tagIds = '';
(function($) {
	// ---------------------博客的表单处理
	customer.init = function() {
		var $form = $('form#form-customer-edit');
		var title = $form.find("input[name='title']");
		if (title.val() == null || $.trim(title.val()).length == 0) {
			$form.find("input[name='title']").next().show();
			alert("博客一个好的标题很重要");
			return false;
		} else {
			customer.title = title.val();
			$form.find("input[name='title']").next().hide();
		}
		var tagsId = $form.find("input[name='tagsId']");
		if (tagsId.val() == null || $.trim(tagsId.val()).length == 0) {
			alert("给博客选个好的标签,以后好分类查询");
			return false;
		} else {
			customer.tagIds = tagsId.val();
		}
		customer.id = $form.find("input[name='id']").val();
		customer.content = editor.html();
		return true;
	};
	// ----------------博客内容自动保存
	customer.autoSaveBlog = function(editor) {
		// // 使用方法名字执行方法
		// var t1 = window.setTimeout(customer.saveBlog, 1000);
		// var t2 = window.setTimeout("hello()", 3000); // 使用字符串执行方法
		// window.clearTimeout(t1); // 去掉定时器
	};
	// -----------------博客保存到草稿箱
	customer.saveBlog = function() {
		$.ajax({
			url : contextPath + "/a/customer/saveBlog",
			dataType : "json",
			type : "post",
			async : false,
			data : {
				bid : customer.id,
				title : customer.title,
				content : customer.content,
				publishState : customer.publishState,
				tagIds : customer.tagIds
			},
			error : function(data) {
				alert("请求出错了");
			}
		}).done(function(data) {
			if (data.isSuc == '1') {
				if(customer.publishState==1){
					alert("发布成功");
				}else{
					alert("保存草稿成功");
				}
			} else {
				alert("保存失败,    "+data.msg+"   请选择[取得HTML] 自行保存博客内容,以免丢失信息");
			}
		});
	};

	$("form#form-customer-edit").delegate("#submit_publish", "click", function() {
		if(customer.init()){
			customer.init();
			customer.publishState = 1;
			$(this).next().show();
			customer.saveBlog();
			$(this).next().hide();
		}
	});
	$("form#form-customer-edit").undelegate("#submit_save", "click").delegate("#submit_save", "click", function() {
		if(customer.init()){
			customer.publishState = 0;
			$(this).next().show();
			customer.saveBlog();
			$(this).next().hide();
		}
	});
	
	$("form#form-customer-edit").undelegate("input[name=showHtml]", "click").delegate(
			"input[name=showHtml]",
			"click",
			function() {
				document.getElementById("preview_customer").innerHTML = "<textarea  rows=12 style='width:100%'>"
						+ editor.html() + "</textarea>";
			});
	$("form#form-customer-edit").undelegate("input[name=previewHtml]", "click").delegate("input[name=previewHtml]",
			"click", function() {
				document.getElementById("preview_customer").innerHTML = editor.html();
				SyntaxHighlighter.all();
			});

})(jQuery);
