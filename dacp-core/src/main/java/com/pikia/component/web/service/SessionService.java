package com.pikia.component.web.service;

import com.pikia.component.web.controller.SimpleController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract interface SessionService {
    public static final String LOCALE_SESSION_ATTRIBUTE_NAME = SimpleController.class.getName() + ".LOCALE";

    public abstract <T> T getCurrentUser(HttpServletRequest paramHttpServletRequest, Class<T> paramClass);

    public abstract void setCurrentUser(HttpServletRequest paramHttpServletRequest, Object paramObject);


    public abstract <T> T getCurrentUser(HttpSession paramHttpSession, Class<T> paramClass);

    public abstract void setCurrentUser(HttpSession paramHttpSession, Object paramObject);

    public abstract void setCurrentUserId(HttpSession paramHttpSession, Long paramLong);

    public abstract Long getCurrentUserId(HttpSession paramHttpSession);
}
