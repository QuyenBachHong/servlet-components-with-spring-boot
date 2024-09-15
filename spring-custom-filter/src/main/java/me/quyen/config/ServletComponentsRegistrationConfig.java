package me.quyen.config;

import me.quyen.listener.CustomServletContextListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletComponentsRegistrationConfig {
    @Bean
    public ServletListenerRegistrationBean<?> myServletListenerRegistrationBean(){
        ServletListenerRegistrationBean<CustomServletContextListener> slrb
                = new ServletListenerRegistrationBean<>();
        slrb.setListener(new CustomServletContextListener());
        slrb.setEnabled(true);
        return slrb;
    }
}
