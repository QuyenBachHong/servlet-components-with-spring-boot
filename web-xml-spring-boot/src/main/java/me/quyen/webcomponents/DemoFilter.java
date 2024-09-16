package me.quyen.webcomponents;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

@Slf4j
public class DemoFilter implements Filter {

    public DemoFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("DemoFilter init!");
    }

    @Override
    public void destroy() {
        System.out.println("DemoFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String servletPath = req.getServletPath();

        log.info("#INFO :{}- ServletPath : {}", LocalDateTime.now(),servletPath);
        log.info("#[public void doFilter(ServletRequest request, ServletResponse response, FilterChain " +
                "chain)] REQUEST-URL:{}",(req.getRequestURL().toString()));
        Collections.list(req.getHeaderNames())
                        .forEach(hn -> log.info("---> header-name:{}, header-value:{}"
                                ,hn,req.getHeaders(hn)));
        chain.doFilter(request, response);
    }

}