var tk = tk || $.noop;
var editor;
// --------------------------------------------------Jquery插件加载完毕-后处理-------------------------------------------
(function($) {
	// ---------------------------重写load() 方法
	 jQuery.fn.load = function(e, t, n) {
	        if (typeof e != "string" && _load) return _load.apply(this, arguments);
	        var r, i, s, o = this,
	            u = e.indexOf(" ");
	        return u >= 0 && (r = e.slice(u, e.length), e = e.slice(0, u)), jQuery.isFunction(t) ? (n = t, t = undefined) : t && typeof t == "object" && (s = "POST"), o.length > 0 && jQuery.ajax({
	            url: e,
	            type: s,
	            dataType: "html",
	            data: t
	        }).done(function(e) {
	            if (e && e.indexOf("loginpagehtmlsessiontimeout") > -1) {
	                window.location.href = contextPath + "/d/page/login";
	                return
	            }
	            i = arguments, o.html(r ? jQuery("<div>").append(jQuery.parseHTML(e)).find(r) : e)
	        }).complete(n && function(e, t) {
	            o.each(n, i || [e.responseText, t, e]);
	        }), this;
	    };
	// ---------------------------------------遮罩效果
	tk.mask = function(txt) {
		$('.sync_icon_div').show();
		if (!txt)
			txt = "处理";
		// 显示操作提示，最关键核心代码
		$("#targetFixed").powerFloat({
			eventType : null,
			targetMode : "doing",
			target : "正在" + txt + "中...",
			position : "1-2"
		});
	};

	tk.unmask = function() {
		$('.sync_icon_div').hide();
		$("#overLay").remove();
		$.powerFloat.hide();
	};
	// ------------------------------懒载入
	tk.load = function(url, containerObj, fun, params) {
		tk.nohash = true;
		if (containerObj && containerObj != '') {

		} else {
			containerObj = $("#main");
		}
		tk.mask('加载');
		var funExe = false;
		console.debug(params);
		if (fun && fun == tk.menuFocus) {
			fun(params);
			funExe = true;
		}
		containerObj.load(url, {}, function() {
			containerObj.scrollTop(0);
			tk.nohash = false;
			tk.unmask();
			$('.loading_wp').hide();
			$("input[placeHolder],textarea[placeHolder]").powerFloat({
				eventType : "focus",
				targetMode : "remind",
				targetAttr : "placeHolder",
				position : "1-4"
			});

			if (fun && funExe == false) {
				fun(params);
			}
		});
	};
	 tk.menuFocus = function(params) {
	        $("#sidebar").find("li.active").removeClass('active');
	        $(params[0]).parent().addClass('active');
	    };
	tk.reLoad = function(defaultUrl, containerObj, fun) {
		var url = document.location.href;
		var bookUrl;
		var idx = url.indexOf("#");
		var len = url.length;
		if (idx >= 0 && idx < (len - 1)) {
			bookUrl = url.substring(url.indexOf("#") + 1, len);
		} else {
			bookUrl = defaultUrl;
		}
		tk.load(bookUrl, containerObj, fun);
	};

	// -----------------------------------------通用显示处理信息(ajax,表单验证之类的)
	// document.getElementById("box").style.cssText = "" 清空style 的样式
	// document.getElementById("objid").className="" 清空class样式

	tk.showSuccessMsg = function(msg) {
		var msgDiv = $('.alert_div h4');
		msgDiv.attr("class", ""); // 清空class
		msgDiv.addClass('alert_success'); // 追加class
		// <h4 class="alert_info">
		// <h4 class="alert_warning">
		// <h4 class="alert_error">
		// <h4 class="alert_success">
		msgDiv.show("slide", {
			direction : "right"
		}, 500).find("span").html(msg + '&nbsp;&nbsp;&nbsp;&nbsp;');
	};
	tk.showErrorMsg = function(msg) {
		var msgDiv = $('.alert_div h4');
		msgDiv.attr("class", ""); // 清空class
		msgDiv.addClass('alert_error'); // 追加class
		$(".alert_error").show("slide", {
			direction : "right"
		}, 500).find("span").html(msg + '&nbsp;&nbsp;&nbsp;&nbsp;');
	};
	tk.showWarningMsg = function(msg) {
		var msgDiv = $('.alert_div h4');
		msgDiv.attr("class", ""); // 清空class
		msgDiv.addClass('alert_warning'); // 追加class
		$(".alert_warning").show("slide", {
			direction : "right"
		}, 500).find("span").html(msg + '&nbsp;&nbsp;&nbsp;&nbsp;');
	};
	tk.showInfoMsg = function(msg) {
		var msgDiv = $('.alert_div h4');
		msgDiv.attr("class", ""); // 清空class
		msgDiv.addClass('alert_info'); // 追加class
		$(".alert_info").show("slide", {
			direction : "right"
		}, 500).find("span").html(msg + '&nbsp;&nbsp;&nbsp;&nbsp;');
	};
	// 回调函数
	function callback(obj) {
		setTimeout(function() {
			// $("#effect:visible").removeAttr("style").fadeOut();
			// $(".alert_info").show("slide", {}, 500).find("span").html(msg +
			// '&nbsp;&nbsp;&nbsp;&nbsp;');
		}, 1000);

	}
	;
	// ----------------------------------检测是否超时登陆
	tk.checkIsTimeout = function(data) {
		if (data && data.isTimeOut == 1) {
			window.location.href = contextPath + "/d/page/login";
		}
	};
	// --------------------------------------检查浏览器版本---------------------------------------
	tk.checkBrowserInfo = function(msg) {
		var userAgent = navigator.userAgent.toLowerCase();
		browser = {
			version : (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [ 0, '0' ])[1],
			safari : /webkit/.test(userAgent),
			opera : /opera/.test(userAgent),
			msie : /msie/.test(userAgent) && !/opera/.test(userAgent),
			mozilla : /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
		};
		if (browser.msie) {
			alert("this is msie"); // ie 浏览器
		} else if (browser.safari) {
			alert("this is safari!");
		} else if (browser.mozilla) {
			alert("this is mozilla!");
		} else if (browser.opera) {
			alert("this is opera");
		} else {
			alert("i don't konw!");
		}
	};
})(jQuery);
// --------------------------------------检查插件时候已经加载完毕
