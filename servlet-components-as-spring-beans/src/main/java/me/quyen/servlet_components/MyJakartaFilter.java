package me.quyen.servlet_components;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
public class MyJakartaFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(String.format("ORDER:`%s`, In `public void  me.quyen.servlet_components.MyJakartaFilter.init(FilterConfig)` at `%s`"
                ,++Counter.filterCount, LocalDateTime.now()));
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response
            , FilterChain chain) throws IOException, ServletException {
        String url = (request instanceof HttpServletRequest)
                ? ((HttpServletRequest) request).getRequestURL().toString()
                : "N/A";
        log.info(String.format("ORDER:`%s`, In `public void  me.quyen.servlet_components.MyJakartaFilter.doFilter" +
                "(ServletRequest,ServletResponse,FilterChain)` at `%s`, \t\n-->Processing-RequestURL:`%s`"
                ,++Counter.filterCount, LocalDateTime.now(),url));
        // allows to pass this filter
        chain.doFilter(request,response);
    }


    @Override
    public void destroy() {
        log.info(String.format("ORDER:%s, In `public void  me.quyen.servlet_components.MyJakartaFilter.destroy()` at %s"
                ,++Counter.filterCount, LocalDateTime.now()));
        Filter.super.destroy();
    }
}
