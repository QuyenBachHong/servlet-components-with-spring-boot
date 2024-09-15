#Registering Servlet Components as Spring Beans
Spring provides following classes to register servlet-components-as-beans.
```
    ServletRegistrationBean
    FilterRegistrationBean
    ServletListenerRegistrationBean
```
#By registering above beans with Spring context, will automatically register the underlying Servlet-component to the Servlet Container.
#The Servlet component as a bean will also have all advantages which a Spring bean does, for example we can inject configuration or dependencies into them.