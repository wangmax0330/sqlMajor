var permission = permission || {};
permission.isAutoSave = false;
permission.publishState = 1;
permission.assort = '';
permission.id = 0;
permission.tagIds = '';
(function($) {
	// ---------------------博客的表单处理
	permission.init = function() {
		var $form = $('form#form-permission-edit');
		var title = $form.find("input[name='title']");
		if (title.val() == null || $.trim(title.val()).length == 0) {
			$form.find("input[name='title']").next().show();
			alert("博客一个好的标题很重要");
			return false;
		} else {
			permission.title = title.val();
			$form.find("input[name='title']").next().hide();
		}
		var tagsId = $form.find("input[name='tagsId']");
		if (tagsId.val() == null || $.trim(tagsId.val()).length == 0) {
			alert("给博客选个好的标签,以后好分类查询");
			return false;
		} else {
			permission.tagIds = tagsId.val();
		}
		permission.id = $form.find("input[name='id']").val();
		permission.content = editor.html();
		return true;
	};
	// ----------------博客内容自动保存
	permission.autoSaveBlog = function(editor) {
		// // 使用方法名字执行方法
		// var t1 = window.setTimeout(permission.saveBlog, 1000);
		// var t2 = window.setTimeout("hello()", 3000); // 使用字符串执行方法
		// window.clearTimeout(t1); // 去掉定时器
	};
	// -----------------博客保存到草稿箱
	permission.saveBlog = function() {
		$.ajax({
			url : contextPath + "/a/permission/saveBlog",
			dataType : "json",
			type : "post",
			async : false,
			data : {
				bid : permission.id,
				title : permission.title,
				content : permission.content,
				publishState : permission.publishState,
				tagIds : permission.tagIds
			},
			error : function(data) {
				alert("请求出错了");
			}
		}).done(function(data) {
			if (data.isSuc == '1') {
				if(permission.publishState==1){
					alert("发布成功");
				}else{
					alert("保存草稿成功");
				}
			} else {
				alert("保存失败,    "+data.msg+"   请选择[取得HTML] 自行保存博客内容,以免丢失信息");
			}
		});
	};

	$("form#form-permission-edit").delegate("#submit_publish", "click", function() {
		if(permission.init()){
			permission.init();
			permission.publishState = 1;
			$(this).next().show();
			permission.saveBlog();
			$(this).next().hide();
		}
	});
	$("form#form-permission-edit").undelegate("#submit_save", "click").delegate("#submit_save", "click", function() {
		if(permission.init()){
			permission.publishState = 0;
			$(this).next().show();
			permission.saveBlog();
			$(this).next().hide();
		}
	});
	
	$("form#form-permission-edit").undelegate("input[name=showHtml]", "click").delegate(
			"input[name=showHtml]",
			"click",
			function() {
				document.getElementById("preview_permission").innerHTML = "<textarea  rows=12 style='width:100%'>"
						+ editor.html() + "</textarea>";
			});
	$("form#form-permission-edit").undelegate("input[name=previewHtml]", "click").delegate("input[name=previewHtml]",
			"click", function() {
				document.getElementById("preview_permission").innerHTML = editor.html();
				SyntaxHighlighter.all();
			});

})(jQuery);
