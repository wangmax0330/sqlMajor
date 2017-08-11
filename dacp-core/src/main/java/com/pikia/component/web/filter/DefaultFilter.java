package com.pikia.component.web.filter;

import com.pikia.component.base.BaseDomain;
import com.pikia.component.web.service.SessionService;
import com.pikia.component.web.util.SimpleKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断没有url的权限
 * <p>
 * 暂时先判断irs的
 *
 * @date 2009-8-7
 * @date 2010-12-10
 * @date 2014-2-20 支持不进行登录判断开关
 * 当EXCEPT_PATH_REGEX变量为all时，不进行登录判断与日志记录等耦合操作，但是还处理字符集、mvcPath等参数的设置
 */
public class DefaultFilter implements Filter {

    private static Logger LOG = LoggerFactory.getLogger(DefaultFilter.class);

    private String EXCEPT_PATH_REGEX = ".*/(Login|login|api).*";

    private boolean REST_AUTH = false;

    private String TRACE_LOG = "none";

    private String ENCODING = "utf-8";

    private String DOCUMENT_PATTERN = ".*\\.(doc|docx|xsl|xslx|ppt|pptx|rar|zip|xml|txt|csv)";

    private String RESOURCE_PATTERN = ".*\\.(jpg|jpeg|bmp|gif|png|tng|css|js|swf)";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        res.addHeader("Server", "DACP SERVER");
        if (uri.matches(DOCUMENT_PATTERN)) {
            req.setCharacterEncoding(ENCODING);
            res.setContentType("application/octet-stream;charset=" + ENCODING);
        }
        if (req.getSession().getAttribute("REST_AUTH") == null) {
            req.getSession().setAttribute("REST_AUTH", REST_AUTH);
        }
        if (REST_AUTH && req.getSession().getAttribute("X-Auth-Token") == null) {
            String xAuthToken = SimpleKey.genShortUuid();
            req.getSession().setAttribute("X-Auth-Token", xAuthToken);
        }
        // 非资源文件
        // 不是资源文件都记录日志，有部分还需要判断权限
        if (!uri.matches(RESOURCE_PATTERN)) {
            // 一、字符集
            if (req.getHeader("Request-Type") != null && "ajax".equalsIgnoreCase(req.getHeader("Request-Type"))) {
                req.setCharacterEncoding("utf-8");
                res.setContentType("text/html;charset=utf-8");
            } else {
                req.setCharacterEncoding(ENCODING);
                // res.setCharacterEncoding(encoding);
                res.setContentType("text/html;charset=" + ENCODING);
            }

            /** 临时初始化mvcPath */
            // if (req.getSession().getAttribute("mvcPath") == null ||
            // req.getSession().getAttribute("springMvc") == null) {
            // /** 临时初始化mvcPath */
            // String springMvc =
            // Configuration.getInstance().getProperty("com.pikai.dacp.web.DefaultFilter.mvcPath");
            // String contextPath = req.getContextPath();
            // req.getSession().setAttribute("mvcPath", contextPath +
            // springMvc);
            // req.getSession().setAttribute("springMvc", springMvc);
            // }
            if (req.getSession().getAttribute("UA") == null
                    || ((String) req.getSession().getAttribute("UA")).length() == 0) {
                String uaStr = req.getHeader("user-agent");
                String ua = userAgent(uaStr);
                req.getSession().setAttribute("UA", ua);
            }
            BaseDomain user = null;
            String ip = null;
            long beginTime = System.currentTimeMillis();
            // userId = req.getSession().getAttribute("userId");
            // user = (SystemUserDomain)
            // req.getSession().getAttribute("userInfo");
            // 二、是否登录验证
            // 判断是否需要登录
            if (!"all".equalsIgnoreCase(EXCEPT_PATH_REGEX) && !uri.matches(EXCEPT_PATH_REGEX)) {
                // String userId = SsoService.getUserIdFromCookie(req);
                SessionService sessionService = SpringContextHolder.getApplicationContext().getBean(
                        SessionService.class);
                user = sessionService.getCurrentUser(req, BaseDomain.class);

                if (user == null) {// 跳转到登录页面
                    String loginPage = "/index";
//                            = Configuration.getInstance().getProperty("com.pikia.component.web.DefaultFilter.loginPage");
//                    if (loginPage == null || loginPage.length() == 0) {
//                        loginPage = "/loginPage";
//                }

                    // String contextPath = req.getContextPath();
                    // String newUri = uri.replaceAll(contextPath, "");
                    // String offset = "";
                    // // 遍历参数,用户登录完后重新带参数跳转
                    // if (newUri.length() > 1) {
                    // String paras = "";
                    // if (req.getParameterMap().entrySet().size() > 0) {
                    // Iterator<Entry<String, String[]>> iter =
                    // req.getParameterMap().entrySet().iterator();
                    // while (iter.hasNext()) {
                    // Entry<String, String[]> entry = iter.next();
                    // String paraKey = entry.getKey();
                    // String paraVal = entry.getValue()[0];
                    // paras += paraKey + "=" + paraVal + "$";
                    // }
                    // paras = "?" + paras.substring(0, paras.length() - 1);
                    // }
                    // offset = (loginPage.indexOf("?") > 0 ? "&" : "?") +
                    // "redirect=" + newUri + paras;
                    // }
                    // if (loginPage.startsWith("http://")) {
                    // res.sendRedirect(loginPage + offset);
                    // } else {
                    // String redirect =
                    // req.getSession().getAttribute("mvcPath") + loginPage;
                    // // String redirect = contextPath + loginPage;
                    // System.out.println(redirect + offset);
                    // res.sendRedirect(redirect + offset);
                    // }

                    String requestUrl = req.getRequestURL().toString();
                    request.getRequestDispatcher(loginPage).forward(request, response);
                    return;
                }
            }
        }
        try {
            chain.doFilter(req, res);
        } finally

