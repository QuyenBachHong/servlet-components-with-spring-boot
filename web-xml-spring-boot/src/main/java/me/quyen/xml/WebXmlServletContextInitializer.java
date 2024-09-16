package me.quyen.xml;

import jakarta.servlet.*;
import org.apache.tomcat.util.descriptor.web.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.xml.sax.InputSource;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation of {@link ServletContextInitializer} to register the servlets, listeners and filters defined
 * in the application's web.xml on startup.
 *
 * TODO servlets' init-params are not supported
 * TODO filters are not registered
 */
public class WebXmlServletContextInitializer implements ServletContextInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebXmlServletContextInitializer.class);

    private static final String WEB_XML_PATH = "/WEB-INF/web.xml";

    @Override
    public void onStartup(ServletContext servletContext) {
        WebXml webXml = parseWebXml(servletContext);
        registerServlets(webXml, servletContext);
        registerFilters(webXml, servletContext);
        registerListeners(webXml, servletContext);
    }

    private WebXml parseWebXml(ServletContext servletContext) {
        URL resource;
        try {
            resource = servletContext.getResource(WEB_XML_PATH);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }

        WebXml webXml = new WebXml();

        WebXmlParser parser = new WebXmlParser(false, false, false);
        parser.setClassLoader(WebXmlServletContextInitializer.class.getClassLoader());

        try (InputStream is = resource.openStream()) {
            boolean success = parser.parseWebXml(new InputSource(is), webXml, false);
            if (!success) {
                throw new IllegalStateException("Error parsing " + WEB_XML_PATH);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Error reading " + WEB_XML_PATH, e);
        }

        return webXml;
    }

    /**
     * It reads servlets defined in web.xml and registers them into the application.
     */
    private void registerServlets(WebXml webXml, ServletContext servletContext) {
        Map<String, String> servletMappings = webXml.getServletMappings();
        Optional.ofNullable(webXml.getServlets())
                .stream().map(Map::values).flatMap(Collection::parallelStream)
                .forEach(servletDef -> {
                    String servletName = servletDef.getServletName();
                    List<String> urlPatterns = findMatchedUrlPatterns(servletMappings, servletName);
                    if (urlPatterns.isEmpty()) {
                        throw new IllegalStateException("Not mapping defined for " + servletName);
                    }
                    urlPatterns.stream().parallel().forEach(urlPattern -> {
                                ServletRegistration.Dynamic reg = servletContext.addServlet(
                                        servletName, servletDef.getServletClass());
                                reg.addMapping(urlPattern);
                            });
                    LOGGER.info("Registered servlet with name [{}] Class[{}] Mappings[{}]"
                            , servletName, servletDef.getServletClass(),urlPatterns);
                });

    }



    /**
     * Method to read filters defined in web.xml and to register them into the application.
     */
    private void registerFilters(WebXml webXml, ServletContext servletContext) {
        List<FilterMap> filterMaps = Optional.ofNullable(webXml).stream()
                .map(WebXml::getFilterMappings)
                .flatMap(Collection::parallelStream)
                .peek(filterMap -> LOGGER.info("FILTER-NAME: {},URL-PATTERNS: {},SERVLET-NAMES: {}"
                        ,filterMap.getFilterName(),Arrays.toString(filterMap.getURLPatterns()),
                        Arrays.toString(filterMap.getServletNames())))
                .collect(Collectors.toList());
        Optional.ofNullable(webXml).stream()
                .map(WebXml::getFilters).map(Map::values)
                .flatMap(Collection::parallelStream)
                .peek(filterDef -> LOGGER.info("FILTER-NAME : {}, FILTER: {}, FITTER-CLASS: {}"
                            ,filterDef.getFilterName(),filterDef.getFilter(),filterDef.getFilterClass()))
                .forEach(filterDef -> {
                    String filterName = Optional.ofNullable(filterDef).stream()
                            .map(FilterDef::getFilterName).findFirst().orElse(null);
                    String filterClass = Optional.ofNullable(filterDef).stream()
                            .map(FilterDef::getFilterClass).findFirst().orElse(null);
                    if((Objects.nonNull(filterName)) && (Objects.nonNull(filterClass))){
                        List<Pair> pairs = findMatchedServletNamesAndUrlPatterns(filterMaps, filterName);
                        if (pairs.isEmpty()) {
                            throw new IllegalStateException("Not mapping defined for " + filterName);
                        }
                        pairs.stream().parallel()
                                .forEach(pair -> {
                                    FilterRegistration.Dynamic registration = servletContext.addFilter(filterName,filterClass);
//                                    String[] servletNames = pair.servletNames();
                                    String[] urlPatterns = pair.urlPatterns();
                                    EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.REQUEST,
                                            DispatcherType.ERROR, DispatcherType.ASYNC);
//                                    registration.addMappingForServletNames(dispatcherTypes,true,servletNames);
                                    registration.addMappingForUrlPatterns(dispatcherTypes,true,urlPatterns);
                                    LOGGER.info(
                                            "Registered Filter with name=`{}`, class=`{}`,url-patterns:{}, " +
                                                    "servlet-names:"
                                                    ,filterName,filterClass,Arrays.toString(urlPatterns)
                                    );
                                });

                    }
                });


    }

    /**
     * It reads listeners defined in web.xml and registers them into the application.
     */
    private void registerListeners(WebXml webXml, ServletContext servletContext) {
        Optional.ofNullable(webXml).stream()
                .map(WebXml::getListeners).flatMap(Collection::parallelStream)
                .forEach(listener -> {
                    servletContext.addListener(listener);
                    LOGGER.info("Registered listener class [{}]", listener);
                });
    }

    private List<String> findMatchedUrlPatterns(
            Map<String, String> servletMappings, String servletName) {
        List<String> urlPatterns = new ArrayList<>();
        servletMappings.entrySet().parallelStream()
                .filter(e -> Objects.equals(e.getValue(),servletName))
                .forEach(e -> urlPatterns.add(e.getKey()));
        return urlPatterns;
    }
    private List<Pair> findMatchedServletNamesAndUrlPatterns(
            List<FilterMap> filterMaps,String filterName){
        return filterMaps.stream()
                .filter(filterMap -> Objects.equals(filterMap.getFilterName(),filterName))
                .map(filterMap -> {
                    String[] urlPatterns = filterMap.getURLPatterns();
                    Arrays.sort(urlPatterns,Comparator.nullsLast(String::compareTo));
                    String[] servletNames = filterMap.getServletNames();
                    Arrays.sort(servletNames,Comparator.nullsLast(String::compareTo));
                    return new Pair(servletNames,urlPatterns);
                }).collect(Collectors.toList());
    }
    public static record Pair(String[] servletNames, String[] urlPatterns){
    }
    public static record Triple(String filterName,String[] servletNames, String[] urlPatterns){
    }
}
