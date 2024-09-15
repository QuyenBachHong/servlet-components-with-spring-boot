package me.quyen.config;

import lombok.extern.slf4j.Slf4j;
import me.quyen.interceptors.CustomHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class HandlerInterceptorRegistrationConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("void me.quyen.config.HandlerInterceptorRegistrationConfig.addInterceptors(InterceptorRegistry)");
        registry.addInterceptor(new CustomHandlerInterceptor())
                .addPathPatterns("/spring/**");

    }
}
