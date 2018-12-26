package xyz.yudong520.manageadmin.core.filter;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class PathLogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();
        log.info("正在请求的地址是："+requestURI);
//        String contentType = servletRequest.getContentType();
//        log.info("contentType====================================================="+contentType);
//        String characterEncoding = servletRequest.getCharacterEncoding();
//        log.info("characterEncoding====================================================="+characterEncoding);
//        String localAddr = servletRequest.getLocalAddr();
//        log.info("localAddr====================================================="+localAddr);
//        String localName = servletRequest.getLocalName();
//        log.info("localName====================================================="+localName);
//        int localPort = servletRequest.getLocalPort();
//        log.info("localPort====================================================="+localPort);
//        String protocol = servletRequest.getProtocol();
//        log.info("protocol====================================================="+protocol);
//        String remoteAddr = servletRequest.getRemoteAddr();
//        log.info("remoteAddr====================================================="+remoteAddr);
//        String remoteHost = servletRequest.getRemoteHost();
//        log.info("remoteHost====================================================="+remoteHost);
//        int remotePort = servletRequest.getRemotePort();
//        log.info("remotePort====================================================="+remotePort);
//        String scheme = servletRequest.getScheme();
//        log.info("scheme====================================================="+scheme);
//        int serverPort = servletRequest.getServerPort();
//        log.info("serverPort====================================================="+serverPort);
//        String serverName = servletRequest.getServerName();
//        log.info("serverName====================================================="+serverName);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
