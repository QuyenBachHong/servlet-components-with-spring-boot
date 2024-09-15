package me.quyen.servlet_components;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@WebListener
@Slf4j
public class MyJakartaServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("ORDER:`{}`, In Method `me.quyen.servlet_components.MyJakartaServletListener.contextInitialized" +
                "(ServletContextEvent)` at `{}`"
                ,++Counter.listenerCount, LocalDateTime.now());
        String sceName = Optional.ofNullable(sce).stream()
                .map(it -> it.getClass().toGenericString())
                .findFirst().orElse(null);
        log.info("ServletContextEvent object: `{}`",sceName);

        ServletContext servletContext = sce.getServletContext();
        String servletContextClass = Optional.ofNullable(servletContext).stream()
                .map(sc -> sc.getClass().toGenericString())
                .findFirst().orElse(null);
        Object source = sce.getSource();
        String sourceName = Optional.ofNullable(source).stream()
                .map(src -> src.getClass().toGenericString())
                .findFirst().orElse(null);
        log.info("ServletContextEvent.getServletContext(): `{}`",servletContextClass);
        log.info("ServletContextEvent.getSource(): `{}`",sourceName);
        ServletContextListener.super.contextInitialized(sce);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("ORDER:`{}`, In Method `me.quyen.servlet_components.MyJakartaServletListener.contextDestroyed" +
                        "(ServletContextEvent)` at `{}`"
                ,++Counter.listenerCount, LocalDateTime.now());
        String sceName = Optional.ofNullable(sce).stream()
                .map(it -> it.getClass().toGenericString())
                .findFirst().orElse(null);
        log.info("ServletContextEvent object: `{}`",sceName);

        ServletContext servletContext = sce.getServletContext();
        String servletContextClass = Optional.ofNullable(servletContext).stream()
                .map(sc -> sc.getClass().toGenericString())
                .findFirst().orElse(null);
        Object source = sce.getSource();
        String sourceName = Optional.ofNullable(source).stream()
                .map(src -> src.getClass().toGenericString())
                .findFirst().orElse(null);
        log.info("ServletContextEvent.getServletContext(): `{}`",servletContextClass);
        log.info("ServletContextEvent.getSource(): `{}`",sourceName);

        ServletContextListener.super.contextDestroyed(sce);
    }
}
