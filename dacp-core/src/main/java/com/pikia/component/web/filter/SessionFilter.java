//package com.pikia.component.web.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.ApplicationContext;
//import org.springframework.util.StringUtils;
//
//import com.pikia.component.web.service.SessionService;
//
///**
// * Session 请求过滤器
// *
// * @author methew
// */
//public class SessionFilter implements Filter {
//
//    protected final Logger logger = Logger.getLogger(SessionFilter.class);
//    // 忽略的请求地址
//    public static final String IGNORE_URLS = "ignoreUrls";
//    // 是否需要追踪日志
//    private String TRACE_LOG = "none";
//    private String ENCODING = "utf-8";
//    protected FilterConfig filterConfig;
//    protected String[] ignoreUrls;
//
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//        if (logger.isInfoEnabled()) {
//            logger.info("Initializing filter '" + filterConfig.getFilterName()
//                    + "'");
//        }
//
//        this.filterConfig = filterConfig;
//
//        String ignoreUrlsString = this.filterConfig
//                .getInitParameter(IGNORE_URLS);
//        if (StringUtils.hasText(ignoreUrlsString)) {
//            if (logger.isInfoEnabled()) {
//                logger.info("Ignore Urls: " + ignoreUrlsString);
//            }
//            ignoreUrls = StringUtils
//                    .commaDelimitedListToStringArray(ignoreUrlsString);
//        } else {
//            if (logger.isInfoEnabled()) {
//                logger.info("No ignore list configured");
//            }
//            ignoreUrls = new String[0];
//        }
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        ApplicationContext applicationContext = SpringContextHolder
//                .getApplicationContext();
//        SessionService sessionService = SpringContextHolder
//                .getApplicationContext().getBean(SessionService.class);
//        if (ignoreRequest(httpRequest)) {
//            filterChain.doFilter(request, response);
//        } else {
//            SystemUserDomain user = sessionService.getCurrentUser(httpRequest,
//                    SystemUserDomain.class);
//            if (user == null) {
//                if (isAjax(httpRequest)) {
//                    logger.info("--------isAjax(httpRequest)-------------");
//                    redirect("/d/page/res.ajaxSessionOut", httpRequest,
//                            (HttpServletResponse) response);
//                } else {
//                    String requestUrl = httpRequest.getRequestURL().toString();
//                    logger.info("-------- not isAjax(httpRequest)-------------"
//                            + requestUrl);
//                    // redirect("/d/page/login", httpRequest,
//                    // (HttpServletResponse)response);
//                    request.getRequestDispatcher("/d/page/login").forward(
//                            request, response);
//                }
//            } else {
//                // ModelContainer modelContainer =
//                // SpringContextHolder.getApplicationContext()
//                // .getBean(ModelContainer.class);
//                // try {
//                // if (PropertyUtils.getProperty(user, "lazyLoader") == null) {
//                // user = modelContainer.enhanceModel(user);
//                // }
//                // if (sessionService
//                // .getCurrentUser(httpRequest.getSession(),
//                // ApsUserDomain.class) == null) {
//                // sessionService.setCurrentUser(httpRequest.getSession(),
//                // user);
//                // }
//                // } catch (Exception e) {
//                // logger.error(e, e);
//                // }
//                System.out.println("----------user is not null-----------");
//                filterChain.doFilter(request, response);
//            }
//
//        }
//    }
//
//    protected boolean isAjax(HttpServletRequest httpRequest) {
//        String requestUrl = httpRequest.getRequestURL().toString();
//        if (logger.isDebugEnabled()) {
//            logger.debug("Request URL: " + requestUrl);
//        }
//        requestUrl = requestUrl.substring(requestUrl.indexOf(":") + 3);// remove
//        // or
//        // and
//        // https
//        requestUrl = requestUrl.substring(requestUrl.indexOf("/"));// remove
//        // domain
//        String context = httpRequest.getContextPath();
//        if (requestUrl.startsWith(context + "/a/")) {
//            return true;
//        }
//        return false;
//    }
//
//    protected boolean ignoreRequest(HttpServletRequest httpRequest) {
//        String requestUrl = httpRequest.getRequestURL().toString();
//        if (logger.isDebugEnabled()) {
//            logger.debug("Request URL: " + requestUrl);
//        }
//        String context = httpRequest.getContextPath();
//        for (String ignoreUrl : ignoreUrls) {
//            if (requestUrl.indexOf(context + ignoreUrl) > -1) {
//                if (logger.isDebugEnabled()) {
//                    logger.debug("Matched ignore url: " + ignoreUrl
//                            + ", passing......");
//                }
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Clean up resources
//     */
//    public void destroy() {
//        this.filterConfig = null;
//    }
//
//    protected void redirect(String url, HttpServletRequest request,
//                            HttpServletResponse response) throws IOException {
//        /** only prepend context path for relative path */
//        if (url.startsWith("/")) {
//            url = request.getContextPath() + url;
//        }
//        response.sendRedirect(response.encodeRedirectURL(url));
//    }
//}
