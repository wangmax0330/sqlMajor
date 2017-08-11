package com.pikia.component.web.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * 单点登录
 * 
 * @author methew
 * 
 */
@Component
public class SsoService {
	public static final String DACP_SSO_UID = "dacp_sso_uid";
	public static final String DACP_OPEN_SESSIONKEY = "dacp_open_sessionkey";

	public void setCookie(String userId, HttpServletResponse response) {
		Cookie cookie = new Cookie("dacp_sso_uid", userId);
		cookie.setMaxAge(1800);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static String getUserIdFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String userId = "";
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if ((cookie != null) && ("dacp_sso_uid".equalsIgnoreCase(cookie.getName()))) {
					// userId = DesCipher.decrypt(cookie.getValue());
				}
			}
		}
		return userId;
	}
}
