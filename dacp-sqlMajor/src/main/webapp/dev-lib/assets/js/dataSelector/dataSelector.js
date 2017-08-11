var blurflag = false;
$(document).ready(function() {
	function isInArray(array,it) {
		for (var i =0 ;i< array.length;i++){
			if(array[i]==it) return true;
		}
		return false;
	}
    $("body").delegate(".add_idcon", "click", function() {
        var $this = $(this);
        $this.hide().next("a").show().find("input").focus().trigger("focus");
    });

    $("body").delegate(".dataSelectorInput", "keydown", function(event) {
        var keyCode = event.which || event.keyCode;
        if (keyCode == 38 || keyCode == 40 || keyCode == 13) { //up
        	console.info("按键事件");
            return false;
        }
        if (keyCode == 27) {
            $("#usl-div").hide();
        }
    });

    $("body").delegate("#usl-div", "mouseover", function(event) {
        blurflag = true;
    });

    $("body").delegate("#usl-div", "mouseleave", function(event) {
        blurflag = false;
    });
    $("body").delegate(".del-dataSelector", "click", function(event) {
        var $this = $(this);
        var $add_workgrounp = $this.closest(".add_workgrounp");
        var $selectedInput = $add_workgrounp.find(".selectedInput");
        var data = [];
        $this.closest("span").remove();
        $add_workgrounp.find(".z").each(function() {
            data.push($(this).attr("did"));
        });
        $selectedInput.val(data.join(","));
        if (data.length == 0) {
            $add_workgrounp.find(".add_idcon").show().next("a").hide().find("input").val('');
        }
    });
    $("body").delegate(".dataSelectorInput", "focus", function(event) {
        var $this = $(this);
        var $add_workgrounp = $this.closest(".add_workgrounp");
        var value = this.value;
        $rel_add_workgrounp = $add_workgrounp;
        if (value == null || $.trim(value) == "") {
            $("body").css("position", "relative").find("#usl-div").remove();
            var $usl = $("body").append("<div style='display:block' id='usl-div' class='user_list'><ul class='u-li'></ul></div>").find("ul.u-li");
            $("body").find("#usl-div").css({
                left: $add_workgrounp.offset().left,
                top: $add_workgrounp.offset().top + $add_workgrounp.height()
            });
            $usl.append("<li style='list-style-type:none;background:#f0f0f0;text-align:center'><a>输入名称或者拼音...</a></li>");
        }
    });

    $("body").delegate(".dataSelectorInput", "blur", function(event) {
        if (!blurflag) {
            var $this = $(this);
            var $add_workgrounp = $this.closest(".add_workgrounp");
            $("body").find("#usl-div").remove();
            $add_workgrounp.find(".add_idcon").show().next("a").hide().find("input").val('');
        }
    });
    $("body").delegate(".dataSelectorInput", "keyup", function(event) {
        var $this = $(this);
        var value = this.value;
        var $add_workgrounp = $this.closest(".add_workgrounp");
        var data = [];
        var $cwidthdiv = $add_workgrounp.find(".cwidthdiv").html(value);
        var $add_idcon = $add_workgrounp.find(".add_idcon");
        var single = $add_workgrounp.hasClass("single");
        var $selectedInput = $add_workgrounp.find(".selectedInput");
        var width = $cwidthdiv.width();
        var keyCode = event.which || event.keyCode;
        $rel_add_workgrounp = $add_workgrounp;
        $add_workgrounp.find(".z").each(function() {
            data.push($(this).attr("did"));
        });
        $this.width(width + 20);
        $wbatusersprompt = $("#usl-div");
        if (keyCode == 27) return;
        if ($wbatusersprompt.size() > 0 && $wbatusersprompt.is(":visible")) {
            var $cur = $wbatusersprompt.find("li.selected");
            if (keyCode == 38) { //up
                var $prev = $cur.prev();
                if ($prev.size() > 0) {
                    $prev.addClass("selected").siblings().removeClass("selected");
                }
                return;
            }
            if (keyCode == 40) { //down
                var $next = $cur.next("li.ssu");
                if ($next.size() > 0) {
                    $next.addClass("selected").siblings().removeClass("selected");
                }
                return;
            }
            if (keyCode == 13) { //enter
                if ($cur.size() > 0) {
                    var id = $cur.find("a").attr("did");
                    var dname = $cur.find("a").attr("dname");
                    var funcStr = $add_idcon.attr("func");

                    if (isInArray(data, id)) {
                        $wbatusersprompt.hide();
                        $this.val('');
                        return;
                    }
                    if (single) {
                        data = [];
                        $add_workgrounp.find(".z").remove();
                    }
                    data.push(id);
                    $selectedInput.val(data.join(","));
                    $add_idcon.before("<span class='z' did='" + id + "'>" + dname + "<i class='del-dataSelector'></i></span>");
                    if (funcStr != '' && funcStr.length > 0) {
                        eval(funcStr + "(" + id + ",'" + dname + "','" + $selectedInput.attr("id") + "')");
                    }
                    $wbatusersprompt.hide();
                    $this.val('');
                }
                return;
            }
        }
        var urls = $this.attr("action");
        var relObjId = $this.attr("relObjId");
        var relObjVal = '';
        if ($('#' + relObjId).length > 0) {
            relObjVal = $('#' + relObjId).val();
        }
        if (value != null && $.trim(value) != "") {
            console.info("-----------" + value);
            $.ajax({
                url: contextPath + urls,
                dataType: "json",
                type: "post",
                data: {
                    name: $.trim(value),
                    relObjId: relObjVal,
                    pageSize: 10
                },
                success: function() {
                },
                error: function(err) {
                    alert("系统错误，请联系管理员！");
                }
            }).done(function(data) {
                $("body").css("position", "relative").find("#usl-div").remove();
                if (data.isSuc == 1) {
                    var $usl = $("body").append("<div style='display:block' id='usl-div' class='user_list'><ul class='u-li'></ul></div>").find("ul.u-li");
                    $("body").find("#usl-div").css({
                        left: $add_workgrounp.offset().left,
                        top: $add_workgrounp.offset().top + $add_workgrounp.height()
                    });
                    $.each(data.rows, function(i, row) {
                        $usl.append("<li class='ssu " + (i == 0 ? "selected" : "") + "'><a href='javascript:void(0);' dname='" + row.name + "' did='" + row.id + "'>" + row.name + "</a></li>")
                    });
                    if (data.pagi) {
                        if (data.pagi.records > data.pagi.pageSize) {
                            $usl.append("<li><a>更多...</a></li>");
                        }
                        if (data.pagi.records == 0) {
                            $usl.append("<li style='background:#f0f0f0;text-align:center'><a>未能找到数据...</a></li>");
                        }
                    }
                }
            });
        } else {
            $("body").css("position", "relative").find("#usl-div").remove();
            var $usl = $("body").append("<div style='display:block' id='usl-div' class='user_list'><ul class='u-li'></ul></div>").find("ul.u-li");
            $("body").find("#usl-div").css({
                left: $add_workgrounp.offset().left,
                top: $add_workgrounp.offset().top + $add_workgrounp.height()
            });
            $usl.append("<li style='background:#f0f0f0;text-align:center'><a>输入名称或者拼音...</a></li>");
        }
    });

    $("body").delegate(".ssu", "click", function(event) {
        var $this = $(this);
        var $add_workgrounp = $rel_add_workgrounp;
        var $selectedInput = $add_workgrounp.find(".selectedInput");
        var $add_idcon = $add_workgrounp.find(".add_idcon");
        var single = $add_workgrounp.hasClass("single");
        var data = [];
        var id = $this.find("a").attr("did");
        var dname = $this.find("a").attr("dname");
        $wbatusersprompt = $("#usl-div");
        var funcStr = $add_idcon.attr("func");

        $add_workgrounp.find(".z").each(function() {
            data.push($(this).attr("did"));
        });
        if (isInArray(data, id)) {
            $wbatusersprompt.hide();
            $add_workgrounp.find(".dataSelectorInput").val('');
            return;
        }
        if (single) {
            data = [];
            $add_workgrounp.find(".z").remove();
        }
        data.push(id);
        $selectedInput.val(data.join(","));
        $add_idcon.before("<span class='z' did='" + id + "'>" + dname + "<i class='del-dataSelector'></i></span>");
        if (funcStr != '' && funcStr.length > 0) {
            eval(funcStr + "(" + id + ",'" + dname + "','" + $selectedInput.attr("id") + "')");
        }
        $wbatusersprompt.hide();
        $add_workgrounp.find(".dataSelectorInput").val('');
        $add_workgrounp.find(".add_idcon").show().next("a").hide().find("input").val('');
    });
    $("body").delegate(".ssu", "mouseover", function(event) {
        $(this).addClass("selected").siblings(".ssu").removeClass("selected");
    });
});
