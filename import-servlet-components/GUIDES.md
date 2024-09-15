```
@Import(org.springframework.boot.web.servlet.ServletComponentScanRegistrar.class)
public @interface ServletComponentScan
```
#Spring boot's annotation "@ServletComponentScan" enables scanning of Servlet components.
##That means Servlet 3.0 annotations: `@WebServlet, @WebFilter and @WebListener` 
##can be used along with Spring components.

#`@ServletComponentScan` imports `org.springframework.boot.web.servlet.ServletComponentScanRegistrar.class` 
##which is an implementation of `ImportBeanDefinitionRegistrar`. 
##This implementation performs package scanning and kicks off the servlet components registration process.
##By default Spring will scan the package of the class where this annotation is used. The scanning package can be 
##altered by using one of the elements: value, backPackages or basePackageClasses of this annotation.

#@ServletComponentScan will @@ONLY be used when the application needs to run in embedded container.
##When deployed in an external container, this annotation has no effect, as Servlet container's built-in scanning 
##mechanism (per Servlet specifications) takes care of discovering servlet components.
