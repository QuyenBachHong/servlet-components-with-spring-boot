package me.quyen.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeansException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Component
@Slf4j
public class CustomHeadersLoggingFilter extends OncePerRequestFilter {
    public static int count = 0;
    public static int headerCount = 0;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        Collections.list(request.getHeaderNames())
                .forEach(
                        h -> log.info("{},header-name:{}, header-value:{}"
                                ,++CustomHeadersLoggingFilter.headerCount,h,Collections.list(request.getHeaders(h)))
                );
        // allow request pass this filer
        filterChain.doFilter(request,response);
    }

    @Override
    protected String getAlreadyFilteredAttributeName() {
        String alreadyFilteredAttributeName = CustomHeadersLoggingFilter.super.getAlreadyFilteredAttributeName();
        Utils.printInfo("String me.quyen.filters.CustomHeadersLoggingFilter.getAlreadyFilteredAttributeName()"
                            ,alreadyFilteredAttributeName,log);
        return alreadyFilteredAttributeName;
    }

    @Override
    public Environment getEnvironment() {
        Environment environment = CustomHeadersLoggingFilter.super.getEnvironment();
        Utils.classInfo(environment.getClass(),log);
        return environment;
    }


    @Override
    public void afterPropertiesSet() throws ServletException {
        Utils.printInfo(
                "void me.quyen.filters.CustomHeadersLoggingFilter.afterPropertiesSet() throws ServletException"
                ,"__NULL", log);
        CustomHeadersLoggingFilter.super.afterPropertiesSet();
    }


    @Override
    public FilterConfig getFilterConfig() {
        FilterConfig filterConfig = CustomHeadersLoggingFilter.super.getFilterConfig();
        Utils.classInfo(filterConfig.getClass(),log);
        return filterConfig;
    }


    @Override
    protected String getFilterName() {
        String filterName = CustomHeadersLoggingFilter.super.getFilterName();
        Utils.printInfo("me.quyen.filters.CustomHeadersLoggingFilter.getFilterName()"
                            ,filterName,log);
        return filterName;
    }

    @Override
    protected ServletContext getServletContext() {
        ServletContext servletContext = CustomHeadersLoggingFilter.super.getServletContext();
        Utils.classInfo(servletContext.getClass(),log);
        return servletContext;
    }


    @Override
    protected void initBeanWrapper(BeanWrapper bw) throws BeansException {
        Utils.printInfo(
                "me.quyen.filters.CustomHeadersLoggingFilter.initBeanWrapper(BeanWrapper) throws BeansException"
                        ,"__null",log);
        Utils.classInfo(bw.getClass(),log);
        CustomHeadersLoggingFilter.super.initBeanWrapper(bw);
    }


    @Override
    protected void initFilterBean() throws ServletException {
        Utils.printInfo(
                "me.quyen.filters.CustomHeadersLoggingFilter.initFilterBean() throws BeansException"
                ,"__null",log);
        CustomHeadersLoggingFilter.super.initFilterBean();
    }

    @Override
    public void destroy() {
        Utils.printInfo(
                "me.quyen.filters.CustomHeadersLoggingFilter.destroy() throws BeansException"
                ,"__null",log);
        CustomHeadersLoggingFilter.super.destroy();
    }

}
