package com.anvasy.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EncodingFilter.class);

    public void init(FilterConfig filterConfig) {
        logger.debug("EncodingFilter created.");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        response.setContentType("text/html; charset=UTF-8");
    }

    public void destroy() {
        logger.debug("EncodingFilter destroyed.");
    }
}