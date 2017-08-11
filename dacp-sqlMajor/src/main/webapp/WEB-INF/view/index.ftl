<#setting number_format="#">
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <title></title>
</head>
<link type="text/css" href="${rc.contextPath}/dev-lib/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
<link type="text/css" href="${rc.contextPath}/dev-lib/assets/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${rc.contextPath}/dev-lib/assets/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/dev-lib/assets/bootstrap/js/bootstrap.min.js"></script>
<link type="text/css" href="${rc.contextPath}/dev-lib/assets/css/site.css" rel="stylesheet">

<script type="text/javascript">
    var contextPath = '${rc.contextPath}';
    var login = {} || login;
    $(function() {
        login.autoLogin = false;
        login.cookieName = '_work_cookie_login_name';
        login.cookieCompany = '_work_cookie_login_company';
        login.cookiePasword = '_work_cookie_login_pwd';
        login.cookieChk = '_work_cookie_login_chk';
        login.cookieUID = "_desed_gdpj";

        var validate = true;
        login.toLogin = function(isAuto) {
            var userName = $(".login_form input[name='userName']");
            if (userName.val() == null || $.trim(userName.val()).length == 0) {
                validate = false;
            }
            var password = $(".login_form input[name='password']");
            if (password.val() == null || $.trim(password.val()).length == 0) {
                validate = false;
            }
            var submiBtn = $('.login_form .submit');
            if (validate == false) {
                submiBtn.val('登录');
                submiBtn.powerFloat({
                    eventType: null,
                    targetMode: "remind",
                    target: "输入内容不能为空！",
                    position: "2-1"
                });
                return false;
            }
            // alert($('.login_form input[type="checkbox"]').is(':checked'));
            if (validate) {
                $.ajax({
                    url: contextPath + "/login/checkLogin",
                    dataType: "json",
                    type: "post",
                    data: {
                        userName: userName.val(),
                        password: password.val()
                    }
                }).done(function(data) {
                    if (data.isSuc == '1') {
                        if (!isAuto) {
                            window.location = contextPath + "/customer/index";
                        }
                    } else {
                          alert("用户名或密码错误！");
                    }
                });
            }
        }
        //----------------登陆
        $(".login_form").undelegate(".submit_button", "click").delegate(".submit_button", "click", function() {
            login.toLogin(false);
        });

        $(".login_form").undelegate("input[name='userName']", "keydown").delegate("input[name='userName']", "keydown", function(e) {
            var key = e.which;
            if (key == 13) {
                $(".login_form input[name='password']").focus();
            }
        });
        $(".login_form").undelegate("input[name='password']", "keydown").delegate("input[name='password']", "keydown", function(e) {
            var key = e.which;
            if (key == 13) {
                login.toLogin(false);
            }
        });
    });
</script>

<body>
<div class="container">
    <div class="login_form" method='post' action='${rc.contextPath}/login/checkLogin' onsubmit="return checkUser();">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputAccount" class="sr-only">账号</label>
        <input type="text" id="inputAccount" name="userName" class="form-control" placeholder="账号" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码" required>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block submit_button" type="submit">登陆</button>
    </div>
</div>
<!-- /container -->
<#--<a href="${rc.contextPath}/customer/index" target="_self">客户管理</a>
    <br/>-->
</body>

</html>
