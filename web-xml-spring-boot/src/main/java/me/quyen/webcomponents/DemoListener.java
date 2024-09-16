package me.quyen.webcomponents;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Application started! <<{}>>",sce.getClass().toGenericString());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Application stopped! <<{}>>",sce.getClass().toGenericString());
    }

}