        {
            // 这里可以记录日志
            // if ("all".equalsIgnoreCase(this.TRACE_LOG)) {
            // traceLog(user, uri, ip, beginTime, req);
            // } else if (("part".equalsIgnoreCase(this.TRACE_LOG)) && (
            // ("true".equalsIgnoreCase(req.getHeader("x-dacp-trace_log"))) ||
            // ("true".equalsIgnoreCase(req.getParameter("x-dacp-trace_log")))))
            // {
            // traceLog(user, uri, ip, beginTime, req);
            // }
        }

    }

    protected String userAgent(String uaStr) {
        String ua = "未知";
        if (uaStr == null) {
            return ua;
        }
        Pattern pattern = Pattern
                .compile(".*(iPhone|Android|iPad|IEMobile|OPhone|Chrome|Firefox|MSIE 11.0|MSIE 10.0|MSIE 9.0|MSIE 8.0|MSIE 7.0|MSIE 6.0|MSIE|Trident/7.0).*");
        Matcher m = pattern.matcher(uaStr);
        if (m.matches()) {
            ua = m.group(1);
            LOG.info("UA为：" + ua);
        }

        if (ua.equals("Trident/7.0")) {
            ua = "MSIE 11.0";
        }
        return ua;
    }

    public void init(FilterConfig config) throws ServletException {
        String encoding1 = config.getInitParameter("encoding");
        if (encoding1 != null && encoding1.length() > 0) {
            ENCODING = encoding1;
        }

        String except = config.getInitParameter("except_path_regex");
        if (except != null && except.length() > 0) {
            EXCEPT_PATH_REGEX = except;
        }

        String restAuth = config.getInitParameter("rest-auth");
        if ("true".equalsIgnoreCase(restAuth)) {
            REST_AUTH = true;
        }
    }

    public void destroy() {
    }

    public static void main(String[] args) {
        String EXCEPT_PATH_REGEX = ".*/(Login|login|api).*";
        String url = "/school-app/d-front/company/login";
        System.out.println(url.matches(EXCEPT_PATH_REGEX) + "-----------------" + url);
        url = "/school-app/page/login";
        System.out.println(url.matches(EXCEPT_PATH_REGEX) + "-----------------" + url);
    }
}
