package me.quyen.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import lombok.extern.slf4j.Slf4j;
import me.quyen.filters.Utils;

import java.util.Collections;
import java.util.Optional;
@Slf4j
public class CustomServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Utils.printInfo(
                "void me.quyen.listener.CustomServletContextListener.'contextInitialized'(ServletContextEvent)"
                ,"__NULL",log
        );
        Utils.classInfo(sce.getClass(),log);
        ServletContext servletContext = sce.getServletContext();
        Utils.classInfo(servletContext.getClass(),log);
        Optional.ofNullable(servletContext)
                .ifPresent(sc -> {
                    Collections.list(sc.getAttributeNames())
                            .forEach(it -> log.info("ServletContext-Attriubute:{}-{}"
                            ,it,servletContext.getAttribute(it)));

                });
        ServletContextListener.super.contextInitialized(sce);
    }


    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Utils.printInfo(
                "void me.quyen.listener.CustomServletContextListener.'contextDestroyed'(ServletContextEvent)"
                ,"__NULL",log
        );
        Utils.classInfo(sce.getClass(),log);
        ServletContext servletContext = sce.getServletContext();
        Utils.classInfo(servletContext.getClass(),log);
        Optional.ofNullable(servletContext)
                .ifPresent(sc -> {
                    Collections.list(sc.getAttributeNames())
                            .forEach(it -> log.info("ServletContext-Attriubute:{}-{}"
                                    ,it,servletContext.getAttribute(it)));
                });
        ServletContextListener.super.contextDestroyed(sce);
    }
}
