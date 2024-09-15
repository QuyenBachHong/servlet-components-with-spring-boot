package me.quyen.config;

import jakarta.servlet.http.HttpServlet;
import me.quyen.servlet_components.MyJakartaFilter;
import me.quyen.servlet_components.MyJakartaServet;
import me.quyen.servlet_components.MyJakartaServletListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class ServletComponentRegistrationConfig {
    @Bean
    public  ServletRegistrationBean<?> servetServletRegistrationBean(){
        ServletRegistrationBean<MyJakartaServet> srb = new ServletRegistrationBean<>();
        srb.setServlet(new MyJakartaServet());
        srb.setUrlMappings(Set.of("/my-servlet/*"));
        return srb;
    }

    @Bean
    public FilterRegistrationBean<?> filterRegistrationBean(){
        FilterRegistrationBean<MyJakartaFilter> frb = new FilterRegistrationBean<>();
        frb.setFilter(new MyJakartaFilter());
        frb.setUrlPatterns(Set.of("/*"));
        return frb;
    }

    @Bean
    public ServletListenerRegistrationBean<?> servletListenerRegistrationBean(){
        ServletListenerRegistrationBean<MyJakartaServletListener> lrb
                = new ServletListenerRegistrationBean<>();
        lrb.setListener(new MyJakartaServletListener());
        return lrb;
    }
}
