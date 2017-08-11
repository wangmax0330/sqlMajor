package com.pikia.component.web.controller;

import com.pikia.component.controller.ModelCrudOperator;
import com.pikia.component.web.service.SessionService;
import com.pikia.component.web.util.ResponseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ModelCrudControllerSupport extends ModelCrudOperator implements
		ApplicationContextAware {
	@Resource
	protected SessionService sessionService;

	protected ApplicationContext context;

	protected boolean isMobile(String mobiles) {
		if (StringUtils.isBlank(mobiles)) return false;
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	protected boolean isPassword(String pwd) {
		if (StringUtils.isBlank(pwd)) return false;
		if ((pwd.length() >= 6) && (pwd.length() <= 16)) return true;
		return false;
	}

	protected boolean isMail(String email) {
		if (StringUtils.isBlank(email)) return false;
		String check = "^([a-z0-9A-Z]+[-|._]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email.trim());
		boolean isMatched = matcher.matches();
		if (isMatched) {
			return true;
		}
		return false;
	}

	protected boolean checkToken(HttpServletRequest request, HttpServletResponse response) {
		String callbackName = request.getParameter("jsoncallback");
		Object user = this.sessionService.getCurrentUserId(request.getSession());
		if (user == null) {
			ResponseUtils.writeMessage(response,
					"{\"isSuc\":0,\"msg\":\"会话超时\",\"isTimeOut\":\"1\"}", callbackName);
			return false;
		}
		return true;
	}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
}
