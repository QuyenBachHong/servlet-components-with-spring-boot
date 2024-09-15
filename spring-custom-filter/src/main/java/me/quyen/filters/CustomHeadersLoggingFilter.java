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

@Component
@Slf4j
public class CustomHeadersLoggingFilter extends OncePerRequestFilter {
    public static int count = 0;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

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
        return super.getFilterConfig();
    }


    @Override
    protected String getFilterName() {
        return super.getFilterName();
    }

    @Override
    protected ServletContext getServletContext() {
        return super.getServletContext();
    }


    @Override
    protected void initBeanWrapper(BeanWrapper bw) throws BeansException {
        super.initBeanWrapper(bw);
    }


    @Override
    protected void initFilterBean() throws ServletException {
        super.initFilterBean();
    }

    @Override
    public void destroy() {
        CustomHeadersLoggingFilter.super.destroy();
    }





}
