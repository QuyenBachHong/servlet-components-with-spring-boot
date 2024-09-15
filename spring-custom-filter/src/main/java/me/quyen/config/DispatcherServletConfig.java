package me.quyen.config;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.ServletContextListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.quyen.filters.Utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DispatcherServletConfig {
    final RequestContextFilter requestContextFilter;
    final DispatcherServlet dispatcherServlet;
    @PostConstruct
    public void init() {
        /*
         * For some reason, when running our spring boot application NOT in DEBUG-MODE,
         * Spring's RequestContextFilter overrode DispatcherServlet ThreadContextInheritable property.
         * In DEBUG-MODE setting the servlet is enough.
         */
        // Normal mode
        requestContextFilter.setThreadContextInheritable(true);
        Utils.classInfo(requestContextFilter.getClass(),log);
        // Debug mode
        dispatcherServlet.setThreadContextInheritable(true);
        Utils.classInfo(dispatcherServlet.getClass(),log);
    }
}
