package me.quyen.config;

import me.quyen.xml.WebXmlServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebXmlInitializerConfig {
    @Bean
    public WebXmlServletContextInitializer webXmlServletContextInitializer(){
        return new WebXmlServletContextInitializer();
    }
}
